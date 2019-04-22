package cn.easyliao.transfer.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StringToInputStream {
	

	public static InputStream getStringStream(String str){
		if(str != null && !str.trim().equals("")){
			try{
				ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
				return in;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}

	
}
