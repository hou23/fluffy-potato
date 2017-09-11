package com.marshall.Spring.pattern.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by yaojie.hou on 2017/9/11.
 *
 * 字体提供的抽象工厂类，主要提供对象的创建功能
 */
public abstract class ProviderFactory {

	public static FontProvider getFontProvider() {
		// 普通工厂
		//return new FontProviderFromDisk();

		// 静态代理实现缓存功能
		//return new CachedFontProvider(new FontProviderFromDisk());

		// 动态代理
		Class<FontProvider> targetClass = FontProvider.class;
		return (FontProvider) Proxy.newProxyInstance(targetClass.getClassLoader(),
				new Class[] {targetClass},
				new CachedProviderHandler(new FontProviderFromDisk()));
	}
}
