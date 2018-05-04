package com.marshall.spring_security.service.impl;

import com.marshall.spring_security.constant.RoleConstant;
import com.marshall.spring_security.entity.UserEntity;
import com.marshall.spring_security.mapper.UserMapper;
import com.marshall.spring_security.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author yaojie.hou
 * @create 2018/5/3
 */
@Service
@Primary
//@Log4j
public class BaseUserService implements UserService {

	private final UserMapper userMapper;

	public BaseUserService(UserMapper userMapper){
		this.userMapper = userMapper;
	}

	@Override
	public boolean insert(UserEntity userEntity) {
		String username = userEntity.getUsername();
		if (exist(username)) {
			return false;
		}
		encryptPassword(userEntity);
		userEntity.setRoles(RoleConstant.ROLE_USER);
		int result = userMapper.insert(userEntity);
		return  result == 1;
	}

	@Override
	public UserEntity getByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

	/**
	 * 判断用户是否存在
	 * @param username 账号
	 * @return 密码
	 */
	private boolean exist(String username){
		UserEntity userEntity = userMapper.selectByUsername(username);
		return (userEntity != null);
	}

	/**
	 * 加密密码
	 */
	private void encryptPassword(UserEntity userEntity){
		String password = userEntity.getPassword();
		password = new BCryptPasswordEncoder().encode(password);
		userEntity.setPassword(password);
	}
}
