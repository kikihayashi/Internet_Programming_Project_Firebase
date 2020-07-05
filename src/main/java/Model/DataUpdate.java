package Model;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpSession;

import com.google.firebase.database.DatabaseReference;

import JavaBeans.Sell;

public class DataUpdate {
	
	DataUpdate(){}
	
	public static <T> void refresh(DatabaseReference dataRef, Class<T> clazz, String setName, HttpSession session)
	{
		//因為MyDataBase.getFromFirebase是非同步，使用CountDownLatch指定主程式需等待1個執行緒	
		final CountDownLatch latch = new CountDownLatch(1);
		try
		{
			//從DataBase中取資料
			MyDataBase.getFromFirebase(dataRef, "getAll", clazz, new MyDataBase.JavaBeansCallback()
			{
				@Override
				public <T> void onCallback(List<T> javaBeansList)
				{
					session.setAttribute(setName, javaBeansList);//將得到的javaBeansList，setAttribute
					latch.countDown();//讓CountDownLatch設定的數值減掉1
				}
			});
			latch.await();//等CountDownLatch設定的數值變成0時，主程式才會繼續執行下面的程式}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}	

	public static void refresh(DatabaseReference sellDataRef, String[] sellers, String[] names, String[] numbers)
	{	
		String[] folders = new String[sellers.length];
		
		for(int i = 0; i < sellers.length; i++)
		{	
			folders[i] = sellers[i] + "_" + names[i];
			final int fi = i;
			
			//從DataBase中取資料
			MyDataBase.getFromFirebase(sellDataRef.child(folders[fi]), "getOne", Sell.class, new MyDataBase.JavaBeansCallback()
			{
				@Override
				public <T> void onCallback(List<T> javaBeansList)
				{
					Sell sell = (Sell)(javaBeansList.get(0));

					int sell_number = Integer.valueOf(sell.getItem_sell_number())-Integer.valueOf(numbers[fi]);

					if(sell_number > 0)
					{
						DatabaseReference updateRef = sellDataRef.child(folders[fi]).child("item_sell_number");
						MyDataBase.setToFirebase(updateRef, sell_number + "");
					}
					else
					{
						MyDataBase.removeFromFirebase(sellDataRef.child(folders[fi]));
					}
				}
			});	
		}
	} 
}
