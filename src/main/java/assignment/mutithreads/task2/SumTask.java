package assignment.mutithreads.task2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SumTask extends RecursiveTask<Double> {

	private static final long serialVersionUID = 1L;

	static final int THRESHOLD = 1000000 * 9;
	List<Double> values;
	int start;
	int end;
	FileChannel channel;
	RandomAccessFile randomAccessFile;

	private static Logger logger = LoggerFactory.getLogger(SumTask.class);

	public SumTask(List<Double> values, int start, int end) {
		this.values = values;
		this.start = start;
		this.end = end;
	}

	public SumTask(int start, int end) throws IOException {
		randomAccessFile = new RandomAccessFile(SumTest.DEFAULT_FILE_PATH, "r");
		channel = randomAccessFile.getChannel();
		this.start = start;
		this.end = end;
	}

	@Override
	protected Double compute() {

		// 如果任务足够小,直接计算:
		if (end - start <= THRESHOLD) {
			Double sum = 0.00;
			try {
				randomAccessFile.seek(start);
				for (long i = start; i < end;) {
					sum += randomAccessFile.readDouble();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			logger.info("Sub Sum is {}",sum);
			return sum;
		}

		// 任务太大,一分为二:
		SumTask subtask1 = null;
		SumTask subtask2 = null;
		int middle = (end + start) / 2;
		try {
			subtask1 = new SumTask(start, middle);
			subtask2 = new SumTask(middle+1, end);
		} catch (Exception e) {
			// TODO: handle exception
		}
		invokeAll(subtask1, subtask2);
		Double subresult1 = subtask1.join();
		Double subresult2 = subtask2.join();
		Double result = subresult1 + subresult2;
		return result;
	}
}