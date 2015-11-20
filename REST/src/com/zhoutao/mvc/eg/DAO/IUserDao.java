package com.zhoutao.mvc.eg.DAO;

import com.zhoutao.mvc.eg.bean.TestBean;

public interface IUserDao {
	public int insert() throws Exception;
	public TestBean selDB(String id)throws Exception;
	public TestBean selDB2(String id)throws Exception;
}	
