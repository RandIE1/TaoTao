package com.ld.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.ld.bean.Category;
import com.ld.bean.Order;
import com.ld.bean.OrderItem;
import com.ld.bean.PageBean;
import com.ld.bean.Product;

public interface ProductDao {
	@Autowired
	List<Product> findHotProduct(@Param("min")int min,@Param("max")int max);//获得热门商品
	List<Product> findNewProduct(@Param("min")int min,@Param("max")int max);//获得最新商品
	int getCount(String cid);
	List<Product> findProductByPage(@Param("cid")String cid,@Param("index")int index,@Param("currentCount")int currentCount);
	Product findProductByPid(String pid);
	void addOrders(Order order);
	void addOrderItem(OrderItem order);
	void updateOrder(Order order);
	List<Order> findAllOrders(String uid);
	Order findOrderByOid(String oid);
	List<OrderItem> findAllOrderItemByOid(String oid);
}

