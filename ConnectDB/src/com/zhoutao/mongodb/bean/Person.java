package com.zhoutao.mongodb.bean;

import java.util.Iterator;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class Person {
	public static void main(String[] args)
	{
		 try{
			Mongo m = new Mongo("localhost", 27017);
	        DB db = m.getDB("test");
	        //校验用户密码是否正确  
            if (!db.authenticate("test", "123".toCharArray())){  
                System.out.println("连接MongoDB数据库,校验失败！");  
            }else{  
                System.out.println("连接MongoDB数据库,校验成功！");  
                  
                db.requestStart();  
                //获取集合名称  
                Set<String> colNameSet = db.getCollectionNames();  
                Iterator<String> colNameItr = colNameSet.iterator();  
                while(colNameItr.hasNext()){  
                    String colName = colNameItr.next();  
                    System.out.println("数据集合的名称："+colName);  
                }  
                //获取集合对象  
                DBCollection dbCol = db.getCollection("ADMIN1352528239201");  
                if(dbCol != null){  
                    //获取集合大小  
                    Integer colSize = (Integer) dbCol.getStats().get("size");  
                    System.out.println("数据集大小为[单位：B]："+colSize);  
                }  
                db.requestDone();  
            }
		 }catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }
	}
}
