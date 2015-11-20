package com.zhoutao.mvc.rest.bean;

import javax.validation.constraints.NotNull;

public class TestListBean {
	@NotNull(message="tlb child not null")
	private String child;

	public String getChild() {
		return child;
	}

	public void setChild(String child) {
		this.child = child;
	}
}
