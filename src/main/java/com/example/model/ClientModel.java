package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
public class ClientModel {

	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	private String clientName;
	
	private Date createdBy;
	
	private int status;
	

	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Date getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Date createdBy) {
		this.createdBy = createdBy;
	}
	
	
}
