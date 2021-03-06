package java_concurrency_in_practice.ch03;

/**
 * Created by yaojie.hou on 2018/3/7.
 */
public class NoVisibility {

	private static boolean ready;
	private static int number;

	private static class ReaderThread extends Thread {

		@Override
		public void run() {
			while (!ready) {
				Thread.yield();
			}
			System.out.println(number);
		}
	}

	public static void main(String[] args) {
		new ReaderThread().start();
		number = 42;
		ready = true;
	}

}
