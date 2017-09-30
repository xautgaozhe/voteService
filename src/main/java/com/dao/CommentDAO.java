package com.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.helpers.DBHelper;
import com.models.Comments;
import com.models.VComment;

public class CommentDAO {

	private DBHelper db =null;
	private Configuration cfg = null;
	private SessionFactory factory = null;
	private Session session = null;
	private Transaction t = null;
	public List<VComment> getComments() throws Exception{
		
		List<VComment> comList = new ArrayList<VComment>();
		db = new DBHelper();
		session = db.session;
		String sql ="from Comments order by comDate desc";
		Query que = db.session.createQuery(sql);
		List<Comments> coms = que.list();
		Iterator<Comments> it = coms.iterator();
		while(it.hasNext()){
			Comments cm = it.next();
			VComment vc = new VComment();
			vc.setUsername(cm.getUser().getUsername());
			vc.setDept(cm.getUser().getDept());
			vc.setComContent(cm.getComContent());
			vc.setComDate(cm.getComDate());
			comList.add(vc);
		}
		System.out.println(comList.toString());
		db.t.commit();
		db.session.close();
		return comList;
	}
	
	public boolean addComment(Comments comment) throws Exception{
		boolean flag = false;
		db = new DBHelper();
		session = db.sessionFactory.openSession();
		session.beginTransaction();
		session.save(comment);
		session.getTransaction().commit();
		session.close();
		return flag;
	}
}
