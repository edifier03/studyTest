package com.zhoutao.mongodb.main;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.zhoutao.mongodb.DAO.impl.MongoDAOImpl;
import com.zhoutao.mongodb.bean.TestBean;
import com.zhoutao.mongodb.connect.MongoManager;

public class Test {
	public static void main(String[] args)
	{
		
		Test test = new Test();
		test.insertDB();
	}
	
	public void insertDB()
	{
		MongoDAOImpl baseDAOImpl = new MongoDAOImpl();
		BasicDBObject obj = new BasicDBObject();
		obj.put("name", "test1");
		obj.put("img", this.getImageBinary());
		boolean result = baseDAOImpl.insert("javaDBtest1", obj);
		System.out.println(result);
//		String json = "{\"name\":\"a\",\"age\":123}";
//		DBObject dbo = (DBObject) JSON.parse(json);  
	}
	

	public  byte[] getImageBinary(){      
        File f = new File("D:\\sqllite\\img\\10_16X16.png");             
        BufferedImage bi;   
        byte[] bytes = null;
        try {      
            bi = ImageIO.read(f);      
            ByteArrayOutputStream baos = new ByteArrayOutputStream();      
            ImageIO.write(bi, "png", baos);      
            bytes = baos.toByteArray();        
        } catch (IOException e) {      
            e.printStackTrace();      
        }      
        return bytes;      
    }      
	
	public void selDB()
	{
		MongoDAOImpl baseDAOImpl = new MongoDAOImpl();
		List<String> list= baseDAOImpl.find("javaDBtest1");
		System.out.println(list);
	}
}
