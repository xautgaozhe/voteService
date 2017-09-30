package com.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.helpers.DBHelper;
import com.models.User;

public class UserDAO {

	private DBHelper db =null;
	private Configuration cfg = null;
	private SessionFactory factory = null;
	private Session session = null;
	private Transaction t = null;
	private SessionFactory sessionFactory =null;
	public User getUserByAccount(String account) throws Exception{
		User user = new User();
		db = new DBHelper();
		session = db.sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("account", account));
		List<User> list =criteria.list();
		user = list.get(0);
		db.t.commit();
		session.close();
		
		/*User user = new User();
		db = new DBHelper();
		session = db.sessionFactory.openSession();
		session.beginTransaction();
		Query que = session.createQuery("from User u where u.account='"+account+"'");
		List<User> list = que.list();
		Iterator<User> itr = list.iterator();
		while(itr.hasNext()){
			user = itr.next();
		}
		session.getTransaction().commit();
		session.close();*/
		return user;
	}
}
