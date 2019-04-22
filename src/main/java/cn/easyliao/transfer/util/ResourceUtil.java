package cn.easyliao.transfer.util;

import java.util.Properties;


/**
 * 资源文件信息获取帮助
 */
public class ResourceUtil {
	
	private static ResourceUtil rcUtil;
	
	private static 	Properties properties;
	
	private ResourceUtil(){
		loadProperties();
	}
	
	//加载文件资源信息
	private static void loadProperties() {
		try {
			properties = new Properties();
			properties.load(ResourceUtil.class.getClassLoader().getResourceAsStream("interface_config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//返回ResourceUtil实例
	public static ResourceUtil getInstance(){
		if(rcUtil == null) rcUtil = new ResourceUtil();
		return rcUtil;
	}

	//取得属性值
	public String getProperty(String keyName,String defaultValue){
		return properties.getProperty(keyName, defaultValue);
	}
	
	//取得属性值
	public String getProperty(String keyName){
		return properties.getProperty(keyName);
	}

	public static Properties getProperties() {
		if(properties == null) loadProperties();
		return properties;
	}

	public static void setProperties(Properties properties) {
		ResourceUtil.properties = properties;
	}
}
