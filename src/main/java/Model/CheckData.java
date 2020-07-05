package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import JavaBeans.User;

public class CheckData {
	
	public static String checkPassword(String inputAccountName, String inputPassword, User inputuser) 
	{		
		// 如果物件為空值，此情況發生在沒有人註冊過，且沒填帳號或密碼，按送出時發生
		if (inputuser == null)
			return "No_ID";
				
		// 此情況發生在沒填帳號，或沒填密碼，按送出時發生
	    if(inputAccountName.equals("") || inputPassword.equals(""))	
			return "Nothing";			
											
		// 如果系統內,已經有此使用者的帳號名稱
	    if(inputAccountName.equals(inputuser.getAccountName()))
	    {		
	    	//如果密碼正確的話, 傳回Yes_PWD；不正確的話, 傳回No_PWD
	    	return (inputPassword.equals(inputuser.getPassword())) ? "Yes_PWD" : "No_PWD";
	    }					
	    // 如果系統內,尚未有此使用者的帳號名稱
	    else
	    {	
	    	return "No_ID";
	    }		
	}
	
	public static String checkMoney(String[] buy_number,String[] item_money)
	{
        //先令總價格為0
        int total_money = 0;
        
        //利用迴圈算出總金額
        for(int i = 0; i < buy_number.length ;i++)
        {
     	   total_money += (Integer.parseInt(item_money[i]))*(Integer.parseInt(buy_number[i]));		
        }         
        //將總金額轉為字串形式
        String message = "總計："+ total_money +"元";
        //回傳總金額
        return message;
	}	        	
		
	public static boolean checkNumber(String[] buy_number_memory)
	{
		//先令總數量為0
		int total_number = 0;
		
		//利用迴圈算出總購買數量
		for(int i = 0; i < buy_number_memory.length; i++)
		{
			total_number += (Integer.parseInt(buy_number_memory[i]));		
		}
		return (total_number == 0)? false : true;   
	}
	
	public static boolean checkSell(String item_sell_number, String item_cost)
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher sell_number_isNum = pattern.matcher(item_sell_number);
		Matcher item_cost_isNum = pattern.matcher(item_cost);
		
		if  (!sell_number_isNum.matches()||!item_cost_isNum.matches())
		{
			return false;
		}	
		else
		{	
			return (Integer.parseInt(item_sell_number) <= 0 || Integer.parseInt(item_cost) <= 0)
					? false : true;
		}
	}
}
