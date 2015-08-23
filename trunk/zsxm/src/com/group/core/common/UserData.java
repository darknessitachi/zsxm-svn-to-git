package com.group.core.common;

public class UserData {
	private String name;
	private String content;
	
	public UserData(){}
	public UserData(String n,String c){
		this.name = n;
		this.content = c;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
