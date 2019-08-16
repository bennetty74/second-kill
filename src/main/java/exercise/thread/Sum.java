package exercise.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sum {
	
	private int sum=0;
	
	
	public Sum() {
		
	}
	
	public Sum(int sum){
		this.sum=sum;
	}
	
	public int get() {
		return sum;
	}
	
	public synchronized int addAndGet() {
		sum++;
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return sum;
	}

	@Override
	public String toString() {
		return "Sum [sum=" + sum + "]";
	}
	
	
	public static void main(String[] args) {
		
		Logger logger=LoggerFactory.getLogger(Sum.class);
		
		logger.info("Main started");
		
		Sum sum = new Sum();
		
		for(int i=0;i<5;i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					logger.info("Sum add and get {}",sum.addAndGet());
				}
			},"Thread--"+i).start();;
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					logger.info("Sum get {}",sum.get());
				}
			}
		},"Thread--").start();
		
	}
	
}
