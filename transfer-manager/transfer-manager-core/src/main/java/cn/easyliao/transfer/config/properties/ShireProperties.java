package cn.easyliao.transfer.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Shire相关的参数
 * @author 
 * @date 2017年
 */
@Component
@ConfigurationProperties(prefix = "spring.shire")
public class ShireProperties {


	public static String getLoginFile() {
		return loginFile;
	}
	public static void setLoginFile(String loginFile) {
		ShireProperties.loginFile = loginFile;
	}
	public static final String MANAGE="manage";
	public static final String COMMON="common";
	public static	int   cookiemaxage = 7 * 24 * 60 * 60;
	/**
	 * SSO登录的url
	 */
	public static	String loginurl  = "";
/**
 * 用于登录的文件路径
 */
	public static	String loginFile  = "";
	public static	String successurl = "";
	public static   String userType="";
	
	public static String getUserType() {
		return userType;
	}
	public static void setUserType(String userType) {
		ShireProperties.userType = userType;
	}
	public int getCookiemaxage() {
		return cookiemaxage;
	}
	public void setCookiemaxage(int cookiemaxage) {
		this.cookiemaxage = cookiemaxage;
	}
	public String getLoginurl() {
		return loginurl;
	}
	public void setLoginurl(String loginurl) {
		this.loginurl = loginurl;
	}
	public String getSuccessurl() {
		return successurl;
	}
	public void setSuccessurl(String successurl) {
		this.successurl = successurl;
	}
	
 
}
