package com.sectong.api;

import com.sectong.mongodb.OperationLog;
import com.sectong.mongodb.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/operationLog")
public class OperationLogApi {

	@Autowired
	private OperationLogService operationLogService;
	/**
	 * mongodb入库api接口
	 * @param log
	 */
	@RequestMapping(value = "/save",method= RequestMethod.POST)
	public void insert (@RequestBody OperationLog log) {
		operationLogService.save(log);
	}
	/**
	 * mongodb入库api接口
	 */
	@RequestMapping(value = "/new",method= RequestMethod.GET)
	@ResponseBody
	public void insertForGet (@RequestParam String content, @RequestParam String ip, @RequestParam String mobile, @RequestParam String identity) {
		OperationLog log=new OperationLog();
		try{
			 content = new String(URLDecoder.decode(content,"utf-8"));
			log.setContent(content);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String dateTime= sdf.format(date);
			log.setCreateTime(dateTime);
			log.setIp(ip);
			log.setMobile(mobile);
			log.setIdentity(identity);
			operationLogService.save(log);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/test",method= RequestMethod.POST)
	public void test (@RequestParam String content, @RequestParam String createTime, @RequestParam String ip, @RequestParam String mobile, @RequestParam String identity) {
		OperationLog log=new OperationLog();
		log.setContent(content);
		log.setCreateTime(createTime);
		log.setIp(ip);
		log.setMobile(mobile);
		log.setIdentity(identity);
		operationLogService.save(log);
	}
	@RequestMapping(value="/find",method=RequestMethod.GET)
	public void findOperationLogByMap(@RequestParam String content , HttpServletRequest request, HttpServletResponse response) {
		OperationLog log=operationLogService.find(content);
		boolean flag=false;
		try{
			PrintWriter out = response.getWriter();
			// 返回结果做成一个List
			boolean result = false;
			if(!StringUtils.isEmpty(log)){
				flag=true;
			}
			out.print(flag);
			out.close();
			out = null;
		}catch (Exception e){
			e.getMessage();
		}
	}
}
