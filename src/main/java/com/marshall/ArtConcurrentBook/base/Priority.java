package com.marshall.ArtConcurrentBook.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yaojie.hou on 2017/5/31.
 *
 * 使用Priority来控制线程优先级
 * 操作系统可以完全不用理会线程优先级的设定
 */
public class Priority {
	private static volatile boolean notStart = true;
	private static volatile boolean notEnd   = true;

	public static void main(String[] args) throws Exception {
		List<Job> jobs = new ArrayList<Job>();
		for (int i = 0; i < 10; i++) {
			int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
			Job job = new Job(priority);
			jobs.add(job);
			Thread thread = new Thread(job, "Thread:" + i);
			thread.setPriority(priority);
			thread.start();
		}
		notStart = false;
		Thread.currentThread().setPriority(8);
		System.out.println("done.");
		TimeUnit.SECONDS.sleep(10);
		notEnd = false;

		for (Job job : jobs) {
			System.out.println("Job Priority : " + job.priority + ", Count : " + job.jobCount);
			/**
			 优先级1和10的Job计数相近，线程优先级没有生效
			 Job Priority : 1, Count : 18579621
			 Job Priority : 1, Count : 19614825
			 Job Priority : 1, Count : 20147184
			 Job Priority : 1, Count : 20164684
			 Job Priority : 1, Count : 19925831
			 Job Priority : 10, Count : 19623289
			 Job Priority : 10, Count : 19876104
			 Job Priority : 10, Count : 19998762
			 Job Priority : 10, Count : 19755753
			 Job Priority : 10, Count : 20194844
			 */
		}

	}

	static class Job implements Runnable {
		private int  priority;
		private long jobCount;

		public Job(int priority) {
			this.priority = priority;
		}

		public void run() {
			while (notStart) {
				Thread.yield();
			}
			while (notEnd) {
				Thread.yield();
				jobCount++;
			}
		}
	}
}
