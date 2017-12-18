package com.marshall.design_patterns.singleton;

/**
 * Created by yaojie.hou on 2017/12/4.
 */
public class Singleton {

	private Singleton() {}

	private volatile static Singleton instance = null;

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}

		return instance;
	}
}
