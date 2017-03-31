package com.sparrow.controller;

import com.sparrow.domain.Role;
import com.sparrow.domain.User;
import com.sparrow.repository.RoleRepository;
import com.sparrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

	private RoleRepository roleRepository;
	private UserService userService;

	@Autowired
	public RoleController(RoleRepository roleRepository, UserService userService) {
		this.roleRepository = roleRepository;
		this.userService = userService;
	}

	/**
	 * 角色管理
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String adminNews(Model model) {
		model.addAttribute("role", true);
		Iterable<Role> rolelist = roleRepository.findAll();
		model.addAttribute("rolelist", rolelist);
		return "admin/roles";
	}

	/**
	 * 角色新增表单
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/add")
	public String newsAdd(Model model) {
		model.addAttribute("roleAdd", new Role());
		return "admin/roleAdd";
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
		model.addAttribute("roleEdit", roleRepository.findOne(id));
		return "admin/roleEdit";
	}

	/**
	 * 新闻修改提交操作
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param role
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/edit")
	public String newsSubmit(@ModelAttribute Role role) {
		role.setCreatetime(new Date());
		User user = userService.getCurrentUser();
		role.setUser(user);
		roleRepository.save(role);
		return "redirect:/role/list";
	}

	/**
	 * 角色删除操作
	 * @author:郑云飞
	 * @createDate:2017-03-28
	 * @param model
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/del")
	public String delRole(Model model, @RequestParam Long id) {
		roleRepository.delete(id);
		return "redirect:/role/list";

	}

}
