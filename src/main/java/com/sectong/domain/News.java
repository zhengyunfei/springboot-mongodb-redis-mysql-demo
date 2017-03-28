package com.sectong.domain;

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
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String img;
	private String content;
	private Date datetime;
	@ManyToOne
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void createNews(String title, String img, String content, Date datetime, User user) {
		this.title = title;
		this.img = img;
		this.content = content;
		this.datetime = datetime;
		this.user = user;
	}

}
