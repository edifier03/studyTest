package com.pubtools.sercurity.dao;

import com.pubtools.sercurity.bean.Resources;
import com.pubtools.sercurity.bean.Roles;
import com.pubtools.sercurity.bean.Users;


public class UsersDao {

	public Users findByName(String username) {
		Users users = new Users();
		if("admin".equals(username))
		{
			users.setAccount("admin");
//			users.setPassword("202CB962AC59075B964B07152D234B70");
			users.setPassword("123");
//			users.setPassword("202cb962ac59075b964b07152d234b70");
			Roles role1 = new Roles();
			
			Resources resource1 = new Resources();
			resource1.setName("ROLE_ADMIN");
			resource1.setUrl("/view/admin/*");
			role1.getResources().add(resource1);
			
			users.getRoles().add(role1);
		}
		return users;
	}
	
}
