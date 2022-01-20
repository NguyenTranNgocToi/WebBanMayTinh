package com.pcstore.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pcstore.entity.Customer;
import com.pcstore.entity.Order;
import com.pcstore.entity.OrderDetail;
import com.pcstore.entity.Product;


@Transactional
@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	SessionFactory factory;
	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Order entity = session.find(Order.class, id);
		
		return entity;
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		String hql = "FROM Order" ;
		Session session = factory.getCurrentSession();
		TypedQuery<Order> query = session.createQuery(hql, Order.class);
		List<Order> list = query.getResultList();
		return list;
	}

	@Override
	public Order create(Order entity) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	public void update(Order entity) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public Order delete(int id) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Order entity = session.find(Order.class, id);
		session.delete(entity);
		return entity;
	}

	@Override
	public void create(Order order, List<OrderDetail> details) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		
		order.setOrderDetails(details);
		session.save(order);
		for(OrderDetail o:details) {
			session.save(o);
		}
	}

	@Override
	public List<Order> findByUser(Customer customer) {
		// TODO Auto-generated method stub
		String hql = "FROM Order o WHERE o.customer.id=:uid ORDER BY o.orderDate DESC" ;
		Session session = factory.getCurrentSession();
		TypedQuery<Order> query = session.createQuery(hql, Order.class);
		query.setParameter("uid", customer.getId());
		List<Order> list = query.getResultList();
		return list;
	}

	@Override
	public List<Product> finditemsByUser(Customer customer) {
		// TODO Auto-generated method stub
		String hql = "SELECT DISTINCT d.product FROM OrderDetail d WHERE d.order.customer.id=:uid" ;
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		query.setParameter("uid", customer.getId());
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public List<Order> findByDetail(OrderDetail d) {
		// TODO Auto-generated method stub
		String hql = "FROM Order o WHERE o.orderDetail.id=:uid" ;
		Session session = factory.getCurrentSession();
		TypedQuery<Order> query = session.createQuery(hql, Order.class);
		query.setParameter("uid", d.getId());
		List<Order> list = query.getResultList();
		return list;
	}

}
