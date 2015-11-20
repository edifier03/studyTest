package com.zhoutao.mvc.eg.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zhoutao.mvc.eg.bean.TestBean;
@Repository 
public class UserDaoImpl extends SqlSessionDaoSupport implements IUserDao
{
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	@Override
	public int insert() throws Exception {
		 TestBean bean = new TestBean();
		 bean.setId("1");
		 bean.setName("2");
		 this.getSqlSession().insert("com.mvc.test2.doUpd",bean );
		 return 0;
	}
	@Override
	public TestBean selDB(String id) throws Exception {
		TestBean bean = this.getSqlSession().selectOne("com.mvc.test.selectList", id);
//		 List<TestBean> beanlist = this.getSqlSession().selectOne("com.mvc.test.selectList", id);
		return bean;
	}
	
	@Override
	public TestBean selDB2(String id) throws Exception {
		TestBean bean = this.getSqlSession().selectOne("com.mvc.test.selectList2", id);
//		 List<TestBean> beanlist = this.getSqlSession().selectOne("com.mvc.test.selectList", id);
		return bean;
	}

}
