package com.zhoutao.mongodb.DAO.impl;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.WriteResult;
import com.zhoutao.mongodb.DAO.MongoDAOIA;
import com.zhoutao.mongodb.connect.MongoManager;

public class MongoDAOImpl implements MongoDAOIA{
		public static final String DBNAME = "test";
		public static final String USERNAME = "test";
		public static final String PASSWORD = "123";
		
		
	 	@Override
	    public boolean insert(String collectionName, BasicDBObject bean) {
	        DB db = MongoManager.getDB(this.DBNAME,this.USERNAME,this.PASSWORD);
	        WriteResult result = db.getCollection(collectionName).insert(bean);
	        if(result.getN()!=1)
	        {
	        	return false;
	        }
	        return true;
	    }
	 
	    @Override
	    public boolean delete(String collectionName, BasicDBObject bean) {
	    	 DB db = MongoManager.getDB(this.DBNAME,this.USERNAME,this.PASSWORD);
	        db.getCollection(collectionName).remove(bean);
	        return false;
	    }
	 
	    @Override
	    public List find(String collectionName, BasicDBObject bean) {
	    	 DB db = MongoManager.getDB(this.DBNAME,this.USERNAME,this.PASSWORD);
	        List list = db.getCollection(collectionName).find(bean).toArray();
	        return list ;
	    }
	    @Override
	    public List find(String collectionName) {
	    	 DB db = MongoManager.getDB(this.DBNAME,this.USERNAME,this.PASSWORD);
	        List list = db.getCollection(collectionName).find().toArray();
	        return list ;
	    }
	 
	    @Override
	    public boolean update(String collectionName, BasicDBObject oldBean, BasicDBObject newBean) {
	    	 DB db = MongoManager.getDB(this.DBNAME,this.USERNAME,this.PASSWORD);
	        db.getCollection(collectionName).update(oldBean, newBean);
	        return false;
	    }

}
