package assignment.logandexception;

import java.io.IOException;

/**
 * Used to save log to file and output in console
 * 
 * @author Bennetty74
 *
 */
public interface IFileLog {
	/**
	 * 日志提示信息
	 * 
	 * @param msg
	 */
	public void debug(String msg, String filePath) throws IOException;

	/**
	 * 日志提示
	 * 
	 * @param msg
	 * @param t
	 */
	public void debug(String msg, Throwable t, String filePath) throws IOException;

	/**
	 * 日志提示
	 * 
	 * @param msg  提示信息
	 * @param args 提示信息参数
	 */
	public void debug(String msg, String filePath, Object... args) throws IOException;

	/**
	 * 日志提示信息
	 * 
	 * @param msg
	 */
	public void info(String msg, String filePath)throws IOException;

	/**
	 * 日志提示
	 * 
	 * @param msg
	 * @param t
	 */
	public void info(String msg, Throwable t, String filePath)throws IOException;

	/**
	 * 日志提示
	 * 
	 * @param msg  提示信息
	 * @param args 提示信息参数
	 */
	public void info(String msg, String filePath, Object... args)throws IOException;

	/**
	 * 日志警告信息
	 * 
	 * @param msg
	 */
	public void warn(String msg, String filePath)throws IOException;

	/**
	 * 日志警告
	 * 
	 * @param msg
	 * @param t
	 */
	public void warn(String msg, Throwable t, String filePath)throws IOException;

	/**
	 * 日志警告
	 * 
	 * @param msg  提示信息
	 * @param args 提示信息参数
	 */
	public void warn(String msg, String filePath, Object... args)throws IOException;

	/**
	 * 日志错误信息
	 * 
	 * @param msg
	 */
	public void error(String msg, String filePath)throws IOException;

	/**
	 * 日志错误
	 * 
	 * @param msg
	 * @param t
	 */
	public void error(String msg, Throwable t, String filePath)throws IOException;

	/**
	 * 日志错误
	 * 
	 * @param msg  提示信息
	 * @param args 提示信息参数
	 */
	public void error(String msg, String filePath, Object... args)throws IOException;
}
