package assignment.logandexception;

import java.io.IOException;

public class LogTest {
	
	public static void main(String[] args) throws IOException {
		
		String filePath = "E:/log.txt";
		ILog logger = LoggerFactory.getLogger(LogTest.class);
		ILog fileLogger = LoggerFactory.getFileLogger(logger, LogTest.class);
		
		/**
		 * info test
		 */
		fileLogger.info("This is a test",filePath);
		fileLogger.info("This is an object test {}",filePath,"Object");
		fileLogger.info("This a throwable test", new NullPointerException("Null"));
		/**
		 * debug test
		 */
		fileLogger.debug("This is a test",filePath);
		fileLogger.debug("This is an object test {}",filePath,"Object");
		fileLogger.debug("This an throwable test", new NullPointerException("Null"));
		
		/**
		 * warn test
		 */
		fileLogger.warn("This is a test",filePath);
		fileLogger.warn("This is an object test {}",filePath,"Object");
		fileLogger.warn("This a throwable test", new NullPointerException("Null"));
		
		/**
		 * error test
		 */
		fileLogger.error("This is a test",filePath);
		fileLogger.error("This is an object test {}",filePath,"Object");
		fileLogger.error("This a throwable test", new NullPointerException("Null"));
	}

}
