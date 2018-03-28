package art_concurrent_book.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yaojie.hou on 2017/6/7.
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

	// 线程池大小
	private static final int MAX_WORKER_NUMBERS = 10;
	private static final int DEFAULT_WORKER_NUMBERS = 5;
	private static final int MIN_WORKER_NUMBERS = 1;

	// 工作列表，将会向里面插入工作
	private final LinkedList<Job> jobs = new LinkedList<>();
	// 工作者列表
	private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());
	// 工作者线程的数量
	private int workerNum = DEFAULT_WORKER_NUMBERS;
	// 线程编号
	private AtomicLong threadNum = new AtomicLong();

	public DefaultThreadPool() {
	}

	public DefaultThreadPool(int workerNum) {
		this.workerNum = workerNum > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS :
				workerNum < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : workerNum;
	}

	private void initializeWorkers(int workerNum) {
		for (int i = 0; i < workerNum; i++) {
			Worker worker = new Worker();
			workers.add(worker);
			Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
			thread.start();
		}
	}

	@Override
	public void execute(Job job) {
		if(job != null) {
			// 添加一个工作，然后进行通知
			synchronized (jobs) {
				jobs.addLast(job);
				jobs.notify();
			}
		}
	}

	@Override
	public void shutdown() {
		for (Worker worker : workers) {
			worker.shutdown();
		}
	}

	@Override
	public void addWorkers(int num) {
		synchronized (jobs) {
			// 限制新增的Worker数量不能超过最大值
			if(num + this.workerNum > MAX_WORKER_NUMBERS) {
				num = MAX_WORKER_NUMBERS - this.workerNum;
			}
			initializeWorkers(num);
			this.workerNum += num;
		}
	}

	@Override
	public void removeWorker(int num) {
		synchronized (jobs) {
			if (num >= this.workerNum) {
				throw new IllegalArgumentException("beyond workNum");
			}

			int count = 0;
			while (count < num) {
				workers.get(count).shutdown();
				count++;
			}
			this.workerNum -= count;
		}
	}

	@Override
	public int getJobSize() {
		return jobs.size();
	}

	class Worker implements Runnable {

		private volatile boolean running = true;

		@Override
		public void run() {
			while (running) {
				Job job = null;
				synchronized (jobs) {
					// 如果工作列表是空的，那么就wait
					while (jobs.isEmpty()) {
						try {
							jobs.wait();
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
							return;
						}
					}
					// 取出一个job
					job = jobs.removeFirst();
				}
				if(job != null) {
					try {
						job.run();
					} catch (Exception e) {
					}
				}
			}
		}

		public void shutdown() {
			running = false;
		}
	}
}
