package assignment.logandexception;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 简单日志实现
 * 直接使用System.out输出
 * @author wanglw2
 *
 */
public class SimpleLogger implements ILog {
	/**
	 * 日志级别
	 */
	private static int logLevel=0;
	static{
		String logL = System.getProperty("LOGLEVEL", "DEBUG");
		logLevel = LogEnum.getLevelByName(logL);
	}
	public SimpleLogger(Class<?> clazz){
		this.clazz = clazz;
	}
	
	/**
	 * 使用日志记录的class
	 */
	private Class<?> clazz;
	/**
	 * 日志文本
	 * @param logType
	 * @param msg
	 * @return
	 */
	private String getLogText(LogEnum logType,String msg){
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS ");
		StringBuffer logSB = new StringBuffer();
		logSB.append(dFormat.format(new Date()));
		logSB.append((clazz==null?"":clazz.getName()+":"));
		logSB.append(logType.getPreLogText()).append("-").append(msg);
		return logSB.toString();
	}
	/**
	 * 根据格式消息获取完整消息内容
	 * 例如：getMsg("证件号：{} 不存在 ","001002") -> "证件号：001002 不存在"
	 * @param msg
	 * @param args
	 * @return
	 */
	private String getMsg(String msg, Object... args){
		for (Object object : args) {
			msg = msg.replaceFirst("\\{\\}", object==null?"null":object.toString());
		}
		return msg;
	}
	
	@Override
	public void info(String msg) {
		if(!showLog(LogEnum.INFO)) return;
		String logT = getLogText(LogEnum.INFO, msg);
		System.out.println(logT);
	}

	@Override
	public void info(String msg, Throwable t) {
		if(!showLog(LogEnum.INFO)) return;
		String logT = getLogText(LogEnum.INFO, msg);
		System.out.println(logT);
		t.printStackTrace(System.err);
	}

	@Override
	public void info(String msg, Object... args) {
		if(!showLog(LogEnum.INFO)) return;
		String logT = getLogText(LogEnum.INFO, getMsg(msg, args));
		System.out.println(logT);
	}

	@Override
	public void warn(String msg) {
		if(!showLog(LogEnum.WARN)) return;
		String logT = getLogText(LogEnum.WARN, msg);
		System.out.println(logT);
	}

	@Override
	public void warn(String msg, Throwable t) {
		if(!showLog(LogEnum.WARN)) return;
		String logT = getLogText(LogEnum.WARN, msg);
		System.out.println(logT);
		t.printStackTrace(System.err);
	}

	@Override
	public void warn(String msg, Object... args) {
		if(!showLog(LogEnum.WARN)) return;
		String logT = getLogText(LogEnum.WARN, getMsg(msg, args));
		System.out.println(logT);
	}

	@Override
	public void error(String msg) {
		if(!showLog(LogEnum.ERROR)) return;
		String logT = getLogText(LogEnum.ERROR, msg);
		System.err.println(logT);
	}

	@Override
	public void error(String msg, Throwable t) {
		if(!showLog(LogEnum.ERROR)) return;
		String logT = getLogText(LogEnum.ERROR, msg);
		System.err.println(logT);
		t.printStackTrace(System.err);
	}

	@Override
	public void error(String msg, Object... args) {
		if(!showLog(LogEnum.ERROR)) return;
		String logT = getLogText(LogEnum.ERROR, getMsg(msg, args));
		System.err.println(logT);
	}

	@Override
	public void debug(String msg) {
		if(!showLog(LogEnum.DEBUG)) return;
		String logT = getLogText(LogEnum.DEBUG, msg);
		System.out.println(logT);
	}

	@Override
	public void debug(String msg, Throwable t) {
		if(!showLog(LogEnum.DEBUG)) return;
		String logT = getLogText(LogEnum.DEBUG, msg);
		System.out.println(logT);
		t.printStackTrace(System.out);
	}

	@Override
	public void debug(String msg, Object... args) {
		if(!showLog(LogEnum.DEBUG)) return;
		String logT = getLogText(LogEnum.DEBUG, getMsg(msg, args));
		System.out.println(logT);
	}
	private boolean showLog(LogEnum logEnum){
		return logEnum.getLogLevel()>=logLevel;
	}
}
