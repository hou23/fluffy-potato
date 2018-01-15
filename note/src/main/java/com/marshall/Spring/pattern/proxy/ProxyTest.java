package com.marshall.Spring.pattern.proxy;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

import java.awt.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by yaojie.hou on 2017/9/7.
 *
 * 代理模式
 */
public class ProxyTest {

	@Test
	public void test1() {
		ProxyFactory factory = new ProxyFactory(new House());
		//factory.addInterface(Construction.class);
		factory.addAdvice(new BeforeConstructAdvice());
		factory.setExposeProxy(true);
		Construction construction = (Construction) factory.getProxy();
		construction.construct();
		assertTrue("Construction is illegal. "
				+"Supervisor didn't give a permission to build "
				+"the house", construction.isPermitted());
	}

	@Test
	public void test2() {
		FontProvider fontProvider = ProviderFactory.getFontProvider();
		Font monospaced = fontProvider.getFont("Monospaced");
		System.out.println(monospaced);
	}
}
