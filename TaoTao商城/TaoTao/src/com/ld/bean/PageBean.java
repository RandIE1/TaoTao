package com.ld.bean;

import java.util.List;

public class PageBean<T> {
	private int currentPage;//当前页
	private int currentCount;//当前页显示条数
	private int totalCont;//总条数
	private int totalPage;//总页数
	private List<T> list;//当前页显示内容
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getTotalCont() {
		return totalCont;
	}
	public void setTotalCont(int totalCont) {
		this.totalCont = totalCont;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	

}
