package com.sectong.controller;

import com.sectong.repository.NewsRepository;
import com.sectong.repository.UserRepository;
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
	 *
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
