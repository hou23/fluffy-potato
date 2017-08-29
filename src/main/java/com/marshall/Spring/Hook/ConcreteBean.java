package com.marshall.Spring.Hook;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by yaojie.hou on 2017/8/24.
 *
 * 当一个Bean实现InitializingBean， DisposableBean时
 * 可以在初始化/销毁时添加一些自定义的操作
 */
public class ConcreteBean implements InitializingBean, DisposableBean {

	/**
	 * 可以添加自定义的一些销毁方法或者资源释放操作
	 * 单例销毁时由BeanFactory调用
	 * @throws Exception
	 */
	@Override
	public void destroy() throws Exception {
		System.out.println("释放资源");
	}

	/**
	 * 可以添加自定义的初始化方法或者做一些资源初始化操作
	 * 当BeanFactory设置完所有的Bean属性之后才会被调用
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("初始化资源");
	}
}
