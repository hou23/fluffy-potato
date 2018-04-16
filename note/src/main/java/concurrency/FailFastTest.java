package concurrency;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yaojie.hou
 * @create 2018/4/2
 */
public class FailFastTest {

	private static List<Integer> list = new CopyOnWriteArrayList<>();

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		new thread1().start();
		new thread2().start();
	}

	private static class thread1 extends Thread {
		@Override
		public void run() {
			Iterator<Integer> iterator = list.iterator();
			while (iterator.hasNext()) {
				int i = iterator.next();
				System.out.println("thread 1 run: " + i);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class thread2 extends Thread {
		@Override
		public void run() {
			int i = 0;
			while (i < 6) {
				System.out.println("thread 2 run: " + i);
				if (i == 3) {
					list.remove(i);
				}
				i++;
			}
		}
	}
}
