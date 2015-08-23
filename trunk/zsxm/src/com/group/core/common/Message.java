package com.group.core.common;

import java.util.Map;

public class Message {
	private String code;

	private String text;
	
	private Map map ;

	public Message() {

	}

	public Message(String text) {
		this.text = text;
	}

	public Message(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map getMap() {
	    return map;
	}

	public void setMap(Map map) {
	    this.map = map;
	}
}
