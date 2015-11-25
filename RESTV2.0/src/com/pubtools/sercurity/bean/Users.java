package com.pubtools.sercurity.bean;

import java.util.HashSet;
import java.util.Set;

public class Users {
	private String account;
	private String password;
	private Set<Roles> roles = new HashSet<Roles>();
	public Set<Roles> getRoles() {
		return roles;
	}
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
