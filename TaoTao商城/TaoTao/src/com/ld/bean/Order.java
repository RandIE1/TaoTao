package com.ld.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	/*
	 * `oid` varchar(32) NOT NULL,
  `ordertime` datetime DEFAULT NULL,
  `total` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `uid` varchar(32) DEFAULT NULL,
	 */
	private String oid;//该订单的订单号
	private Date ordertime;//下单时间
	private double total;//该订单总金额
	private int state;//订单支付状态 1代表已付款0代表未付款
	private String address;//收货地址
	private String name;//收货人
	private String telephone;//收货人电话
	private User user;//该订单属于哪个用户
	
	//该订单中有多少订单项
	List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Order(String oid, String name, String telephone, User user) {
		super();
		this.oid = oid;
		this.name = name;
		this.telephone = telephone;
		this.user = user;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(String oid, Date ordertime, double total, int state, String address, String name, String telephone,
			User user, List<OrderItem> orderItems) {
		super();
		this.oid = oid;
		this.ordertime = ordertime;
		this.total = total;
		this.state = state;
		this.address = address;
		this.name = name;
		this.telephone = telephone;
		this.user = user;
		this.orderItems = orderItems;
	}
	
	
	 

}
