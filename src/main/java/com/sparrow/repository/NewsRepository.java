package com.sparrow.repository;
import com.sparrow.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * 新闻表Repository定义
 * @author 郑云飞
 *
 */
@RestResource(exported = false)

public interface NewsRepository extends PagingAndSortingRepository<News, Long> {

	Page<News> findByIdGreaterThan(Long startid, Pageable p);
}
