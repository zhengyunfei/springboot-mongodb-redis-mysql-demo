package com.sparrow.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 新闻POJO
 * @author:郑云飞
 * @createDate:2017-03-28
 * @author 郑云飞
 *
 */

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String roleno;
	private String rolename;
	private Date createtime;
	private int isactive;
	private String roledesc;
	@ManyToOne
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRoleno() {
		return roleno;
	}

	public void setRoleno(String roleno) {
		this.roleno = roleno;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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

	public Role(String roleno, String rolename, Date createtime, int isactive, String roledesc, User user) {
		this.roleno = roleno;
		this.rolename = rolename;
		this.createtime = createtime;
		this.isactive = isactive;
		this.roledesc = roledesc;
		this.user = user;
	}

	public Role() {
	}
}
