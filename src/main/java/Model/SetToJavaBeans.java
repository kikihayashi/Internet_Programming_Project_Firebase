package Model;

import JavaBeans.User;
import JavaBeans.Sell;
import JavaBeans.Buy;

public class SetToJavaBeans {

	public static User addUser(String name, String sid,String phone, String mail, 
									String education, String grade, String accountName, String password) 
	{
		User user = new User();
		user.setName(name);
		user.setSid(sid);
		user.setPhone(phone);
		user.setMail(mail);
		user.setEdu(education);
		user.setGrade(grade);
		user.setAccountName(accountName);
		user.setPassword(password);

		//回傳User物件
		return user;
	}

	public static Buy addBuy(String item_time, String item_name, String item_buy_number, 
								String item_cost, String item_seller)
	{
		Buy buy = new Buy();
		buy.setItem_time(item_time);
		buy.setItem_name(item_name);
		buy.setItem_buy_number(item_buy_number);
		buy.setItem_cost(item_cost);
		buy.setItem_seller(item_seller);
		
		//回傳Buy物件
		return buy;
	}

	public static Sell addSell(String item_name, String item_sell_number, String item_discription,
									String item_seller, String item_cost)
	{
		Sell sell = new Sell();
		sell.setItem_name(item_name);
		sell.setItem_sell_number(item_sell_number);
		sell.setItem_discription(item_discription);
		sell.setItem_seller(item_seller);
		sell.setItem_cost(item_cost);

		//回傳Sell物件
		return sell;
	}
}
