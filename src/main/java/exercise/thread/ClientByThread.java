package exercise.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientByThread extends Thread{
	
	private Logger logger=LoggerFactory.getLogger(ClientByThread.class);
	
	public ClientByThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		logger.info(Thread.currentThread().getName());
		logger.info("Thread started");
		try {
			Thread.sleep(1000*10);
			int j=0;
			for(int i=0;i<40000000;i++) {
				j=i+j;
			}
		
			synchronized (logger) {
				logger.wait();
			}
		}catch (Exception e) {
			logger.info("Thread exception",e);
		}
		logger.info("Thread end");
	}
	
	

}
