package concurrency;

/**
 * @author yaojie.hou
 * @create 2018/7/10
 */
public class DaemonDemo {

	public static void main(String[] args) {
		Thread daemonThread = new Thread(() -> {
			while (true) {
				try {
					System.out.println("i am alive");
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					// 守护线程在退出时不会执行finally中的代码
					System.out.println("finally block");
				}
			}
		});

		daemonThread.setDaemon(true);
		daemonThread.start();

		// 确保main线程结束前能给daemonThread分到时间片
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
