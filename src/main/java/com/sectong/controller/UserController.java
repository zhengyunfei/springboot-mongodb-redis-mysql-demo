package com.sectong.controller;
import com.sectong.domain.User;
import com.sectong.repository.UserRepository;
import com.sectong.service.UserService;
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
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String adminUser(Model model,HttpServletRequest request) {
		model.addAttribute("user", true);
		//Iterable<User> userlist = userRepository.findAll();
		//model.addAttribute("userlist", userlist);
		return "admin/user";
	}

	/**
	 * 用户新增表单
	 *
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
	 *
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
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/edit")
	public String userSubmit(@ModelAttribute User user) {
		userRepository.save(user);
		return "redirect:/user/list";
	}
}
