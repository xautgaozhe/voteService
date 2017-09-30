package com.helpers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DBHelper {

	public Configuration cfg = null;
	public static SessionFactory factory = null;
	public Session session = null;
	public Transaction t = null;
	public SessionFactory sessionFactory = null;
	public DBHelper() throws Exception{
		//creating configuration object  
	    cfg = new Configuration();
	    cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
	    //creating seession factory object  
	    factory=cfg.buildSessionFactory();  
	    //creating session object  
	    session=factory.openSession();  
	    //creating transaction object  
	    t=session.beginTransaction(); 
	    setUp();
	}
	
	protected void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}
	 
}
