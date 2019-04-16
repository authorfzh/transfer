package cn.easyliao.transfer.model.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送邮件的 对象
 * @author chy
 *
 */
public class EmailConfigMode {
  /**
   * 默认生成的该类的LOG记录器，使用slf4j组件。避免产生编译警告，使用protected修饰符。
   */
  protected final static Logger LOG = LoggerFactory.getLogger(EmailConfigMode.class);
  
   private String serviceaddress;
   private   int port;
   private  String account;
   private  String password;
   private String   emailsender;
   public String getServiceaddress() {
	return serviceaddress;
}

public void setServiceaddress(String serviceaddress) {
	this.serviceaddress = serviceaddress;
}

public int getPort() {
	return port;
}

public void setPort(int port) {
	this.port = port;
}

public String getAccount() {
	return account;
}

public void setAccount(String account) {
	this.account = account;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmailsender() {
	return emailsender;
}

public void setEmailsender(String emailsender) {
	this.emailsender = emailsender;
}

public String getAdminmail() {
	return adminmail;
}

public void setAdminmail(String adminmail) {
	this.adminmail = adminmail;
}

private  String  adminmail;
 
  public static Logger getLog() {
    return LOG;
  }
}
