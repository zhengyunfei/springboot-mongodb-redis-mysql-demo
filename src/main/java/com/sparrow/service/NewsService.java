package com.sparrow.service;

import com.sparrow.domain.News;
import com.sparrow.domain.NewsCreateForm;
import com.sparrow.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 新闻接口
 * @author 郑云飞
 *
 */
public interface NewsService extends IBaseService<News, Integer> {

	News create(NewsCreateForm form);

	Page<News> getNewsList(Long startid, Pageable p);

    long getPageCount();
}
