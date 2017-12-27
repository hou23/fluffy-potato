package com.marshall.java;

/**
 * Created by yaojie.hou on 2017/12/21.
 *
 * volatile关键字并不能保证变量的原子性的演示
 */
public class VolatileTest {

	public volatile static int count = 0;

	public static void main(String[] args) {
		// 开启10个线程
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// 每个线程中让count值自增100次
				for (int j = 0; j < 100; j++) {
					count++;
				}
			}).start();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("count=" + count);
	}
}
