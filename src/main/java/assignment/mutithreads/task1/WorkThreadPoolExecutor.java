package assignment.mutithreads.task1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkThreadPoolExecutor extends ThreadPoolExecutor{
	
	private Logger logger = LoggerFactory.getLogger(WorkThreadPoolExecutor.class);
	
	/**
	 * 核心线程池大小
	 */
	private final static int DEFAULT_CORE_POOL_SIZE=10;
	
	/**
	 * 线程池大小
	 */
	private final static int DEFAULT_MAX_POOL_SIZE=30;
	
	/**
	 * 工作队列最大深度
	 */
	private final static int DEFAULT_MAX_QUEUE_DEPTH=50;
	
	

	/**
	 * 默认线程池构造函数
	 */
	public WorkThreadPoolExecutor() {
		this(DEFAULT_CORE_POOL_SIZE, DEFAULT_MAX_POOL_SIZE, 10, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(DEFAULT_MAX_QUEUE_DEPTH), new WorkThreadFactory(), new WorkRejectedExceptionHandler());
	}
	
	
	public WorkThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
	}

}
