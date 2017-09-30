package com.dao;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Session;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.Query;
import org.hibernate.type.StringType;

import com.helpers.DBHelper;
import com.models.Vote;

public class VoteDAO {

	private DBHelper db =null;
	private Session session = null;
	
	public List<Vote> getVoters(){
		List<Vote> vote_list = new ArrayList<Vote>();
		try {
			db = new DBHelper();
			String sql = "from Vote";
			session = db.sessionFactory.openSession();
			Query que =session.createQuery(sql);
			vote_list = que.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.session.close();
		}
		
		return vote_list;
	}
	
	public boolean updateCounts(String voterIds){
		try {
			db = new DBHelper();			
			ProcedureCall call = db.session.createStoredProcedureCall("updateCounts");
		    call.registerParameter(1,String.class,ParameterMode.IN).bindValue(voterIds);
		    call.executeUpdate();
		    db.t.commit();
		    return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			db.session.close();
		}
		
	}
	
	public List<Vote> getVoterProfile(String vId){
		List<Vote> vote_list = new ArrayList<Vote>();
		try {
			db = new DBHelper();
			session = db.session;
			vote_list = session.createQuery("select v from Vote v where v.voterId=:vId")
			.setParameter("vId", vId,StringType.INSTANCE)
			.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			session.close();
		}
		return vote_list;
	}
}
