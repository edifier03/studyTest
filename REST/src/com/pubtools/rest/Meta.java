package com.pubtools.rest;

public class Meta {
	private boolean success;
	private String message;
	public Meta(boolean success)
	{
		this.success = success;
	}
	
	public Meta(boolean success,String message)
	{
		this.success = success;
		this.message = message;
	}
	
	public boolean isSuccess(){
		return this.success;
	}
	
	public String getMessage(){
		return this.message;
	}
}
