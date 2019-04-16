package cn.easyliao.transfer.common.pojo;

public class IpMsg {
	
	private String ip = "";
	private int count = 0 ;
	private long lastTime;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getLastTime() {
		return lastTime;
	}
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	@Override
	public String toString() {
		return "IpMsg [ip=" + ip + ", count=" + count + ", lastTime="
				+ lastTime + "]";
	}
	
	
	
}
