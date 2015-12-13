package com.zhoutao.mongodb.connect;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

public class MongoManager {  
    private final static String HOST = "localhost";// �˿�  
    private final static int PORT = 27017;// �˿�  
    private final static int POOLSIZE = 100;// ��������  
    private final static int BLOCKSIZE = 100; // �ȴ����г���  
    private static Mongo mongo = null;  

  
    private MongoManager() { }  
  
    static {  
        initDBPrompties();  
    }  
  
    public static DB getDB(String dbName) {  
        return mongo.getDB(dbName);  
    }  
  
    /** 
     * ��ʼ�����ӳ� 
     */  
    private static void initDBPrompties() {  
        // ������������ʵ������������  
        try {  
        
            MongoOptions opt=new MongoOptions();
            opt.connectionsPerHost = POOLSIZE;  
            opt.threadsAllowedToBlockForConnectionMultiplier = BLOCKSIZE;  
            
            ServerAddress serverAddress=new ServerAddress(HOST,PORT);
            
            mongo = new Mongo(serverAddress, opt);  
         
        } catch (UnknownHostException e) {  
        } catch (MongoException e) {  
  
        }  
  
    }  
    
    
    public static DB getDB(String dbname,String username,String password)
    {
    	   DB db = mongo.getDB(dbname);
           if (!db.authenticate(username, password.toCharArray())){  
               System.out.println("����MongoDB���ݿ�,У��ʧ�ܣ�");  
           }else{  
               System.out.println("����MongoDB���ݿ�,У��ɹ���");  
           }
		return db;
    }
}  
