package com.sparrow.mongodb;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface OperationLogService {
	/**
	 * 插入日志
	 * @author 郑云飞
	 * @createDate 2017-03-28
	 * @param user
	 * @return
	 */
	void save(OperationLog user);

	/**
	 * 按名称查询日志
	 * @author 郑云飞
	 * @createDate 2017-03-28
	 * @return
	 */
	OperationLog find(String name);

	/**
	 * 查询日志
	 * @author 郑云飞
	 * @createDate 2017-03-28
	 * @return
	 */
	List<OperationLog> findAll();
	/**
	 * 通过条件进行分页查询
	 * @param query
	 *                     查询条件
	 * @param start
	 *                     查询起始值
	 *                     <strong> 类似mysql查询中的 limit start, size 中的 start</strong>
	 * @param size
	 *                     查询大小
	 *                     <strong> 类似mysql查询中的 limit start, size 中的 size</strong>
	 * @return
	 *                     满足条件的集合
	 */
	public List<OperationLog> getPage(Query query, int start, int size) ;
	/**
	 * 根据条件查询库中符合记录的总数,为分页查询服务
	 * @param query
	 *                     查询条件
	 * @return
	 *                     满足条件的记录总数
	 */
	public Long getPageCount(Query query);
}
