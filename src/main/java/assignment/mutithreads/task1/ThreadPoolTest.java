package assignment.mutithreads.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPoolTest {
	
	private static Logger logger = LoggerFactory.getLogger(WorkRejectedExceptionHandler.class);
	
	public static void main(String[] args) {
		
		logger.info("======Main Method started========");
		WorkThreadPoolExecutor executor = new WorkThreadPoolExecutor();
		for(int i=0;i<100;i++) {
			executor.execute(new Task(i));
		}
		executor.shutdown();
		logger.info("======Main Method end========");
	}

}

class Task implements Runnable{
	
	private Logger logger=LoggerFactory.getLogger(Task.class);
	
	private int id;

	public Task(int id) {
		this.id=id;
	}
	
	@Override
	public void run() {
		logger.info("Task-{} is started",id);
		try {
			Thread.sleep(2000);
		}catch (Exception e) {
			logger.error("Exception in Task {}",e);
		}
		logger.info("Task-{} is end",id);
	}
	
	
}
