package cn.easyliao.transfer.model.search;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class AuditSearch {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beforeTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date afterTime;
	
    private String scope;

    private String user;

    private String operationtype;

    private String datatype;

	public Date getBeforeTime() {
		return beforeTime;
	}

	public void setBeforeTime(Date beforeTime) {
		this.beforeTime = beforeTime;
	}

	public Date getAfterTime() {
		return afterTime;
	}

	public void setAfterTime(Date afterTime) {
		this.afterTime = afterTime;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOperationtype() {
		return operationtype;
	}

	public void setOperationtype(String operationtype) {
		this.operationtype = operationtype;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

}
