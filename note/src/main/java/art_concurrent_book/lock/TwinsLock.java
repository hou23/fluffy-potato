package art_concurrent_book.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by yaojie.hou on 2017/6/19.
 */
public class TwinsLock implements Lock {

	private final Sync sync = new Sync(2);
	private static final class Sync extends AbstractQueuedSynchronizer {

		Sync(int count) {
			setState(count);
		}

		@Override
		protected int tryAcquireShared(int reduceCount) {
			for (;;) {
				int currentCount = getState();
				int newCount = currentCount - reduceCount;
				if(newCount < 0 || compareAndSetState(currentCount, newCount)) {
					return newCount;
				}
			}
		}

		@Override
		protected boolean tryReleaseShared(int returnCount) {
			for (;;) {
				int currentCount = getState();
				int	newCount = currentCount + returnCount;
				if(compareAndSetState(newCount, currentCount)) {
					return true;
				}
			}
		}

		final ConditionObject newCondition() {
			return new ConditionObject();
		}
	}

	@Override
	public void lock() {
		sync.acquireShared(1);
	}

	@Override
	public void unlock() {
		sync.releaseShared(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireSharedInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquireShared(1) >= 0;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}
}
