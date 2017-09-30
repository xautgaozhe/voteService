package com.services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.dao.UserDAO;
import com.google.gson.Gson;
import com.models.User;


@Path("/UserService")
public class UserService {
	@GET
	@Path("/login/{account}")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkLogin(@PathParam("account") String account){
		UserDAO ud = new UserDAO();
		User user = new User();
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<String, String>();
		String status = null;
		try {
			user = ud.getUserByAccount(account);
			if(user==null){
				status = "noexist";
			}else{
				status = "exist";
			}
			map.put("status", status);
			status = gson.toJson(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
}
