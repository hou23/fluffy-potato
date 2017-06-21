package com.marshall.ArtConcurrentBook.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yaojie.hou on 2017/6/21.
 */
public class Cache {
	private static final Map<String, Object> map = new HashMap<>();
	private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private static final Lock r = rwl.readLock();
	private static final Lock w = rwl.writeLock();

	public static Object get(String key) {
		r.lock();
		try {
			return map.get(key);
		} finally {
			r.unlock();
		}
	}

	public static Object put(String key, Object value) {
		w.lock();
		try {
			return map.put(key, value);
		} finally {
			w.unlock();
		}
	}

	public static void clear() {
		w.lock();
		try {
			map.clear();
		} finally {
			w.unlock();
		}
	}
}
