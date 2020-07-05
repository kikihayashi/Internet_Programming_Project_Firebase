package Controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.CheckData;
import Model.DataUpdate;
import Model.CheckCookies;
import Model.MyDataBase;
import JavaBeans.Buy;
import JavaBeans.User;

/*
 * 這支Login程式扮演Controller的角色，負責處理使用者登入的工作
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
		
	private String login_page = null;//用來確認目前的頁面
	private String login_state = null;//用來確認是否已登入
	private String pageToForward = null;//建立一個頁面導向設定，隨著程式條件而改變
	private String login_message = null;//建立一個登入訊息設定，隨著程式條件而改變			
	private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
	private DatabaseReference userDataRef = rootRef.child("NTU_BS").child("UserData");
	private DatabaseReference buyDataRef = rootRef.child("NTU_BS").child("BuyData");
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");//設定請求編碼為UTF-8
		response.setCharacterEncoding("UTF-8");//設定回應編碼為UTF-8	
		HttpSession session = request.getSession();//利用getSession()方法取得HttpSession物件

		login_page = request.getParameter("page");//得到登入頁面Page1的一些資訊
		login_state = (String)session.getAttribute("state");//得到登入狀態
		User user = (User)session.getAttribute("user");

		if("登入".equals(login_page))//如果使用者在頁面Page1有按"登入"
		{
			//得到使用者在Page1輸入的資料
			String now_ID = request.getParameter("ID");
			String now_PWD = request.getParameter("PWD");
			String now_login_set = request.getParameter("login_set");

			//因為MyDataBase.getFromFirebase是非同步，使用CountDownLatch指定主程式需等待1個執行緒
			final CountDownLatch latch = new CountDownLatch(1);
			try
			{
				//從DataBase中取資料
				MyDataBase.getFromFirebase(userDataRef.child(now_ID), "getOne", User.class, new MyDataBase.JavaBeansCallback()
				{
					@Override
					public <T> void onCallback(List<T> javaBeansList)
					{
						User inputuser = (User)(javaBeansList.get(0));
						//檢查使用者的帳號、密碼
						login_message = CheckData.checkPassword(now_ID, now_PWD, inputuser);
						//將得到的user_value，setAttribute
						session.setAttribute("user", inputuser);
						//讓CountDownLatch設定的數值減掉1
						latch.countDown();
					}
				});

				latch.await();//等CountDownLatch設定的數值變成0時，主程式才會繼續執行下面的程式
				
				switch (login_message) 
				{
				case "Nothing":				
					login_message = "請輸入帳號與密碼";
					pageToForward = "userInfoPage1.jsp";//將使用者導向登入頁面Page1					
					break;

				case "No_ID":				
					login_message = "輸入帳號錯誤，查無此帳號";
					pageToForward = "userInfoPage1.jsp";//將使用者導向登入頁面Page1		
					break;

				case "No_PWD":				
					login_message = "輸入密碼錯誤";
					pageToForward = "userInfoPage1.jsp";//將使用者導向登入頁面Page1			
					break;

				case "Yes_PWD":
					login_message = "";
					pageToForward = "userInfoPage2.jsp";//將使用者導向登入頁面Page2userInfoPage2.jsp
					
					
					login_state = "Logged_in";
					session.setAttribute("state", login_state);
					DataUpdate.refresh(buyDataRef.child(now_ID), Buy.class, "buyList", session);
					
					if ("auto".equals(now_login_set))
					{
						//設定cookie儲存使用者的帳密
						Cookie cookie1 = new Cookie("ID", now_ID);
						Cookie cookie2 = new Cookie("PWD", now_PWD);
						
						//設定生命週期為1分鐘
						cookie1.setMaxAge(1*60);
						cookie2.setMaxAge(1*60);

						//將cookie加到response中
						response.addCookie(cookie1);
						response.addCookie(cookie2);					
					}
					
					break;									
				}				
			}
			catch (Exception e) 
			{
				System.out.println("Exception");
			}		
		}
		
		else if("Logged_in".equals(login_state))
		{
			DataUpdate.refresh(buyDataRef.child(user.getAccountName()), Buy.class, "buyList", session);
			login_message = "";
			pageToForward = "userInfoPage2.jsp";//將使用者導向登入頁面Page2
		}
		
		else//如果沒按送出，檢查是否有cookie。有：去CookieCheck / 沒有：去登入頁面Page1
		{		
			Cookie[] cookies = request.getCookies();
			CheckCookies cookiesCheck = new CheckCookies(cookies, session);
			
			login_message = "";
			pageToForward = (cookies != null)?
					("Yes_PWD".equals(cookiesCheck.getCookieMessage()))? "userInfoPage2.jsp" : "userInfoPage1.jsp"
					: "userInfoPage1.jsp";
		}
		//將此login_message物件setAttribute，登入頁面要使用
		request.setAttribute("login_message", login_message);
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageToForward);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}
}
