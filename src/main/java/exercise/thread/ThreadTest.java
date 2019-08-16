package exercise.thread;

public class ThreadTest {
	
	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new ClientByRunnable(),"Runnable Thread");
		Thread thread2 = new ClientByThread("Thread thread");
		thread1.start();
		thread2.start();
	}

}
