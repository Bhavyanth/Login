package com.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "admin_details")

public class AdminDetail {
	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
    @Column(name="admin_id")  
	private int adminId;
	
	@Column(name="name")  
	private String name;
	
	@Column (name="email", unique = true)
	private String emailId;

	@Column(name="password")  
	private String password;
	
	@Column(name="role")  
	private String role;
	
	public AdminDetail(){}
	
	public AdminDetail(int adminId, String name, String emailId, String password, String role) {
		super();
		this.adminId = adminId;
		this.name = name;
		this.emailId = emailId;
		this.password = password;
		this.role = role;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "AdminDetail [adminId=" + adminId + ", name=" + name + ", emailId=" + emailId + ", password=" + password
				+ ", role=" + role + "]";
	}
	
	
	
}
