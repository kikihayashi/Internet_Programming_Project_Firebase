package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 這支Logout程式扮演使用者登出的角色
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{				
		HttpSession session = request.getSession();//利用getSession()方法取得HttpSession物件   
 
		session.invalidate();//刪除session會話
				
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null)//刪除所有cookie
		{
			for(Cookie cookie : cookies)
			{
		        cookie.setMaxAge(0);//設定生存期為0 
		        response.addCookie(cookie);//設回Response中生效
		    }
		}
		response.sendRedirect("Home");
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
