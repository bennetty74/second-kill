package assignment.mutithreads.task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SumTest {

	// 默认文件地址
	public final static String DEFAULT_FILE_PATH = "E:/number.log";
	//存储计算结果的list
	public static List<Double> values = new ArrayList<>();

	private static Logger logger = LoggerFactory.getLogger(SumTest.class);
	
	//单线程计算
	public double sumBySingleThread() throws IOException {
		FileReader reader = new FileReader(DEFAULT_FILE_PATH);
		BufferedReader bufferedReader = new BufferedReader(reader);
		double sum = 0;
		String line = null;
		while (null != (line = bufferedReader.readLine())) {
			sum = sum + Double.parseDouble(line);
		}
		bufferedReader.close();
		return sum;
	}
	
	//多线程计算
	public boolean getNumsFromFile() throws NumberFormatException, IOException {
		FileReader reader = new FileReader(SumTest.DEFAULT_FILE_PATH);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = null;
		while (null != (line = bufferedReader.readLine())) {
			values.add(Double.parseDouble(line));
		}
		bufferedReader.close();
		return true;
	}

	public static void main(String[] args) throws IOException {

		SumTest sumTest = new SumTest();
		
		//单线程测试
		long start = System.currentTimeMillis();
		Double sum = sumTest.sumBySingleThread();
		long end = System.currentTimeMillis();
		logger.info("Single Thread Run time is {} ms", end - start);
		logger.info("Single Sum is {}", sum);

		
		//多线程测试
		start = System.currentTimeMillis();
		// sum=Files.lines(Paths.get(sumTest.DEFAULT_FILE_PATH),StandardCharsets.UTF_8).parallel().mapToDouble(Double::parseDouble).sum();
		sumTest.getNumsFromFile();
		 // 最大并发数4
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		ForkJoinTask<Double> task = new SumTask(0, values.size());
		sum = forkJoinPool.invoke(task);
		end = System.currentTimeMillis();
		logger.info("Mutiple Thread Run time is {} ms", end - start);
		logger.info("Mutiple Sum is {}", sum);
	}

}
