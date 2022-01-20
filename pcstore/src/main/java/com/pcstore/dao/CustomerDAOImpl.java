package com.pcstore.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pcstore.entity.Customer;


@Transactional
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	SessionFactory factory;
	@Override
	public Customer findById(String id) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Customer entity = session.find(Customer.class, id);
		
		return entity;
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		String hql = "FROM Customer" ;
		Session session = factory.getCurrentSession();
		TypedQuery<Customer> query = session.createQuery(hql, Customer.class);
		List<Customer> list = query.getResultList();
		return list;
	}

	@Override
	public Customer create(Customer entity) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	public void update(Customer entity) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public Customer delete(String id) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Customer entity = session.find(Customer.class, id);
		session.delete(entity);
		return entity;
	}

}
