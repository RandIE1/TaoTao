package com.ld.bean;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	//购物车中存放的诸多购物项
	private Map<String ,CartItem> cartItems = new HashMap<String ,CartItem>();
	//商品总计
	private double total;

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	

}
