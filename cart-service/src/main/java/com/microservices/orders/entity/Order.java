package com.microservices.orders.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "order")
public class Order {

	@Id
	private String orderId;
	private int count;
	private String couponId;
	private String companyName;
    private int total;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Order(String orderId, int count, String couponId, String companyName, int total) {
		super();
		this.orderId = orderId;
		this.count = count;
		this.couponId = couponId;
		this.companyName = companyName;
		this.total = total;
	}
    
	@Override
    public String toString()
    {
    	return "[ the order id is: "+orderId
    			+"the no of coupons added: "+count
    	        +"the coupond id is :"+couponId
    	        +"the name of the company is: "+companyName
    	        +"total amount is: "+total+"]";
    		
    }

	


	
}
