package com.pcstore.dao;

import java.util.List;

import com.pcstore.entity.Category;


public interface CategoryDAO {
	Category findById(int id);
	List<Category> findAll();
	Category create(Category entity);
	void update(Category entity);
	Category delete(int id);


}
