package com.zhoutao.mongodb.connect;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

public class MongoManager {  
    private final static String HOST = "localhost";// 端口  
    private final static int PORT = 27017;// 端口  
    private final static int POOLSIZE = 100;// 连接数量  
    private final static int BLOCKSIZE = 100; // 等待队列长度  
    private static Mongo mongo = null;  

  
    private MongoManager() { }  
  
    static {  
        initDBPrompties();  
    }  
  
    public static DB getDB(String dbName) {  
        return mongo.getDB(dbName);  
    }  
  
    /** 
     * 初始化连接池 
     */  
    private static void initDBPrompties() {  
        // 其他参数根据实际情况进行添加  
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
               System.out.println("连接MongoDB数据库,校验失败！");  
           }else{  
               System.out.println("连接MongoDB数据库,校验成功！");  
           }
		return db;
    }
}  
