package assignment.bigtask.utils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientLock {

	private static Lock lock = new ReentrantLock();

	private static Condition condition = lock.newCondition();

	public static void lock() {
		lock.tryLock();
	}

	public static void unlock() {
		lock.unlock();
	}

	public static void await() {
		lock();
		try {
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			unlock();
		}
	}

	public static void signalAll() {
		lock();
		try {
			condition.signalAll();
		} finally {
			unlock();
		}
	}

}
