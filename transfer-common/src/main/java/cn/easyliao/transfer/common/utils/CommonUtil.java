package cn.easyliao.transfer.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.xml.XMLSerializer;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.net.util.Base64;

import cn.easyliao.transfer.common.pojo.IpMsg;
import cn.easyliao.transfer.common.pojo.SvserverResult;

public class CommonUtil {

	
	 
    //全局map
    public static Map<String, Object> IPMAPS = new HashMap<String, Object>();
	// 获取英文名称
	public static String getPingYin(String src) {
		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();

		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(t1[i]).matches(
						"[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 += t2[0];
				} else
					t4 += java.lang.Character.toString(t1[i]);
			}
			// System.out.println(t4);
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return t4;
	}

	// 返回中文的首字母
	public static String getPinYinHeadChar(String str) {

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}
	public static String MD5( String password)
	{
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
        
        //加密后的字符串
        String newstr=new String(Base64.encodeBase64(md5.digest(password.getBytes("utf-8"))));
        return newstr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	 public static Field getDeclaredField(Object object, String fieldName){  
	        Field field = null ;  
	          
	        Class<?> clazz = object.getClass() ;  
	          
	        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
	            try {  
	                field = clazz.getDeclaredField(fieldName) ;  
	                return field ;  
	            } catch (Exception e) {  
	            	
	             
	            }   
	        }  
	      
	        return null;  
	    }  
	 //去掉回车/空格等
	 public static String replaceBlank(String str) {
			String dest = "";
			if (str!=null) {
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(str);
				dest = m.replaceAll("");
			}
			return dest;
		}
	 public static String ObjectToXml( Object obj )
	 {
	 
	  XMLSerializer xmlSerializer = new XMLSerializer();
      return  xmlSerializer.write( net.sf.json.JSONSerializer.toJSON( JsonUtils.objectToJson( obj) ));
      
	    
	 }
	 
	 //读取二进制流
	 public static  byte[] getBytes(File file){  
	        byte[] buffer = null;
	        if (file == null){
	            return buffer;
	        } else {             
	            try {     
	                FileInputStream fis = new FileInputStream(file);  
	                ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
	                byte[] b = new byte[1000];  
	                int n;  
	                while ((n = fis.read(b)) != -1) {  
	                    bos.write(b, 0, n);  
	                }  
	                fis.close();  
	                bos.close();  
	                buffer = bos.toByteArray();  
	            } catch (FileNotFoundException e) {  
	                e.printStackTrace();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }
	            
	        }
	          
	         return buffer;  
	  }   
	 /**
	   * 任务编码随机生成
	   */
	  private static final String base = "abcdefghijklmnopqistuvwxyz0123456789";
	  public static String generate() {
	    return getRandomString(6);
	  }
	  private static String getRandomString(int length) {
	    Random r = new Random();
	    StringBuffer buff = new StringBuffer();
	    for(int i = 0;i<length;i++) {
	      int number = r.nextInt(base.length());
	      buff.append(base.charAt(number));
	    }
	    return buff.toString();
	  }
	 
	 
	 /**
	  * 
	  * 转成utf-8
	  * @param s
	  * @return
	  */
	 public static String toUtf8String(String s){  
	     StringBuffer sb = new StringBuffer();  
	       for (int i=0;i<s.length();i++){  
	          char c = s.charAt(i);  
	          if (c >= 0 && c <= 255){sb.append(c);}  
	        else{  
	        byte[] b;  
	         try { b = Character.toString(c).getBytes("utf-8");}  
	         catch (Exception ex) {  
	               
	                  b = new byte[0];  
	         }  
	            for (int j = 0; j < b.length; j++) {  
	             int k = b[j];  
	              if (k < 0) k += 256;  
	              sb.append("%" + Integer.toHexString(k).toUpperCase());  
	              }  
	     }  
	  }  
	  return sb.toString();  
	}
	 /**
	  * 浏览器兼容
	  * @param fileName
	  * @param agent
	  * @return
	  */
	 public static String fileNameCoding(String fileName, String agent){
		 if (agent.contains("Firefox")) { // 火狐浏览器
			 try {
				 fileName=	new String(fileName.getBytes("UTF-8"), "iso-8859-1") ;
			
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
				
			}
			} else { // IE及其他浏览器
				fileName=	CommonUtil.toUtf8String(fileName);
		
			}
			return fileName;

		 
	 }
	 
	 /**
		 * 获取用户真实ip
		 */
		public static String getIp(HttpServletRequest request){
			String ip = request.getHeader("x-forwarded-for");
			if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
				ip=request.getHeader("Proxy-Client-IP");
			}
			if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
				ip=request.getHeader("WL-Proxy-Client-IP");
			}
			if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
				ip=request.getHeader("HTTP_CLIENT_IP");
			}
			if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
				ip=request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
				ip=request.getRemoteAddr();
			}
			return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
		}
		
		public static SvserverResult getWarnInfo(HttpServletRequest request){
			
			//获取用户真实ip
			String ip =CommonUtil.getIp(request);
			//		
			IpMsg ipMsg= (IpMsg)IPMAPS.get(ip);
					
			int count=0;
			
			if(ipMsg!=null){
				//验证是否超过5次
				count= ipMsg.getCount();
				//最后一次登录的时间
				long lastTime=(long)ipMsg.getLastTime();
				//当前的时间
				long now = System.currentTimeMillis();
				//计算出时间差
				long minutes= (now-lastTime)/1000/60;
				//如果登录次数大于5 并且时间间隔小于20分钟 给管理员发邮件		
				if((count>=5) && (minutes < 20)){
					return SvserverResult.build(666, "请求繁忙，请20分钟以后再试");
				}
				//如果时间间隔大于20分钟 正常登录		
				if( minutes > 20 )
				{
					ipMsg = new IpMsg();
					ipMsg.setIp(ip);
					ipMsg.setCount(0);
					IPMAPS.put(ip, ipMsg);
				}
			}else{//如果ipMsg为空 则创建一个IPmsg
				ipMsg = new IpMsg();
				//设置ip
				ipMsg.setIp(ip);
				//设置次数
				ipMsg.setCount(0);
				//保存到全局变量中
				IPMAPS.put(ip, ipMsg);
			}
			//如果session不为空,
			/*if (ShiroKit.getSession() != null) {
				//如果ip不为空
				if(IPMAPS.get(ip)!=null){
					//把ip地址在全局变量里 移除点
					IPMAPS.remove(ip);
				}
				return REDIRECT+ "/login.html";
			}
			
			
			if(token == null){
			count++;
			ipMsg.setCount(count);
			ipMsg.setLastTime(System.currentTimeMillis());
			IPMAPS.put(ip, ipMsg);
			return "账号或密码错误";
		}
		
*/	
			return null;
			
		}
		
		public static int [] GetStartEndTime( String hourstr )
		{
			String pattern = "(\\d+):(\\d+)-(\\d+):(\\d+)";
			 Pattern r = Pattern.compile(pattern);
			 
		      // 现在创建 matcher 对象
		      Matcher m = r.matcher(hourstr);
		      if (m.find( )) {
		    	  int [] tt = new int[4];
		    	  tt[0] = Integer.parseInt(m.group(1));
		    	  tt[1] = Integer.parseInt(m.group(2));
		    	  tt[2] = Integer.parseInt(m.group(3));
		    	  tt[3] = Integer.parseInt(m.group(4));
		    	  return tt;
		      } else {
		         System.out.println("NO MATCH");
		      }
			
			return null;
		}
		
		
	    
}
