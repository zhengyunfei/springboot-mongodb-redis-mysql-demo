package com.sparrow.controller;

import com.sparrow.repository.NewsRepository;
import com.sparrow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	private UserRepository userRepository;
	private NewsRepository newsRepository;

	@Autowired
	public AdminController(NewsRepository newsRepository, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.newsRepository = newsRepository;
	}

	/**
	 * 管理主界面
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/")
	public String adminIndex(Model model) {
		model.addAttribute("dashboard", true);
		model.addAttribute("userscount", userRepository.count());
		model.addAttribute("newscount", newsRepository.count());
		return "admin/index";
	}


}
