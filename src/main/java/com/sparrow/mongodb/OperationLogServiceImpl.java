package com.sparrow.mongodb;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationLogServiceImpl implements OperationLogService{
	private final static Logger logger = LoggerFactory.getLogger(OperationLogServiceImpl.class);
	private static String OPERATION_LOGIN_COLLECTION = "log";

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * mongodb日志信息入库
	 * @author 郑云飞
	 * @createDate 2017-03-28
	 * @param operationLog
	 */
	public void save(OperationLog operationLog) {
		mongoTemplate.save(operationLog,OPERATION_LOGIN_COLLECTION);
	}

	/**
	 * 根据内容查询日志信息
	 * @author 郑云飞
	 * @createDate 2017-03-28
	 * @param content
	 * @return
	 */
	public OperationLog find(String content) {
		if (mongoTemplate == null)
			return null;
		Query query = new Query(Criteria.where("content").is(content));
		OperationLog log = mongoTemplate.findOne(query, OperationLog.class,OPERATION_LOGIN_COLLECTION);
		if (log == null)
			return null;
		logger.info("查询日志：{}",log.toString());
		return log;
	}

	/**
	 *查询所有日志信息
	 * @author 郑云飞
	 * @createDate 2017-03-28
	 * @return
	 */
	public List<OperationLog> findAll() {
		if (mongoTemplate == null)
			return null;
		List<OperationLog> list = mongoTemplate.findAll(OperationLog.class,OPERATION_LOGIN_COLLECTION);
		if (list == null)
			return null;
		logger.info("查询日志：{}",list.toString());
		return list;
	}

	/**
	 *查询所有日志信息
	 * @author 贤名
	 * @createDate 2017-03-28
	 * @return
	 */
	public List<OperationLog> getPage(Query query, int start, int size) {
		if (mongoTemplate == null){
			return null;
		}
		query.skip(start);
		query.limit(size);
		List<OperationLog> list = mongoTemplate.find(query,OperationLog.class);
		if (list == null){
			return null;
		}
		logger.info("查询日志：{}",list.toString());
		return list;
	}
	/**
	 * 根据条件查询库中符合记录的总数,为分页查询服务
	 * @param query
	 *                     查询条件
	 * @return
	 *                     满足条件的记录总数
	 */
	public Long getPageCount(Query query){
		logger.info("[Mongo Dao ]queryPageCount:" + query);
		return this.mongoTemplate.count(query, OperationLog.class);
	}
}
