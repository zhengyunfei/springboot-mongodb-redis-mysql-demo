package com.sparrow.service;

import com.sparrow.domain.Role;
import com.sparrow.domain.RoleCreateForm;
import com.sparrow.domain.User;
import com.sparrow.repository.AuthorityRepository;
import com.sparrow.repository.RoleRepository;
import com.sparrow.repository.UserRepository;
import com.sparrow.utils.ServerResourcesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 新闻接口实现
 *
 * @author 郑云飞
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;
	@Autowired
	private Environment env;
	/**
	 * roleRepository
	 * @param roleRepository
	 * @param roleRepository
	 */
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository, AuthorityRepository authorityRepository,UserRepository userRepository) {
		this.roleRepository = roleRepository;
		this.authorityRepository = authorityRepository;
		this.userRepository = userRepository;
	}

	/**
	 * 查找用户名
	 */
	@Override
	public Role getRoleByRolename(String roleName) {
		LOGGER.debug("Getting user by username={}", roleName);
		return roleRepository.getRoleByRolename(roleName);
	}

	/**
	 * 创建新用户
	 */
	@Override
	public Role create(RoleCreateForm form) {
		Role role = new Role();
		role.setRolename(form.getRolename());
		role.setRoledesc(form.getRoledesc());
		role.setIsactive(form.getIsactive());
		role.setRoleno(form.getRoleno());
		User user = getCurrentUser();
		role.setUser(user);
		role.setCreatetime(new Date());
		return roleRepository.save(role);
	}
	@Override
	public Object listAllRoles(Pageable p) {
		Page<Role> roles = roleRepository.findAll(p);
		return roles;
	}
	/**
	 * 获取当前登录用户名称
	 */
	public String getCurrentUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		return name;
	}
	/**
	 * 获取当前用户实例
	 */
	public User getCurrentUser() {
		return userRepository.findByUsername(getCurrentUsername());
	}

	public Object listAllUsers(Pageable p) {
		Page<Role> users = roleRepository.findAll(p);
		return users;
	}

	@Override
	public Object getRoleList(int current, int rowCount, String searchPhrase,HttpServletRequest request) {
		HashMap<String, Object> ret = new HashMap<String, Object>();
		ArrayList<Object> pList = new ArrayList<Object>();
		Long total = roleRepository.count();
		int i = 0;

		if (rowCount == -1) {
			rowCount = new Long(total).intValue();
		}
		Page<Role> roles = roleRepository.findByRolenameContaining(searchPhrase,
				new PageRequest(current - 1, rowCount));
		for (Role role : roles) {
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("id", role.getId());
			data.put("rolename", role.getRolename());
			data.put("roleDesc", role.getRoledesc());
			data.put("roleNo", role.getRolename());
			if (role.getIsactive() == 1) {
				data.put("enabled", "<font color='green'>启用</font>");
			} else {
				data.put("enabled", "<font color='red'>禁用</font>");
			}

			String domain= ServerResourcesUtil.getCurrentDomainUrl(request);
			data.put("commands","<a href='"+domain+"/role/edit?id="+role.getId()+"'><font color='green'>编辑</font></a> <a href='"+domain+"/role/del?id="+role.getId()+"'><font color='red'>删除</font></a> ");
			pList.add(data);
			i++;
		}

		ret.put("current", current);
		ret.put("rowCount", i);
		ret.put("rows", pList);
		ret.put("total", total);

		return ret;
	}
}
