package com.sparrow.repository;

import com.sparrow.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;

/**
 * 角色表Repository定义
 * @author 郑云飞
 *
 */
@RestResource(exported = false)

public interface RoleRepository extends CrudRepository<Role, Long> {


	Collection<Role> findAll();

	Role getRoleByRolename(String rolename);

	Page<Role> findAll(Pageable p);

	Page<Role> findByRolenameContaining(String searchPhrase, Pageable p);

}
