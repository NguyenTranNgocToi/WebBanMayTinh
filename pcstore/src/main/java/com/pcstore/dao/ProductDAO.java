package com.pcstore.dao;

import java.util.List;

import com.pcstore.entity.Category;
import com.pcstore.entity.Product;

public interface ProductDAO {
	Product findById(int id);
	List<Product> findAll();
	Product create(Product entity);
	void update(Product entity);
	Product delete(int id);
	List<Product> findByKeyWords(String keywords);
	List<Product> findByIds(String ids);
	List<Product> findBySpecial(int id);
	List<Product> findByCategory(Category findById);


}
