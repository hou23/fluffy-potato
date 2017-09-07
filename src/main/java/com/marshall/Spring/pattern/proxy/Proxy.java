package com.marshall.Spring.pattern.proxy;

import org.springframework.aop.framework.ProxyFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by yaojie.hou on 2017/9/7.
 *
 * 代理模式
 */
public class Proxy {
	public static void main(String[] args) {
		ProxyFactory factory = new ProxyFactory(new House());
		factory.addInterface(Construction.class);
		factory.addAdvice(new BeforeConstructAdvice());
		factory.setExposeProxy(true);
		Construction construction = (Construction) factory.getProxy();
		construction.construct();
		assertTrue("Construction is illegal. "
				+"Supervisor didn't give a permission to build "
				+"the house", construction.isPermitted());
	}
}
