package com.zhoutao.mvc.rest.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class AdvertiserParam {
	
	@NotNull(message="diy not empty")
	private String id;
	private String name;
	
	//自定义类型的验证
	
	@NotNull(message="tb not null")
	@Valid
	private TestBean tb;
	

	@Size(min=1)
	@Valid
	private List<TestListBean> tlb = new ArrayList<TestListBean>();
	public TestBean getTb() {
		return tb;
	}
	public void setTb(TestBean tb) {
		this.tb = tb;
	}
	public List<TestListBean> getTlb() {
		return tlb;
	}
	public void setTlb(List<TestListBean> tlb) {
		this.tlb = tlb;
	}
	
	
	
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
