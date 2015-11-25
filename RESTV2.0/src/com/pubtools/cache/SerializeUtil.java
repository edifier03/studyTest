package com.pubtools.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
	public static byte[] serialize(Object obj)
	{
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		
		try{
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			byte[] bytes = baos.toByteArray();
			return bytes;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object unserialize(byte[] bytes)
	{
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try{
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
