package com.pcstore.dao;

import java.util.List;

import com.pcstore.entity.Order;
import com.pcstore.entity.OrderDetail;
import com.pcstore.entity.Product;

public interface OrderDetailDAO {
	OrderDetail findById(int id);
	List<OrderDetail> findAll();
	OrderDetail create(OrderDetail entity);
	void update(OrderDetail entity);
	OrderDetail delete(int id);
	List<OrderDetail> findByOrder(Order order);
	List<OrderDetail> findByProduct(Product p);
}
