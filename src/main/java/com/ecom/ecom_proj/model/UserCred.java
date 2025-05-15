package com.ecom.ecom_proj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserCred {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userid;
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public UserCred() {
		
	}
	public UserCred(int userid, String username, String password) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
	}
	
	
}
