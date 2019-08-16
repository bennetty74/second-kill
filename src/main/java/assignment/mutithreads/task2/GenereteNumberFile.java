package assignment.mutithreads.task2;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenereteNumberFile {

	private static final String FILE_PATH = "E:/number.log";

	private static Logger logger = LoggerFactory.getLogger(GenereteNumberFile.class);

	/**
	 * 产生数字 - 存到文件 - 100万个数字以上
	 * 
	 * @throws IOException
	 */
	public static void generateNumberFile() throws IOException {

		logger.info("Generate Number File Started");

		FileWriter writer = null;
		BufferedWriter bufferedWriter = null;
		PrintWriter printWriter=null;
		try {
			writer = new FileWriter(FILE_PATH, true);
			bufferedWriter = new BufferedWriter(writer);
			printWriter=new PrintWriter(bufferedWriter);
			for (int i = 0; i < 10000000; i++) {
				double num = Math.random() * 10;
				String number = String.format("%.2f", num);
				printWriter.println(number);
				printWriter.flush();
			}
		} finally {
			printWriter.close();
		}
		logger.info("Generate Number File End");
	}

	public static void main(String[] args) throws IOException {
		GenereteNumberFile.generateNumberFile();
	}

}
