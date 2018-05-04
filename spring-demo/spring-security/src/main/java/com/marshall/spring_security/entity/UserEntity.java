package com.marshall.spring_security.entity;

import lombok.Data;

/**
 * @author yaojie.hou
 * @create 2018/5/3
 */
@Data
public class UserEntity {

	private Long id;

	/**
	 * 账号
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 权限
	 */
	private String roles;
}
