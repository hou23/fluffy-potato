package com.marshall.ArtConcurrentBook.base.CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yaojie.hou on 2017/6/7.
 *
 * 演示CountDownLatch用法，当计数减至0时触发特定的事件
 * 利用这种特性，可以让主线程等待子线程的结束
 */
public class CountDownLatchDemo {
	private static final int PLAYER_AMOUNT = 50;

	public CountDownLatchDemo() {
	}

	public static void main(String[] args) {
		CountDownLatch begin = new CountDownLatch(1);
		CountDownLatch end = new CountDownLatch(PLAYER_AMOUNT);

		Player[] players = new Player[PLAYER_AMOUNT];
		for (int i = 0; i < PLAYER_AMOUNT; i++) {
			players[i] = new Player(i + 1, begin, end);
		}

		ExecutorService exe = Executors.newFixedThreadPool(PLAYER_AMOUNT);
		for (Player p : players) {
			exe.execute(p);
		}

		System.out.println("Race begin !!!");
		// begin -1 Player线程才能启动
		begin.countDown();
		try {
			// 等待所有Player线程执行完成 end =0 再结束
			end.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Race end !!!");
		}
		exe.shutdown();
	}
}
