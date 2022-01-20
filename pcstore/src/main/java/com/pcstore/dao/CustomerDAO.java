package com.pcstore.dao;

import java.util.List;

import com.pcstore.entity.Customer;

public interface CustomerDAO {
	Customer findById(String id);
	List<Customer> findAll();
	Customer create(Customer entity);
	void update(Customer entity);
	Customer delete(String id);
}
