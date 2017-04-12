package com.sparrow.domain;

import com.sparrow.domain.support.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 新闻POJO
 * @author:郑云飞
 * @createDate:2017-03-28
 * @author 郑云飞
 *
 */

@Entity
public class Role extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String roleno;
	private String rolename;
	private Date createtime;
	private int isactive;
	private String roledesc;


	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_role_resource", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "resource_id") })
	private java.util.Set<Resource> resources;

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

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

	public Role(String roleno, String rolename, Date createtime, int isactive, String roledesc) {
		this.roleno = roleno;
		this.rolename = rolename;
		this.createtime = createtime;
		this.isactive = isactive;
		this.roledesc = roledesc;
	}

	public Role() {
	}
}
