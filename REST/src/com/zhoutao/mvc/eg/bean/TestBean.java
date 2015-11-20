package com.zhoutao.mvc.eg.bean;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.SerializedCache;
import java.io.Serializable;

public class TestBean implements Serializable{

	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
