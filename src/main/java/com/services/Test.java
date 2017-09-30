package com.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.models.Comments;
import com.models.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 //creating configuration object  
        Configuration cfg=new Configuration();  
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  

        //creating seession factory object  
        SessionFactory factory=cfg.buildSessionFactory();  

        //creating session object  
        Session session=factory.openSession();  

        //creating transaction object  
        Transaction t=session.beginTransaction();  
        User user = new User();
        Query que =session.createQuery("from User u where u.account = '17792821068'");
		List<User> coms = que.list();
		Iterator<User> it = coms.iterator();
		while(it.hasNext()){
			User user1 = it.next();
			System.out.println(user1.getDept());
		}
		
		
        t.commit();//transaction is committed  
        session.close();
	}

}
