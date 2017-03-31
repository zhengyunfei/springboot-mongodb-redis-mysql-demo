package com.sparrow.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 创建角色字段POJO定义
 * @author:郑云飞
 * @createDate:2017-03-28
 *
 */
public class RoleCreateForm {

	@NotEmpty
	private String roleno;

	@NotEmpty
	private String rolename;
	@NotEmpty
	private int isactive;
	@NotEmpty
	private String roledesc;

	public String getRoleno() {
		return roleno;
	}

	public void setRoleno(String roleno) {
		this.roleno = roleno;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public String getRoledesc() {
		return roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

	@Override
	public String toString() {
		return "UserCreateForm [roleNo=" + roleno + ", rolename=" + rolename + "]";
	}

}
