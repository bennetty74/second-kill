package exercise.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientByRunnable implements Runnable{

	private Logger logger=LoggerFactory.getLogger(ClientByRunnable.class);
	
	@Override
	public void run() {
		logger.info(Thread.currentThread().getName());
		logger.info("Thread started");
		try {
			Thread.sleep(1000*30);
		}catch (Exception e) {
			logger.info("Thread exception",e);
		}
		logger.info("Thread end");
	}
	
	

}
