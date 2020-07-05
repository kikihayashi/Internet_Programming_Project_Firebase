package Controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaBeans.Buy;
import JavaBeans.User;
import Model.CheckData;
import Model.DataUpdate;
import Model.MyDataBase;
import Model.SetToJavaBeans;

/*
 * 這支UserBuy程式負責處理使用者購買的工作
 */
@WebServlet("/Buy")
public class UserBuy extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String name = null;
	private String money = null;
	private String seller = null;
	private String login_page = null;
	private String jspPageToForward = null;//設定頁面導向設置
	private String message = "";//設定顯示試算金額訊息或結帳錯誤訊息
	private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
	private DatabaseReference sellDataRef = rootRef.child("NTU_BS").child("SellData");
	private DatabaseReference buyDataRef = rootRef.child("NTU_BS").child("BuyData");

	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");//設定請求編碼為UTF-8
		response.setCharacterEncoding("UTF-8");//設定回應編碼為UTF-8
		
		HttpSession session = request.getSession();//利用getSession()方法取得HttpSession物件
		
		login_page = request.getParameter("page");//得到使用者在buypage2點了哪個按鈕資訊
		String[] buy_number = request.getParameterValues("buy");//得到buypage2的buy字串陣列資訊	
		
		
		name = (String)session.getAttribute("name");//得到buypage2的money字串資訊
		money = (String)session.getAttribute("money");//得到buypage2的money字串資訊
		seller = (String)session.getAttribute("seller");//得到buypage2的money字串資訊

		if("金額試算".equals(login_page))
		{    
			String[] item_money = money.split(",");//利用逗號分開得到個別商品單價
			message = CheckData.checkMoney(buy_number, item_money);//計算總金額
			jspPageToForward = "buypage2.jsp";//將使用者導向buypage2.jsp				
		}
		else if("確定購買".equals(login_page))
		{       		
			if(CheckData.checkNumber(buy_number))//如果有買東西	
			{
				User user = (User)session.getAttribute("user");
				String[] item_name = name.split(",");
				String[] item_money = money.split(",");
				String[] item_seller = seller.split(",");

				Date date = new Date();				
				for(int i = 0; i < item_name.length; i++)
				{
					if(buy_number[i].equals("0"))
					{
						continue;
					}
					else
					{
						int cost = Integer.valueOf(item_money[i])*Integer.valueOf(buy_number[i]);
						Buy buy = SetToJavaBeans.addBuy(""+date, item_name[i], buy_number[i],""+cost ,item_seller[i]);
						MyDataBase.setToFirebase(buyDataRef.child(user.getAccountName()).child(date+"_"+item_name[i]), buy);
					}
				}
				
				DataUpdate.refresh(sellDataRef, item_seller, item_name, buy_number);
				DataUpdate.refresh(buyDataRef.child(user.getAccountName()), Buy.class, "buyList", session);
				message = CheckData.checkMoney(buy_number, item_money);//計算總金額 
				jspPageToForward = "buypage3.jsp";//將使用者導向buypage3.jsp	
			} 
			else//如果沒買東西
			{
				message = "請至少選擇一項商品購買";
				jspPageToForward = "buypage2.jsp";//將使用者導向buypage2.jsp	
			}				
		}
		else//第一次執行程式會導向這裡	
		{
			String[] choose = request.getParameterValues("choose");//得到choose字串陣列資訊
			session.setAttribute("choose", choose);//將該字串陣列資訊setAttribute，buypage2.jsp要用到
			message = (choose != null)? "" : "請至少選擇一樣商品！";
			jspPageToForward = (choose != null)? "buypage2.jsp" : "Home";
		}

		session.setAttribute("buy_number", buy_number);//這是要做之前購買數量記憶用所設置的，buypage2.jsp要用到
		request.setAttribute("message", message);//顯示試算金額訊息或結帳錯誤訊息
		request.getRequestDispatcher(jspPageToForward).forward(request, response);//導向jspPageToForward的地方
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
