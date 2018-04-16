package understanding_the_jvm.ch13;

import java.util.Vector;

/**
 * @author yaojie.hou
 * @create 2018/4/16
 *
 * Java API 中声明自己是线程安全的类, 大多都不是绝对线程安全的.
 * 这里演示线程安全的 Vector 类, 在执行并发操作时, 不使用额外
 * 同步手段, 还是会出现错误
 */
public class VectorTest {

	private static Vector<Integer> vector = new Vector<>();

	public static void main(String[] args) {
		unsafe();
	}

	private static void unsafe() {
		while (true) {
			for (int i = 0; i < 10; i++) {
				vector.add(i);
			}

			Thread removeThread = new Thread(() -> {
				for (int i = 0; i < vector.size(); i++) {
					vector.remove(i);
				}
			});

			Thread printThread = new Thread(() -> {
				for (int i = 0; i < vector.size(); i++) {
					System.out.println(vector.get(i));
				}
			});

			removeThread.start();
			printThread.start();

			while (Thread.activeCount() > 20) {}
		}
	}

	private static void safe() {
		while (true) {
			for (int i = 0; i < 10; i++) {
				vector.add(i);
			}

			Thread removeThread = new Thread(() -> {
				synchronized (vector) {
					for (int i = 0; i < vector.size(); i++) {
						vector.remove(i);
					}
				}
			});

			Thread printThread = new Thread(() -> {
				synchronized (vector) {
					for (int i = 0; i < vector.size(); i++) {
						System.out.println(vector.get(i));
					}
				}
			});

			removeThread.start();
			printThread.start();

			while (Thread.activeCount() > 20) {}
		}
	}
}
