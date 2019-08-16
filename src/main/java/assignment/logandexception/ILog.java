package assignment.logandexception;
/**
 * 日志接口
 * @author wanglw2
 *
 */
public interface ILog {
	/**
	 * 日志提示信息
	 * @param msg
	 */
	public void debug(String msg);
	/**
	 * 日志提示
	 * @param msg 
	 * @param t
	 */
	public void debug(String msg, Throwable t);
	/**
	 * 日志提示
	 * @param msg 提示信息
	 * @param args 提示信息参数
	 */
	public void debug(String msg, Object... args);
	
	/**
	 * 日志提示信息
	 * @param msg
	 */
	public void info(String msg);
	/**
	 * 日志提示
	 * @param msg 
	 * @param t
	 */
	public void info(String msg, Throwable t);
	/**
	 * 日志提示
	 * @param msg 提示信息
	 * @param args 提示信息参数
	 */
	public void info(String msg, Object... args); 
	
	/**
	 * 日志警告信息
	 * @param msg
	 */
	public void warn(String msg);
	/**
	 * 日志警告
	 * @param msg 
	 * @param t
	 */
	public void warn(String msg, Throwable t);
	/**
	 * 日志警告
	 * @param msg 提示信息
	 * @param args 提示信息参数
	 */
	public void warn(String msg, Object... args);
	
	
	
	/**
	 * 日志错误信息
	 * @param msg
	 * 
	 * 
	 */
	public void error(String msg);
	/**
	 * 日志错误
	 * @param msg 
	 * @param t
	 */
	public void error(String msg, Throwable t);
	/**
	 * 日志错误
	 * @param msg 提示信息
	 * @param args 提示信息参数
	 */
	public void error(String msg, Object... args);
	
	
}
