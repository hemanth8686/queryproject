package com.example.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class QueryBean {

	private int Id;
	private String raisedDate;
	private String raisedBy;
	private String client;
	private String query;
	private String attentedBy;
	private String raisedThrough;
	private String completedDate;
	private String completedBy;
	private String status;
	
	private String Priority;
	
	
	
	
	public String getPriority() {
		return Priority;
	}
	public void setPriority(String priority) {
		Priority = priority;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	public String getRaisedBy() {
		return raisedBy;
	}
	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getAttentedBy() {
		return attentedBy;
	}
	public void setAttentedBy(String attentedBy) {
		this.attentedBy = attentedBy;
	}
	public String getRaisedThrough() {
		return raisedThrough;
	}
	public void setRaisedThrough(String raisedThrough) {
		this.raisedThrough = raisedThrough;
	}

	public String getCompletedBy() {
		return completedBy;
	}
	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRaisedDate() {
		return raisedDate;
	}
	public void setRaisedDate(String raisedDate) {
		this.raisedDate = raisedDate;
	}
	public String getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}
	
	
	
	
	

}
