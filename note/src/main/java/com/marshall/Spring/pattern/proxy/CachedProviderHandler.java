package com.marshall.Spring.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaojie.hou on 2017/9/11.
 *
 * 添加缓存功能的动态代理类
 */
public class CachedProviderHandler implements InvocationHandler {

	private Map<String, Object> cached = new HashMap<>();
	private Object target;

	public CachedProviderHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Type[] types = method.getParameterTypes();
		if (method.getName().matches("get.+") && (types.length == 1) &&
				(types[0] == String.class)) {
			String key = (String) args[0];
			Object value = cached.get(key);
			if (value == null) {
				value = method.invoke(target, args);
				cached.put(key, value);
			}
			return value;
		}
		return method.invoke(target, args);
	}
}
