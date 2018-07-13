package concurrency;

/**
 * @author yaojie.hou
 * @create 2018/7/10
 */
public class InterruptDemo {

	public static void main(String[] args) {
		Thread sleepThread = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				super.run();
			}
		};

		Thread busyThread = new Thread() {
			@Override
			public void run() {
				while (true) {}
			}
		};

		sleepThread.start();
		busyThread.start();
		sleepThread.interrupt();
		busyThread.interrupt();
		while (sleepThread.isInterrupted()) {}
		System.out.println("sleepThread isInterrupted: " + sleepThread.isInterrupted());
		System.out.println("busyThread isInterrupted: " + sleepThread.isInterrupted());
	}
}
