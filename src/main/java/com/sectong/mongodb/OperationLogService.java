package com.sectong.mongodb;

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
}
