package com.sparrow.service;

import com.sparrow.dao.IArticleDao;
import com.sparrow.dao.support.IBaseDao;
import com.sparrow.domain.News;
import com.sparrow.domain.NewsCreateForm;
import com.sparrow.domain.User;
import com.sparrow.repository.NewsRepository;
import com.sparrow.service.support.impl.BaseServiceImpl;
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
public class NewsServiceImpl extends BaseServiceImpl<News, Integer> implements NewsService {

	private NewsRepository newsRepository;
	private UserService userService;
	@Autowired
	private IArticleDao articleDao;
	@Override
	public IBaseDao<News, Integer> getBaseDao() {
		return this.articleDao;
	}
	/**
	 * 注入NewsRepository
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
		Page<News> newsList = newsRepository.findByIdGreaterThan(startid, p);
		return newsList;
	}

	@Override
	public long getPageCount() {
		return this.newsRepository.count();
	}


}
