package cn.transfer.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyMvcConfig {

	public static void  main(String [] args)
	{
		String line = "8:30-19:30";
		String pattern = "(\\d+):(\\d+)-(\\d+):(\\d+)";
		 Pattern r = Pattern.compile(pattern);
		 
	      // 现在创建 matcher 对象
	      Matcher m = r.matcher(line);
	      if (m.find( )) {
	         System.out.println("Found value: " + m.group(0) );
	         System.out.println("Found value: " + m.group(1) );
	         System.out.println("Found value: " + m.group(2) );
	         System.out.println("Found value: " + m.group(3) );
	         System.out.println("Found value: " + m.group(4) ); 
	      } else {
	         System.out.println("NO MATCH");
	      }
		System.out.print("ok");
	}
}
