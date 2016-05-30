package com.readymade.model;

public class Document {
	private Integer id;
	private String type;
	private Integer user_id;

	public Document(String type, Integer user_id) {
		this.type = type;
		this.user_id = user_id;
	}
	public Document(int id, String type, int user_id) {
		this.id = id;
		this.type = type;
		this.user_id = user_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}
