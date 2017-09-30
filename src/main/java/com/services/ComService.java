package com.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.dao.CommentDAO;
import com.dao.UserDAO;
import com.google.gson.Gson;
import com.models.Comments;
import com.models.User;
import com.models.VComment;

@Path("/CommentService")
public class ComService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<VComment> getComments() throws Exception{
		List<VComment> comList = new ArrayList<VComment>();
		CommentDAO cd = new CommentDAO();
		comList = cd.getComments();
		return comList;
	}
	
	
	@Path("/addCm")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String addComment(@FormParam("account") String account, @FormParam("comContent") String comContent) throws Exception{
		Map<String, String> map = new HashMap<String,String>();
		System.out.println(account);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = new Gson();
			CommentDAO cd = new CommentDAO();
		UserDAO ud = new UserDAO();
		User user = new User();
		Comments cm = new Comments();
		user = ud.getUserByAccount(account);  //根据用户名获取相应的实体对象
	    String date = sdf.format(new Date());
		cm.setComContent(comContent);
		cm.setComDate(date);
		cm.setUser(user);
		cd.addComment(cm);    //将评论对象保存到数据库
		map.put("status", "success");
		String json = gson.toJson(map);
		System.out.println("dd");
		return json;
	}
}
