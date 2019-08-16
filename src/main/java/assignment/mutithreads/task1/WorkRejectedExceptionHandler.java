package assignment.mutithreads.task1;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkRejectedExceptionHandler implements RejectedExecutionHandler{
	
	private Logger logger = LoggerFactory.getLogger(WorkRejectedExceptionHandler.class);

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		logger.error("Thread pool is overflow: {}",executor.toString());
		/**
		 * 调用线程处理，即产生和分发任务的线程
		 */
		r.run();
	}

}
