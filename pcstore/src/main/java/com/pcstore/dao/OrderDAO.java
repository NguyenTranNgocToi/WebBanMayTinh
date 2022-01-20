package com.pcstore.dao;

import java.util.List;

import com.pcstore.entity.Customer;
import com.pcstore.entity.Order;
import com.pcstore.entity.OrderDetail;
import com.pcstore.entity.Product;

public interface OrderDAO {
	Order findById(int id);
	List<Order> findAll();
	Order create(Order entity);
	void update(Order entity);
	Order delete(int id);
	void create(Order order, List<OrderDetail> details);
	List<Order> findByUser(Customer customer);
	List<Product> finditemsByUser(Customer customer);
	List<Order> findByDetail(OrderDetail d);
}
