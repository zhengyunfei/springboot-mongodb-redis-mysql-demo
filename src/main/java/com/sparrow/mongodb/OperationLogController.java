package com.sparrow.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list (Model model) {
		long startTime = System.currentTimeMillis();    //获取开始时间
		model.addAttribute("log", true);
		List<OperationLog> list= operationLogService.findAll();
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
}
