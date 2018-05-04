package com.marshall.spring_security.service;

import com.marshall.spring_security.entity.UserEntity;

/**
 * @author yaojie.hou
 * @create 2018/5/3
 */
public interface UserService {

	/**
	 * 添加新用户
	 *
	 * username 唯一， 默认 USER 权限
	 */
	boolean insert(UserEntity userEntity);

	/**
	 * 查询用户信息
	 * @param username 账号
	 * @return UserEntity
	 */
	UserEntity getByUsername(String username);
}
