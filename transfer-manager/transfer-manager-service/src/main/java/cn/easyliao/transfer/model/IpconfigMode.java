package cn.easyliao.transfer.model;


public class IpconfigMode {
  
  
  
  private int echx;
  private String ipaddr;
  private  String netmask;
  public int getEchx() {
    return echx;
  }
  public void setEchx(int echx) {
    this.echx = echx;
  }
  public String getIpaddr() {
    return ipaddr;
  }
  public void setIpaddr(String ipaddr) {
    this.ipaddr = ipaddr;
  }
  public String getNetmask() {
    return netmask;
  }
  public void setNetmask(String netmask) {
    this.netmask = netmask;
  }
  public String getGateway() {
    return gateway;
  }
  public void setGateway(String gateway) {
    this.gateway = gateway;
  }
  public String getDnsserver() {
    return dnsserver;
  }
  public void setDnsserver(String dnsserver) {
    this.dnsserver = dnsserver;
  }
  public String getBrocast() {
    return brocast;
  }
  public void setBrocast(String brocast) {
    this.brocast = brocast;
  }
  private  String gateway;
  private String dnsserver;
  private  String brocast;
}