package Controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaBeans.User;
import Model.MyDataBase;
import Model.SetToJavaBeans;

/*
 * 這支AddNewUser程式扮演Controller的角色，負責處理使用者註冊的工作
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private String login_page = null;//用來確認目前的頁面
	private String register_message = null;//建立一個註冊訊息，隨著程式條件而改變
	private String jspPageToForward = null;//建立一個導向設定，隨著程式條件而改變	

	private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
	private DatabaseReference userDataRef = rootRef.child("NTU_BS").child("UserData");
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");//設定請求編碼為UTF-8 
		response.setCharacterEncoding("UTF-8");//設定回應編碼為UTF-8 
		HttpSession session = request.getSession();//利用getSession()方法取得HttpSession物件
			
		login_page = request.getParameter("page");//得到目前是到註冊頁面的哪裡

		if("下一頁".equals(login_page))//如果在註冊介面Page1已按"下一頁"，就設定導向註冊介面Page2
		{
			//得到註冊介面Page1的資訊
			String name_show  = request.getParameter("name");	
			String sid_show   = request.getParameter("sid");	
			String phone_show = request.getParameter("phone");
			String mail_show  = request.getParameter("mail");
			String edu_show   = request.getParameter("edu");
			String grade_show = request.getParameter("grade");

			//將以上物件setAttribute
			session.setAttribute("name_show", name_show);
			session.setAttribute("sid_show", sid_show);
			session.setAttribute("phone_show", phone_show);
			session.setAttribute("mail_show", mail_show);
			session.setAttribute("edu_show", edu_show);
			session.setAttribute("grade_show", grade_show);

			register_message = "";
			jspPageToForward = "addUserPage2.jsp";//設定導向註冊介面Page2
		}

		else if("送出".equals(login_page))//如果在註冊頁面Page2已按"送出"，就進行帳號登錄的邏輯檢驗相關動作
		{			
			//得到使用者名稱
			String ID = request.getParameter("ID");
			String PWD = request.getParameter("PWD");

			//檢查帳號是否存在---------------------------------------------------------
			final CountDownLatch latch = new CountDownLatch(1);
			try
			{	
				MyDataBase.getFromFirebase(userDataRef.child(ID),"getOne", User.class, new MyDataBase.JavaBeansCallback() 
				{	
					@Override
					public<T> void onCallback(List<T> javaBeansList)
					{		
						User user = (User)(javaBeansList.get(0));

						if(user != null)//如果user物件不為null，ID已經被使用
						{
							register_message = "所輸入的帳戶名稱已經有人使用，請輸入另一個帳戶名稱！";
							jspPageToForward = "addUserPage2.jsp";
						}
						else//ID還沒有被使用
						{
							//得到在Page1中輸入的資料
							String name  = (String)session.getAttribute("name_show");
							String sid   = (String)session.getAttribute("sid_show");
							String phone = (String)session.getAttribute("phone_show");
							String mail  = (String)session.getAttribute("mail_show");
							String edu   = (String)session.getAttribute("edu_show");
							String grade = (String)session.getAttribute("grade_show");
							
							//將使用者註冊資料存入newuser，並存到資料庫
							User newuser = SetToJavaBeans.addUser(name, sid, phone, mail, edu, grade, ID, PWD);
							MyDataBase.setToFirebase(userDataRef.child(ID), newuser);
							
							session.setAttribute("user", newuser);						
							register_message = "";
							jspPageToForward = "addUserPage3.jsp";
						}						
						latch.countDown();
					}
				});
				latch.await();
			}
			catch (Exception e) 
			{
				System.out.println("Exception");
			}

		}	
		
		else//不是以上情況，就設定導向註冊介面Page1
		{	
			jspPageToForward = "addUserPage1.jsp";
		}
		
		//將register_message物件setAttribute，註冊頁面要用到(使用者名稱被使用才會顯示字)
		request.setAttribute("register_message", register_message);
		request.getRequestDispatcher(jspPageToForward).forward(request, response);	
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}
}
