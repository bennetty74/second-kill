package assignment.bigtask.xml.request;

import java.util.Date;

import assignment.bigtask.utils.FormatterUtils;

public class RequestUtil {

	public static Request<?> request(Object o, String tranCode) {
		Request<Object> request = new Request<Object>();
		request.setTranCode(tranCode);
		request.setTranDate(FormatterUtils.dateFormat(new Date()));
		request.setTranTime(FormatterUtils.timeFormat(new Date()));
		request.setBody(o);
		return request;
	}

	public static Request<?> request(String tranCode) {
		return request(null, tranCode);
	}

}
