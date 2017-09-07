package com.marshall.Spring.pattern.proxy;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by yaojie.hou on 2017/9/7.
 */
public class BeforeConstructAdvice implements MethodBeforeAdvice{
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		if (method.getName().equals("construct")) {
			((Construction) target).givePermission();
		}
	}
}
