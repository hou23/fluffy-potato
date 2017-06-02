package com.marshall.concurrent.base;

/**
 * Created by yaojie.hou on 2017/6/2.
 */
public class Join {

	public static void main(String[] args) {

	}

	static class Domino implements Runnable {

		private Thread thread;

		public Domino(Thread thread) {
			this.thread = thread;
		}

		@Override
		public void run() {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " terminate.");
		}
	}
}
