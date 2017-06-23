package com.marshall.ArtConcurrentBook.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yaojie.hou on 2017/6/23.
 */
public class ProcessData {
	private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private static final Lock readLock = rwl.readLock();
	private static final Lock writeLock = rwl.writeLock();
	private volatile boolean update = false;

	public void processData() {
		readLock.lock();
		if (!update) {
			readLock.unlock();
			writeLock.lock();
			try {
				if (!update) {
					update = true;
				}
				readLock.lock();
			} finally {
				writeLock.unlock();
			}
		}
		try {
			//
		} finally {
			readLock.unlock();
		}
	}
}
