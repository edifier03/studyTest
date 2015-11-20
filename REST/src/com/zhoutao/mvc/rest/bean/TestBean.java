package com.zhoutao.mvc.rest.bean;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
@Entity
public class TestBean {
	@NotNull(message="tb.mark not null")
	private String mark;

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
}
