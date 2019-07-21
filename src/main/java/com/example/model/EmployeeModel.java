package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class EmployeeModel {
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	
	private String employeeName;
	private String employeeMail;
	private String mailType;
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
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeMail() {
		return employeeMail;
	}
	public void setEmployeeMail(String employeeMail) {
		this.employeeMail = employeeMail;
	}
	public String getMailType() {
		return mailType;
	}
	public void setMailType(String mailType) {
		this.mailType = mailType;
	}
	
	
	

}
