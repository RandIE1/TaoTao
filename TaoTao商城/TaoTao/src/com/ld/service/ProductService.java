package com.ld.service;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld.bean.Order;
import com.ld.bean.OrderItem;
import com.ld.bean.PageBean;
import com.ld.bean.Product;
import com.ld.dao.ProductDao;
import com.ld.utils.DataSourceUtils;
@Service
public class ProductService {
	@Autowired
	ProductDao productdao;
	public List<Product> findHotProduct(int min,int max){
		int a = (min-1)*max;
		int b = min*max;
		return productdao.findHotProduct(a,b);
	}
	public List<Product> findNewProduct(int min,int max){
		int a = (min-1)*max;
		int b = min*max;
		return productdao.findNewProduct(a,b);
	}
	public PageBean<Product> findProductByCid(String cid,int currentPage,int currentCount){
		PageBean<Product> pagebean = new PageBean<Product>();

		int totalCount = 0;
		//封装当前页
		pagebean.setCurrentPage(currentPage);
		//封装每页显示条数
		pagebean.setCurrentCount(currentCount);
		//封装总条数
		totalCount = productdao.getCount(cid);
		pagebean.setTotalCont(totalCount);
		//封装总页数
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		pagebean.setTotalPage(totalPage);
		//当前页显示的数据
		int index = (currentPage-1)*currentCount;
		List<Product> list = productdao.findProductByPage(cid, index, currentCount);
		pagebean.setList(list);
		
		return pagebean;
	}
	public Product findProductByPid(String pid) {
		return productdao.findProductByPid(pid);
	}
	public void addOrderItem (Order order) {
		List<OrderItem> orderItems = order.getOrderItems();
		for(OrderItem item : orderItems) {
			productdao.addOrderItem(item);
		}
	}
	public void submitOrder(Order order) {
		try {
			DataSourceUtils.startTransaction();
			productdao.addOrders(order);
			addOrderItem(order);
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void updateOrder(Order order) {
		productdao.updateOrder(order);
	}
	public List<Order> findAllOrders(String uid){
		return productdao.findAllOrders(uid);
	}
	public List<OrderItem> findAllOrderItemByOid(String oid){
		return productdao.findAllOrderItemByOid(oid);
	}
	public Order findOrderByOid(String oid) {
		return productdao.findOrderByOid(oid);
	}
	
}
