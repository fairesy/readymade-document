package com.readymade.model;

public class Module {
	private Integer id;
	private String type;
	private String data; //json
	private Integer document_id;
	
	public Module(String type, String data, Integer document_id) {
		this.type = type;
		this.data = data;
		this.document_id = document_id;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getDocument_id() {
		return document_id;
	}
	public void setDocument_id(Integer document_id) {
		this.document_id = document_id;
	}
	
}
