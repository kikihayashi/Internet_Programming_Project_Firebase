package Controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.client.util.Data;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.CheckData;
import Model.InitCookies;
import Model.MyDataBase;
import JavaBeans.Sell;
import Model.SetToJavaBeans;
import JavaBeans.User;

/*
 * 這支UserSell程式負責處理使用者上架的工作
 */
@WebServlet("/Sell")
public class UserSell extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private String login_page = null;
	private String login_set = null;
	private String login_state = null;
	private String message = "";
	private String PageToForward = null;
	private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
	private DatabaseReference sellDataRef = rootRef.child("NTU_BS").child("SellData");

	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");//設定請求編碼為UTF-8 
		response.setCharacterEncoding("UTF-8");//設定回應編碼為UTF-8 

		HttpSession session = request.getSession();//利用getSession()方法取得HttpSession物件
		
		InitCookies.run(request);
		
		login_page = request.getParameter("page");		
		login_set = (String)session.getAttribute("isAuto");//得到自動登入狀態
		login_state = (String)session.getAttribute("state");//得到登入狀態

		if("上架商品".equals(login_page))//如果使用者已按下"上架商品"
		{	
			String sell_name = request.getParameter("name");
			String sell_cost = request.getParameter("cost");	
			String sell_number = request.getParameter("number");
			String sell_discription = request.getParameter("discription");
						
			//判斷單價金額與上架數量是否為正整數
			boolean sell_message = CheckData.checkSell(sell_number, sell_cost);			

			if(sell_message)//如果沒問題(true)
			{	
				User user = (User)session.getAttribute("user");
				//將使用者上架資料存入sell物件裡
				Sell sell = SetToJavaBeans.addSell(sell_name, sell_number.replaceFirst("^0*", ""), sell_discription,
														user.getAccountName(), sell_cost.replaceFirst("^0*", ""));	 

				//存入資料庫
				String sellFolder = user.getAccountName() + "_" + sell_name;
				MyDataBase.setToFirebase(sellDataRef.child(sellFolder), sell);
				message = "";
				PageToForward = "Home";//將使用者導向Home
			}			
			else//如果不是
			{
				message = "單價金額與上架數量必須是正整數";
				PageToForward = "sellpage1.jsp";//將使用者導向sellpage1.jsp			  
			}
		}
		else if("Logged_in".equals(login_state) || "Yes".equals(login_set))//第一次執行本程式會導向這裡
		{     
			PageToForward = "sellpage1.jsp";//將使用者導向sellpage1.jsp	
		}
		else//如果沒登入
		{
			PageToForward = "Home";//將使用者導向Home
		}
		
		request.setAttribute("message", message);
		request.getRequestDispatcher(PageToForward).forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
