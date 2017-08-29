package com.marshall.Spring.Hook;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by yaojie.hou on 2017/8/24.
 *
 * Aware接口族：
 * Spring提供了各种Aware接口，方便从上下文中获取当前的运行环境，比较常见的几个子接口有：
 * BeanFactoryAware,BeanNameAware,ApplicationContextAware,EnvironmentAware，BeanClassLoaderAware等
 *
 * 例如：实现ApplicationContextAware接口可以获取ApplicationContext
 */
public class SpringContextAssisor implements ApplicationContextAware{

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextAssisor.applicationContext = applicationContext;
	}

	public static Object getBeanDefinition(String name) {
		return applicationContext.getBean(name);
	}

	public static <T> T getBeanDefinition(String name, Class<T> clazz) {
		return applicationContext.getBean(name, clazz);
	}
}
