package art_concurrent_book.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yaojie.hou on 2017/6/8.
 * 演示Lock的使用，Lock相对于synchronized来说，
 * 可以自定义获取、释放锁的时机，拥有更多的扩展性
 */
public class LockUseCase {
	public void lock() {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
		} finally {
			lock.unlock();
		}
	}
}
