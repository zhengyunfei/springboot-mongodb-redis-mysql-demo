package com.sparrow.service;

import com.sparrow.domain.User;
import com.sparrow.domain.UserCreateForm;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务网接口定义
 * @author 郑云飞
 *
 */
public interface UserService {

	User create(UserCreateForm form);

	User getUserByUsername(String username);

	Object uploadImage(MultipartFile file, HttpServletRequest request);

	User getCurrentUser();

	String getCurrentUsername();

	Object listAllUsers(Pageable p);

	Object getUserList(int current, int rowCount, String searchPhrase,HttpServletRequest request);

}
