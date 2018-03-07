package com.marshall.jcip.ch03;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by yaojie.hou on 2018/3/7.
 *
 * 线程安全的数据访问器
 */
@ThreadSafe
public class SynchronizedInteger {

	@GuardedBy("this") private int value;

	public synchronized int get() {
		return value;
	}

	public synchronized void set(int value) {
		this.value = value;
	}

}
