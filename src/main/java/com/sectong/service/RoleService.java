package com.sectong.service;

import com.sectong.domain.Role;
import com.sectong.domain.RoleCreateForm;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色接口
 * @createDate 2017-03-28
 * @author 郑云飞
 *
 */
public interface RoleService {
	Role create(RoleCreateForm form);
	Role getRoleByRolename(String roleName);
	Object listAllRoles(Pageable p);
	Object getRoleList(int current, int rowCount, String searchPhrase,HttpServletRequest request);
}
