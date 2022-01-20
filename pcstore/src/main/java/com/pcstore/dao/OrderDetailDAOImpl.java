package com.pcstore.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pcstore.entity.Order;
import com.pcstore.entity.OrderDetail;
import com.pcstore.entity.Product;


@Transactional
@Repository
public class OrderDetailDAOImpl implements OrderDetailDAO {

	@Autowired
	SessionFactory factory;
	@Override
	public OrderDetail findById(int id) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		OrderDetail entity = session.find(OrderDetail.class, id);
		
		return entity;
	}

	@Override
	public List<OrderDetail> findAll() {
		// TODO Auto-generated method stub
		String hql = "FROM OrderDetail" ;
		Session session = factory.getCurrentSession();
		TypedQuery<OrderDetail> query = session.createQuery(hql, OrderDetail.class);
		List<OrderDetail> list = query.getResultList();
		return list;
	}

	@Override
	public OrderDetail create(OrderDetail entity) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	public void update(OrderDetail entity) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public OrderDetail delete(int id) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		OrderDetail entity = session.find(OrderDetail.class, id);
		session.delete(entity);
		return entity;
	}

	@Override
	public List<OrderDetail> findByOrder(Order order) {
		// TODO Auto-generated method stub
		
		String hql = "FROM OrderDetail d WHERE d.order.id=:oid" ;
		Session session = factory.getCurrentSession();
		TypedQuery<OrderDetail> query = session.createQuery(hql, OrderDetail.class);
		query.setParameter("oid", order.getId());
		List<OrderDetail> list = query.getResultList();
		return list;
	}

	@Override
	public List<OrderDetail> findByProduct(Product p) {
		// TODO Auto-generated method stub
		String hql = "FROM OrderDetail d WHERE d.product.id=:oid" ;
		Session session = factory.getCurrentSession();
		TypedQuery<OrderDetail> query = session.createQuery(hql, OrderDetail.class);
		query.setParameter("oid", p.getId());
		List<OrderDetail> list = query.getResultList();
		return list;
	}

}
