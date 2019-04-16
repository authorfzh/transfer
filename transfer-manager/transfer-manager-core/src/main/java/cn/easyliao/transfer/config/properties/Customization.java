package cn.easyliao.transfer.config.properties;



import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * spring boot 配置文件中定制参数的类
 * @date 2017年9月19日
 */
@Component
@ConfigurationProperties(prefix = Customization.PREFIX)
public class Customization {
	


public String getCertificatepolicyPath() {
		return certificatepolicyPath;
	}
	public void setCertificatepolicyPath(String certificatepolicyPath) {
		this.certificatepolicyPath = certificatepolicyPath;
	}
public String getFilesUploadPath() {
		return filesUploadPath;
	}
	public void setFilesUploadPath(String filesUploadPath) {
		this.filesUploadPath = filesUploadPath;
	}
public  static final String PREFIX="spring.here" ;
//文件上传的地址
    private String filesUploadPath = "";
    //客户端jar文件运行的path
    private String runPath="";
    //删除进程是所需要的属性
    private String killParam="";
	//证书验证配置策略的配置文件
    private String certificatepolicyPath;
    //微信APPID
    private String APPID;
    //微信APPSECRET
    private String APPSECRET;
    
    
	public String getAPPID() {
		return APPID;
	}
	public void setAPPID(String aPPID) {
		APPID = aPPID;
	}
	public String getAPPSECRET() {
		return APPSECRET;
	}
	public void setAPPSECRET(String aPPSECRET) {
		APPSECRET = aPPSECRET;
	}
	public static String getPrefix() {
		return PREFIX;
	}
	public String getRunPath() {
		return runPath;
	}
	public void setRunPath(String runPath) {
		this.runPath = runPath;
	}
	public String getKillParam() {
		return killParam;
	}
	public void setKillParam(String killParam) {
		this.killParam = killParam;
	}
	
	//发送邮件
	private String sendEmail="";
	
	public String getSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	
	

	
}
