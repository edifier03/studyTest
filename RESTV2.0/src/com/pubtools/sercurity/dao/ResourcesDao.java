package com.pubtools.sercurity.dao;

import java.util.ArrayList;
import java.util.List;

import com.pubtools.sercurity.bean.Resources;

public class ResourcesDao {

	public List<Resources> findAll()
	{
		List<Resources> list = new ArrayList<Resources>();
		Resources res1 = new Resources();
		res1.setName("ROLE_ADMIN");
//		res1.setUrl("/view/admin/*");
		res1.setUrl("/security/advertiser");
		
		
		list.add(res1);
		return list;
		
	}

}
