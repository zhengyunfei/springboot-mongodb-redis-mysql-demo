package com.sectong.controller;
import com.sectong.domain.News;
import com.sectong.domain.User;
import com.sectong.repository.NewsRepository;
import com.sectong.service.UserService;
import com.sectong.utils.ServerResourcesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/news")
public class NewsController {

	private NewsRepository newsRepository;
	private UserService userService;

	@Autowired
	public NewsController(NewsRepository newsRepository, UserService userService) {
		this.newsRepository = newsRepository;
		this.userService = userService;
	}

	/**
	 * 新闻管理
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String adminNews(Model model,HttpServletRequest request) {
		model.addAttribute("news", true);
		Iterable<News> newslist = newsRepository.findAll();
		model.addAttribute("newslist", newslist);
		String domain=ServerResourcesUtil.getCurrentDomainUrl(request);
		model.addAttribute("domain",domain);
		return "admin/news";
	}

	/**
	 * 新闻增加表单
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/add")
	public String newsAdd(Model model, HttpServletRequest request) {
		model.addAttribute("newsAdd", new News());
		String domain= ServerResourcesUtil.getCurrentDomainUrl(request);
		model.addAttribute("domain",domain);
		return "admin/newsAdd";
	}

	/**
	 * 新闻修改表单
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/edit")
	public String newsEdit(Model model, @RequestParam Long id) {
		model.addAttribute("newsEdit", newsRepository.findOne(id));
		return "admin/newsEdit";
	}

	/**
	 * 新闻修改提交操作
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param news
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/edit")
	public String newsSubmit(@ModelAttribute News news) {
		news.setDatetime(new Date());
		User user = userService.getCurrentUser();
		news.setUser(user);
		newsRepository.save(news);
		return "redirect:/news/list";
	}

	/**
	 * 新闻删除操作
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/del")
	public String delNews(Model model, @RequestParam Long id) {
		newsRepository.delete(id);
		return "redirect:/admin/news";

	}


	/**
	 * 新闻详情页面
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/detail")
	public String detailNews(Model model, @RequestParam Long id) {
		News news=newsRepository.findOne(id);
		model.addAttribute("bo",news);
		return "html/detail";

	}
	/**
	 * 文章系列
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @return
	 */
	@GetMapping("/xilie")
	public String xilie(Model model) {
		return "html/xilie";

	}

}
