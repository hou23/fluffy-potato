package com.marshall.design_patterns.singleton;

/**
 * Created by yaojie.hou on 2017/12/6.
 *
 * 唯一缺点:并非使用懒加载, 而是在枚举类被加载的时候进行初始化的（饿汉式）
 */
public enum Singleton_enum {
	INSTANCE;

	public void doSomething() {
		System.out.println("enum singleton is running");
	}
}
