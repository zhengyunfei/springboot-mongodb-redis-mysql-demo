package com.sectong.service;

import com.sectong.domain.News;
import com.sectong.domain.NewsCreateForm;
import com.sectong.domain.User;
import com.sectong.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 新闻接口实现
 *
 * @author 郑云飞
 *
 */
@Service
public class NewsServiceImpl implements NewsService {

	private NewsRepository newsRepository;
	private UserService userService;

	/**
	 * 注入NewsRepository
	 *
	 * @param newsRepository
	 * @param userService
	 */
	@Autowired
	public NewsServiceImpl(NewsRepository newsRepository, UserService userService) {
		this.newsRepository = newsRepository;
		this.userService = userService;
	}

	/**
	 * 创建新闻
	 */
	@Override
	public News create(NewsCreateForm form) {
		User user = userService.getCurrentUser();
		News news = new News();
		news.createNews(form.getTitle(), form.getImg(), form.getContent(), new Date(), user);
		newsRepository.save(news);
		return news;

	}

	/**
	 * 获取新闻列表
	 */
	@Override
	public Page<News> getNewsList(Long startid, Pageable p) {
		Page<News> news = newsRepository.findByIdGreaterThan(startid, p);
		return news;
	}

}
