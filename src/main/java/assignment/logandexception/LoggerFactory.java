package assignment.logandexception;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * 日志工厂方法，所有需要日志记录的都使用此工厂获取
 * @author wanglw2
 *
 */
public class LoggerFactory {
	 
	/**
	 * 获取日志记录的工厂方法
	 * @param clazz
	 * @return
	 */
	public static ILog getLogger(Class<?> clazz){
		ILog log = new SimpleLogger(clazz);
		return log;
	}
	
	public static ILog getFileLogger(ILog logger,Class<?> clazz) throws FileNotFoundException, UnsupportedEncodingException {
		return new FileAndConsoleLogger(logger,clazz);
	}
	
	public static void main(String[] a){
		String usercode = "测试";
		//无任何意义，对分析解决问题无任何帮助
		System.out.println("------------------------------------------");
		//日志记录应使用统一的接口，不能对字符串进行直接拼接
		System.out.println("开始计算用户：["+usercode+"]的薪资");
		System.out.println();
		System.out.println();
		System.out.println();
		ILog log = LoggerFactory.getLogger(LoggerFactory.class);
		
		log.debug("开始计算用户：[{}]的薪资", usercode); //准确
		log.info("开始计算用户：["+usercode+"]的薪资"); //错误
		
		try{
			throw new NullPointerException("null");
		}catch(Exception e){
			//错误，需要使用统一的接口
			e.printStackTrace();
			System.out.println("--1--");
			//错误，丢失异常堆栈信息
			log.error(e.getMessage());
			System.out.println("--2--");
			//错误，丢失异常堆栈信息
			log.error("error", e.getMessage());
			System.out.println("--3--");
			//正确
			log.error("error",e);
		}
		
	}
}
