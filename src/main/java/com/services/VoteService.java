package com.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dao.VoteDAO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.models.Vote;

@Path("/VoterService")
public class VoteService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getVoters(){
		List<Vote> vote_list = new ArrayList<Vote>();
		Gson gson = new Gson();
		VoteDAO vd = new VoteDAO();
		vote_list = vd.getVoters();
		List<String> jsonList = new ArrayList<String>();
		for(Vote v : vote_list){
			jsonList.add(gson.toJson(v));
		}
		int size= vote_list.size();
		int length =0;
		if(size%2==0){
			length = size/2;
		}else{
			length = size/2+1;
		}
		Vote[][] v1 = new Vote[length][2];
		if(size%2==0){
			for(int i =0;i<length;i++){
				for(int j=0;j<2;j++){
						v1[i][j]= vote_list.get(i*2+j);
				}
			}
		}else{
			for(int i =0;i<length;i++){
				for(int j=0;j<2;j++){
					if((i*2+j)<size)
						v1[i][j]= vote_list.get(i*2+j);
				}
			}
		}
		
		return gson.toJson(v1);
	}
	
	@POST
	@Path("updateCounts")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCount(@FormParam("Ct") String Ct){		
		boolean flag = false;
		Gson gson = new Gson();
		VoteDAO vd = new VoteDAO();
		StringBuilder sb= new StringBuilder("");
		java.lang.reflect.Type type = new TypeToken<HashMap<String,String>>(){
		}.getType();
		Map<String,String> map = gson.fromJson(Ct, type);
		for(String item: map.values()){
			
			sb.append(item);
			sb.append(",");
		}
		String str = "1,2,3";
		flag = vd.updateCounts(str);
		if(flag){
			map.put("status", "success");
		}else{
			map.put("status", "failure");
		}
		return gson.toJson(map);
	}
	
	@GET
	@Path("profile/{voterID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getVoterProfile(@PathParam("voterID") String voterID){
		System.out.println(voterID);
		System.out.println("dddd");
		List<Vote> vote_list = new ArrayList<Vote>();
		VoteDAO vd = new VoteDAO();
		Gson gson = new Gson();
		vote_list = vd.getVoterProfile(voterID);
		System.out.println(vote_list.size());		
		String profile = vote_list.get(0).getVProfile();
		Map<String,String> map = new HashMap<String,String>();
		map.put("profile", profile);
		String str = gson.toJson(map);		
		return str;
	}
}
