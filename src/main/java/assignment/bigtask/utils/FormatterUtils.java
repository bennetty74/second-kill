package assignment.bigtask.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import assignment.bigtask.Constants;

/**
 * Date and Time Formatter Util
 * @author Bennetty74
 *
 */
public class FormatterUtils {
	
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMATTER);
	
	
	private static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMATTER);
	
	
	/**
	 * Date formatter method
	 * @param date
	 * @return
	 */
	public static String dateFormat(Date date) {
		return DATE_FORMATTER.format(date);
	}
	
	/**
	 * Time formatter method
	 * @param time
	 * @return
	 */
	public static String timeFormat(Date time) {
		return TIME_FORMATTER.format(time);
	}

}
