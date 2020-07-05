package JavaBeans;

import java.io.Serializable;

/*
 * 此 Buy_Data物件為一JavaBean型態的Class, 用來儲存使用者資料.
 */
public class Buy implements Serializable{

	private String item_time;
	private String item_name;
	private String item_buy_number;
	private String item_seller;
	private String item_cost;
	
	public String getItem_time() {
		return item_time;
	}
	public void setItem_time(String item_time) {
		this.item_time = item_time;
	}	
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_buy_number() {
		return item_buy_number;
	}
	public void setItem_buy_number(String item_number) {
		this.item_buy_number = item_number;
	}	
	public String getItem_cost() {
		return item_cost;
	}
	public void setItem_cost(String item_cost) {
		this.item_cost = item_cost;
	}
	public String getItem_seller() {
		return item_seller;
	}
	public void setItem_seller(String item_seller) {
		this.item_seller = item_seller;
	}

}
