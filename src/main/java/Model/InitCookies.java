package Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class InitCookies {
	
	InitCookies(){}
	
	public static void run(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();

		if(cookies != null)
		{
			CheckCookies checkCookies = new CheckCookies(cookies, session);
			
			if("Yes_PWD".equals(checkCookies.getCookieMessage()))
			{	
				session.setAttribute("isAuto", "Yes");
			}
		}
	}
}
