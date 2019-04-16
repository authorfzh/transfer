package cn.easyliao.transfer.model.search;


public class UserSearch {
	
	private String name;
    private String scope;
    private String phone;
   
    public String getCompanypath() {
		return companypath;
	}

	public void setCompanypath(String companypath) {
		this.companypath = companypath;
	}

	private String companypath;
    

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String permission;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}
