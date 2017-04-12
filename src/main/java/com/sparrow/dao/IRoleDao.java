package com.sparrow.dao;

import com.sparrow.dao.support.IBaseDao;
import com.sparrow.domain.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDao extends IBaseDao<Role, Integer> {

}
