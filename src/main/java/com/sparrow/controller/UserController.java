package com.sparrow.controller;

import com.sparrow.domain.Role;
import com.sparrow.domain.User;
import com.sparrow.domain.UserCreateForm;
import com.sparrow.repository.UserRepository;
import com.sparrow.service.RoleService;
import com.sparrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private UserRepository userRepository;
	private UserService userService;
	private RoleService roleService;

	@Autowired
	public UserController(UserRepository userRepository, UserService userService,RoleService roleService) {
		this.userRepository = userRepository;
		this.userService = userService;
		this.roleService = roleService;
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
		//获取角色列表
		List<Role> roleList=roleService.getList();
		model.addAttribute("roleList",roleList);
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
	public String userAdd(@ModelAttribute User user, @Validated UserCreateForm userCreateForm) {
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
		//获取角色列表
		List<Role> roleList=roleService.getList();
		model.addAttribute("roleList",roleList);
		return "admin/userEdit";
	}

	/**
	 * 用户修改提交操作
	 * @author:贤名
	 * @createDate:2017-03-28
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/edit")
	public String userSubmit(final @ModelAttribute User user,final  @Validated UserCreateForm userCreateForm, BindingResult result,final ModelMap model) {
		if (result.hasErrors()) {
			return "/user/edit";
		}else{
			userRepository.save(user);
		}
		model.clear();
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/findUserByName")
	@ResponseBody
	public Object userEdit(@RequestParam String username,@RequestParam String originalName) {
		if(!StringUtils.isEmptyOrWhitespace(username)&&!username.equals(originalName)){
			User user=userRepository.findByUsername(username);
			if(null==user){
				return true;
			}
		}else{
			return true;
		}
		return false;
	}

}
