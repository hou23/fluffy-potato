package com.marshall.jcip.ch03;

import net.jcip.annotations.NotThreadSafe;

/**
 * Created by yaojie.hou on 2018/3/7.
 *
 * 非线程安全的数据访问器
 */
@NotThreadSafe
public class MutableInteger {

	private int value;

	public int get() {
		return value;
	}

	public void set(int value) {
		this.value = value;
	}

}
