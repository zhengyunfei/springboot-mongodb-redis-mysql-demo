package com.sectong.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 创建新闻POJO
 * @author:郑云飞
 * @createDate:2017-03-28
 *
 */
public class NewsCreateForm {

	@NotEmpty
	private String title;

	@NotEmpty
	private String content;

	@NotEmpty
	private String img;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
