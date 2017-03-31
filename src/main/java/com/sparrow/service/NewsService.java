package com.sparrow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sparrow.domain.News;
import com.sparrow.domain.NewsCreateForm;

/**
 * 新闻接口
 * @author 郑云飞
 *
 */
public interface NewsService {

	News create(NewsCreateForm form);

	Page<News> getNewsList(Long startid, Pageable p);

}
