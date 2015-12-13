package com.zhoutao.mongodb.DAO;

import java.util.List;

import com.mongodb.BasicDBObject;

public interface MongoDAOIA {
	public boolean insert(String collectionName, BasicDBObject bean);
    
    public boolean delete(String collectionName, BasicDBObject bean);
     
    public List find(String collectionName, BasicDBObject bean);
    
    public List find(String collectionName) ;
    
    public boolean update(String collectionName, BasicDBObject oldBean, BasicDBObject newBean);
}
