package com.models;

public class VComment {


	private String comContent;
	private String comDate;
    private String username;
    private String dept;
	public VComment() {
	}

	public VComment(String username,String dept, String comContent, String comDate) {
		
		this.comContent = comContent;
		this.comDate = comDate;
		this.username = username;
		this.dept = dept;
	}

	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getDept(){
		return this.dept;
	}
	public void setDept(String dept){
		this.dept = dept;
	}
	
	public String getComContent() {
		return this.comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public String getComDate() {
		return this.comDate;
	}

	public void setComDate(String comDate) {
		this.comDate = comDate;
	}

}
