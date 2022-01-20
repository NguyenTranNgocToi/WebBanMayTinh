package com.pcstore.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pcstore.entity.Category;
import com.pcstore.entity.Product;


@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	SessionFactory factory;
	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Product entity = session.find(Product.class, id);
		
		return entity;
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		String hql = "FROM Product" ;
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public Product create(Product entity) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	public void update(Product entity) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public Product delete(int id) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Product entity = session.find(Product.class, id);
		session.delete(entity);
		return entity;
	}

	@Override
	public List<Product> findByKeyWords(String keywords) {
		String hql = "FROM Product p WHERE p.name LIKE :kw "
				+ "OR p.category.name LIKE:kw OR p.category.nameVN LIKE:kw " ;
//		String hql = "FROM Product p WHERE p.name LIKE :kw " ;
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		query.setParameter("kw","%"+keywords+"%" );
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public List<Product> findByIds(String ids) {
		String hql = "FROM Product p WHERE p.id in("+ids+")";
	
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);

		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public List<Product> findBySpecial(int id) {
		String hql = "FROM Product";
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		switch (id) {
		case 0://mới
			hql = "FROM Product p ORDER BY p.productDate";
			break;
		case 1://bán chạy
			hql = "FROM Product p ORDER BY size(p.orderDetails) DESC";
			break;
		case 2://xem nhiều
			hql = "FROM Product p ORDER BY p.viewCount DESC";
			break;
		case 3://giảm giá
			hql = "FROM Product p ORDER BY p.discount DESC";
			break;
		}
		
		query = session.createQuery(hql, Product.class);
		query.setMaxResults(12);
		
	
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public List<Product> findByCategory(Category findById) {
		// TODO Auto-generated method stub
		String hql = "FROM Product p WHERE p.category.id=:oid";
		
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		query.setParameter("oid", findById.getId());
		List<Product> list = query.getResultList();
		return list;
	}
	
	

}
