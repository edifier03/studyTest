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
	        //У���û������Ƿ���ȷ  
            if (!db.authenticate("test", "123".toCharArray())){  
                System.out.println("����MongoDB���ݿ�,У��ʧ�ܣ�");  
            }else{  
                System.out.println("����MongoDB���ݿ�,У��ɹ���");  
                  
                db.requestStart();  
                //��ȡ��������  
                Set<String> colNameSet = db.getCollectionNames();  
                Iterator<String> colNameItr = colNameSet.iterator();  
                while(colNameItr.hasNext()){  
                    String colName = colNameItr.next();  
                    System.out.println("���ݼ��ϵ����ƣ�"+colName);  
                }  
                //��ȡ���϶���  
                DBCollection dbCol = db.getCollection("ADMIN1352528239201");  
                if(dbCol != null){  
                    //��ȡ���ϴ�С  
                    Integer colSize = (Integer) dbCol.getStats().get("size");  
                    System.out.println("���ݼ���СΪ[��λ��B]��"+colSize);  
                }  
                db.requestDone();  
            }
		 }catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }
	}
}
