package Model;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaBeans.Buy;
import JavaBeans.User;

public class CheckCookies {
		
	private String login_message;
	private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
	private DatabaseReference userDataRef = rootRef.child("NTU_BS").child("UserData");
	private DatabaseReference buyDataRef = rootRef.child("NTU_BS").child("BuyData");
	
	public CheckCookies(Cookie[] inputcookies, HttpSession inputsession)
	{
		//得到瀏覽器的cookie資料
		String now_ID = getCookieData("ID", inputcookies);
		String now_PWD = getCookieData("PWD", inputcookies);

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
					if(inputuser.getAccountName() != null && inputuser.getPassword().equals(now_PWD))
					{		
						login_message = CheckData.checkPassword(now_ID, now_PWD, inputuser);	
						inputsession.setAttribute("user", inputuser);//將得到的user_value，setAttribute	
					}
					latch.countDown();//讓CountDownLatch設定的數值減掉1
				}
			});	
			latch.await();//等CountDownLatch設定的數值變成0時，主程式才會繼續執行下面的程式	
			DataUpdate.refresh(buyDataRef.child(now_ID), Buy.class, "buyList", inputsession);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	
	public String getCookieMessage()
	{			
		return login_message;
	}
		
	private String getCookieData(String str, Cookie[] cookies)
	{
		String userData = "";
		if(cookies!=null)
		{
			for (Cookie cookie : cookies)
			{
				String name = cookie.getName();
				String value = cookie.getValue();

				if (str.equals(name))
				{		
					userData = value;
					break;
				}
			}		
		}
		return userData;
	}
	
}
