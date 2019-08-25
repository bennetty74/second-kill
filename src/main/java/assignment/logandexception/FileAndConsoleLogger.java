package assignment.logandexception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileAndConsoleLogger implements ILog {

	private ILog logger;

	private Class<?> clazz;

	private String filePath;
	
	private File file;

	private FileOutputStream fileOutputStream;
	
	private PrintStream printStream;
	

	private final static String DEFAULT_LOG_FILE_PATH = "E:/log.txt";

	public FileAndConsoleLogger(ILog logger, Class<?> clazz) throws FileNotFoundException, UnsupportedEncodingException {
		this.logger = logger;
		this.clazz = clazz;
		this.filePath = DEFAULT_LOG_FILE_PATH;
		this.file=new File(filePath);
		this.fileOutputStream = new FileOutputStream(file,true);
		this.printStream=new PrintStream(fileOutputStream);
	}

	public FileAndConsoleLogger(ILog logger, Class<?> clazz, String filePath) throws FileNotFoundException, UnsupportedEncodingException {
		this.logger = logger;
		this.clazz = clazz;
		this.filePath = filePath;
		this.file=new File(filePath);
		this.fileOutputStream = new FileOutputStream(file,true);
		this.printStream=new PrintStream(fileOutputStream);
	}

	private String getLogText(LogEnum logType, String msg) {
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS ");
		StringBuffer logSB = new StringBuffer();
		logSB.append(dFormat.format(new Date()));
		logSB.append((clazz == null ? "" : clazz.getName() + ":"));
		logSB.append(logType.getPreLogText()).append("-").append(msg);
		return logSB.toString();
	}

	
	private void appendToFile(String msg) {
		printStream.println(msg);			
	}

	private String getMsg(String msg, Object... args) {
		for (Object object : args) {
			msg = msg.replaceFirst("\\{\\}", object == null ? "null" : object.toString());
		}
		return msg;
	}

	@Override
	public void debug(String msg) {
		logger.debug(msg);
		String logT = getLogText(LogEnum.DEBUG, msg);
		appendToFile(logT);
	}

	@Override
	public void debug(String msg, Throwable t) {
		logger.debug(msg, t);
		String logT = getLogText(LogEnum.DEBUG, msg);
		appendToFile(logT);
		t.printStackTrace(printStream);
	}

	@Override
	public void debug(String msg, Object... args) {
		logger.debug(msg, args);
		String logT = getLogText(LogEnum.DEBUG, getMsg(msg, args));
		appendToFile(logT);
	}

	@Override
	public void info(String msg) {
		logger.info(msg);
		String logT = getLogText(LogEnum.INFO, msg);
		appendToFile(logT);
	}

	@Override
	public void info(String msg, Throwable t) {
		logger.info(msg, t);
		String logT = getLogText(LogEnum.INFO, msg);
		appendToFile(logT);
		t.printStackTrace(printStream);
	}

	@Override
	public void info(String msg, Object... args) {
		logger.info(msg, args);
		String logT = getLogText(LogEnum.INFO, getMsg(msg, args));
		appendToFile(logT);
	}

	@Override
	public void warn(String msg) {
		logger.warn(msg);
		String logT = getLogText(LogEnum.WARN, msg);
		appendToFile(logT);
	}

	@Override
	public void warn(String msg, Throwable t) {
		logger.warn(msg, t);
		String logT = getLogText(LogEnum.WARN, msg);
		appendToFile(logT);
		t.printStackTrace(printStream);
	}

	@Override
	public void warn(String msg, Object... args) {
		logger.debug(msg, args);
		String logT = getLogText(LogEnum.WARN, getMsg(msg, args));
		appendToFile(logT);
	}

	@Override
	public void error(String msg) {
		logger.error(msg);
		String logT = getLogText(LogEnum.ERROR, msg);
		appendToFile(logT);
	}

	@Override
	public void error(String msg, Throwable t) {
		logger.error(msg, t);
		String logT = getLogText(LogEnum.ERROR, msg);
		appendToFile(logT);
		t.printStackTrace(printStream);
	}

	@Override
	public void error(String msg, Object... args) {
		logger.debug(msg, args);
		String logT = getLogText(LogEnum.ERROR, getMsg(msg, args));
		appendToFile(logT);
	}

}
