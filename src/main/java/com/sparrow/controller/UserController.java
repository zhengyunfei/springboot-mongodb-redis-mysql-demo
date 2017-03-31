package com.sparrow.controller;
import com.sparrow.domain.User;
import com.sparrow.repository.UserRepository;
import com.sparrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private UserRepository userRepository;
	private UserService userService;

	@Autowired
	public UserController(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}
	/**
	 * 用户管理
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String adminUser(Model model,HttpServletRequest request) {
		model.addAttribute("user", true);
		return "admin/user";
	}

	/**
	 * 用户新增表单
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/add")
	public String userAdd(Model model) {
		model.addAttribute("userAdd", new User());
		return "admin/userAdd";
	}

	/**
	 * 用户新增提交操作
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param user
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
	public String userAdd(@ModelAttribute User user) {
		userRepository.save(user);
		return "redirect:/user/list";
	}

	/**
	 * 用户修改表单
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/edit")
	public String userEdit(Model model, @RequestParam Long id) {
		model.addAttribute("userEdit", userRepository.findOne(id));
		return "admin/userEdit";
	}

	/**
	 * 用户修改提交操作
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/edit")
	public String userSubmit(@ModelAttribute User user) {
		userRepository.save(user);
		return "redirect:/user/list";
	}

	/**
	 * 用户删除操作
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/del")
	public String delUser(Model model, @RequestParam Long id) {
		userRepository.delete(id);
		return "redirect:/user/list";

	}
}
