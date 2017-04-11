package com.sparrow.service;

import com.sparrow.domain.Role;
import com.sparrow.domain.RoleCreateForm;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
	List<Role> getList();
}
