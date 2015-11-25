package com.zhoutao.mvc.eg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhoutao.mvc.eg.DAO.IUserDao;
import com.zhoutao.mvc.eg.bean.TestBean;
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao userDao;
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public boolean traInsert(String usr) throws Exception  {
		int result = userDao.insert();
//		int result2 = userDao.insert();
		if("abc".equals(usr))
		{
			throw new Exception("rollback");
		}
		return false;
	}
	@Override
	public void selDB(String id) {
		try {
			TestBean bean = userDao.selDB(id);
			System.out.println(bean.getName());
			
			TestBean bean2 = userDao.selDB2(id);
			System.out.println(bean2.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
