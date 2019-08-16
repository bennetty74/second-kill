package assignment.mutithreads.task1;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用于new Thread
 * @author Bennetty74
 *
 */
public class WorkThreadFactory implements ThreadFactory{
	
	/**
	 * 线程ID
	 */
	private AtomicInteger count = new AtomicInteger(1);
	/**
	 * 线程名称的前缀
	 */
	private static final String THREAD_PREFIX="TestThread-"; 

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r,THREAD_PREFIX+count.getAndAdd(1));
	}
	
//	public void increase() {
//		count.addAndGet(1);	
//	}
	
}
