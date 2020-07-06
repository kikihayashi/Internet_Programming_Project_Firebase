package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaBeans.Sell;

import java.io.*;

import Model.DataUpdate;
import Model.InitCookies;
import Model.MyDataBase;

/*
 * 這支Home程式扮演Controller的角色，負責處理使用者進入首頁的工作
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final String DATABASE_URL = "https://user-database-2a086.firebaseio.com";		
	private MyDataBase mdb = new MyDataBase(DATABASE_URL);
	private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
	private DatabaseReference sellDataRef = rootRef.child("NTU_BS").child("SellData");

	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");//設定請求編碼為UTF-8 
		response.setCharacterEncoding("UTF-8");//設定回應編碼為UTF-8 

		HttpSession session = request.getSession();
		
		InitCookies.run(request);
		DataUpdate.refresh(sellDataRef, Sell.class, "sellList", session);
	
		request.getRequestDispatcher("buypage1.jsp").forward(request, response);
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
