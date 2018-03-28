package art_concurrent_book.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yaojie.hou on 2017/6/23.
 */
public class ConditionUseCase {
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	public void conditionWait() throws InterruptedException {
		lock.lock();
		try {
			condition.await();
		} finally {
			lock.unlock();
		}
	}

	public void conditionSignal() {
		lock.lock();
		try {
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}
