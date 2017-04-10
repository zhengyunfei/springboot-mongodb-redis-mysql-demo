package com.sparrow.controller;

import com.sparrow.domain.News;
import com.sparrow.domain.Page;
import com.sparrow.domain.User;
import com.sparrow.repository.NewsRepository;
import com.sparrow.service.NewsService;
import com.sparrow.service.UserService;
import com.sparrow.service.specification.SimpleSpecificationBuilder;
import com.sparrow.service.specification.SpecificationOperator;
import com.sparrow.utils.ServerResourcesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/news")
public class NewsController extends BaseController{

	private NewsRepository newsRepository;
	private UserService userService;
	private NewsService newsService;

	@Autowired
	public NewsController(NewsRepository newsRepository, UserService userService,NewsService newsService) {
		this.newsRepository = newsRepository;
		this.userService = userService;
		this.newsService = newsService;
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
		String domain=ServerResourcesUtil.getCurrentDomainUrl(request);
		model.addAttribute("domain",domain);
		return "admin/news";
	}
	/**
	 * 新闻管理
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @return
	 */
	@PostMapping("/pagejson")
	@ResponseBody
	public Object pagejson(Page pageParam,HttpServletRequest request) {
		/**fen ye start**/
		SimpleSpecificationBuilder<News> builder = new SimpleSpecificationBuilder<News>();
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.add("nickName", SpecificationOperator.Operator.likeAll.name(), searchText);
		}
		org.springframework.data.domain.Page<News> page = newsService.findAll(builder.generateSpecification(), getPageRequest(pageParam));
		long total=newsService.getPageCount();
		Page listPage=new Page();
		listPage.setTotal(total);
		listPage.setRows(page.getContent());
		listPage.setCurrent(pageParam.getCurrent());
		listPage.setRowCount(pageParam.getRowCount());

		return listPage;
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
	@RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
	public String newsEdit(Model model, @PathVariable Long id) {
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
	@GetMapping("/delete/{id}")
	public String delNews(Model model, @PathVariable Long id) {
		newsRepository.delete(id);
		return "redirect:/admin/news";

	}


	/**
	 * 文章详情页面
	 * @author:贤仁
	 * @createDate:2017-03-28
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/detail/{id}")
	public String detailNews(Model model, @PathVariable Long id) {
		News news=newsRepository.findOne(id);
		String str = news.getContents();
		//使用正则表达式检索出所有的<h2>...</h2>内容
		Matcher m = Pattern.compile("<h2.*?>([\\s\\S]*?)</h2>").matcher(str);
		while(m.find()){
			//删掉<h2></h2>中间的html标签k
			String parstr=deleteAllHTMLTag(m.group(1));
			//替换内容里面所有的h2标签，动态增加id
			news.setContents(news.getContents().replace("<h2>"+m.group(1),"<h2 id='"+parstr+"'>"+m.group(1)));
		}
		model.addAttribute("bo",news);
		return "html/detail";

	}
	/**
	 * 删除所有的HTML标签
	 *
	 * @param source 需要进行除HTML的文本
	 * @return
	 */
	public static String deleteAllHTMLTag(String source) {

		if(source == null) {
			return "";
		}

		String s = source;
		/** 删除普通标签  */
		s = s.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "");
		/** 删除转义字符 */
		s = s.replaceAll("&.{2,6}?;", "");
		return s;
	}


}
