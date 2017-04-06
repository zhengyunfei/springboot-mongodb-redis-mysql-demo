package com.sparrow.mongodb;

import com.sparrow.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/log")
public class OperationLogController {

	@Autowired
	private OperationLogService operationLogService;


	/**
	 * @deprecated  日志信息入库
	 * @author 郑云飞
	 * @createDate 2017-03-28
	 * @param log
	 */
	@RequestMapping("/save")
	public void save (OperationLog log) {
		operationLogService.save(log);
	}

	/**
	 * 根据手机号码查询日志内容
	 * @author 郑云飞
	 * @createDate 2017-03-28
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/find")
	public ModelAndView find (String mobile) {
		OperationLog log = operationLogService.find(mobile);
		ModelAndView mv=new ModelAndView();
		mv.addObject("log",log);
		return mv;
	}

	/**
	 * 查询mongodb所有日志内容
	 * @author 贤名
	 * @param model
	 * @return
	 */
	@RequestMapping("/query/list")
	public ModelAndView queryList (Model model, Page page) {
		long startTime = System.currentTimeMillis();    //获取开始时间
		model.addAttribute("log", true);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/mongodbLog/log");
		long endTime = System.currentTimeMillis();    //获取结束时间
		String runTime=(endTime - startTime) + "ms";
		System.out.println("程序运行时间：" +runTime);    //输出程序运行时间
		model.addAttribute("runtime",runTime);
		return mv;
	}
	/**
	 * 查询mongodb所有日志内容
	 * @author 贤名
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list (Model model, Page page) {
		long startTime = System.currentTimeMillis();    //获取开始时间
		model.addAttribute("log", true);
		Query query = new Query();
		long total=operationLogService.getPageCount(query);
		List<OperationLog> list=null;
		if(total>0){
			int start=page.getCurrent();
			int size=page.getRowCount();
			list= operationLogService.getPage(query,(start-1)*size,size);
		}
		Page listPage=new Page();
		listPage.setTotal(total);
		listPage.setRows(list);
		ModelAndView mv=new ModelAndView();
		mv.addObject("list",list);
		mv.setViewName("/admin/mongodbLog/log");
		long endTime = System.currentTimeMillis();    //获取结束时间
		String runTime=(endTime - startTime) + "ms";
		System.out.println("程序运行时间：" +runTime);    //输出程序运行时间
		model.addAttribute("total",list.size());
		model.addAttribute("runtime",runTime);
		return mv;
	}
	/**
	 * 分页查询mongodb所有日志内容
	 * @author 贤名
	 * @param model
	 * @return
	 */
	@RequestMapping("/pagejson")
	@ResponseBody
	public Page pageJson (Model model, Page page) {
		Query query = new Query();
		long total=operationLogService.getPageCount(query);
		List<OperationLog> list=null;
		if(total>0){
			int start=page.getCurrent();
			int size=page.getRowCount();
			list= operationLogService.getPage(query,(start-1)*size,size);
		}
		Page listPage=new Page();
		listPage.setTotal(total);
		listPage.setRows(list);
		listPage.setCurrent(page.getCurrent());
		listPage.setRowCount(page.getRowCount());
		return listPage;
	}
}
