package com.zhoutao.mvc.auth.bean;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class AuthBean {
	
	@NotNull(message="userName is null")
	private String userName;
	@NotNull(message="passWord is null")
	private String passWord;
	private String role;
	private String tokenid;
	
	public String getTokenid() {
		return tokenid;
	}
	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
