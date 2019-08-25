package exercise.exception;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionTest {
	
	public static void main(String[] args) {
		
		Logger logger = LoggerFactory.getLogger(ExceptionTest.class);
		/**
		 * 子异常优先处理，不然子异常永远无法catch
		 * 
		 * 对于异常，99%都无法处理，请将异常抛出去
		 * 
		 * 如果逻辑中存在需要显式处理的Checked异常，使用统一的异常处理类:ExceptionHandle
		 * 
		 */
		try {
			throw new NullPointerException("Null");
		}catch (NullPointerException e) {
			/**
			 * 标准的异常记录方式
			 */
			logger.error("Null Point Exception",e);
		}catch (Exception e) {
			logger.error("Null Point Exception",e);
		}finally {
			/**
			 * 必须的块，用于处理资源的释放
			 * 
			 */
			logger.info("finally is needed");
		}
	}

}

class ExceptionHandler{
	
	public static void handle(Throwable e) {
		if (e instanceof IOException) {
			
		}
		
	}
	
}
