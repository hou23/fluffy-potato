package com.marshall.ArtConcurrentBook.base.CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yaojie.hou on 2017/6/7.
 *
 * 演示CountDownLatch用法的实体类
 */
public class Player implements Runnable {
	private int id;
	private CountDownLatch begin;
	private CountDownLatch end;

	public Player(int id, CountDownLatch begin, CountDownLatch end) {
		this.id = id;
		this.begin = begin;
		this.end = end;
	}

	@Override
	public void run() {
		try {
			begin.await();
			Thread.sleep((long) (Math.random() * 100));
			System.out.println("Player" + id + " arrived");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//使end状态减1，最终减至0
			end.countDown();
		}
	}
}
