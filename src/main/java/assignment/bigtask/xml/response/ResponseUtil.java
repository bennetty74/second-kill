package assignment.bigtask.xml.response;

import java.util.Date;

import assignment.bigtask.Constants;
import assignment.bigtask.utils.FormatterUtils;

public class ResponseUtil {
	public static Response<Object> success(String tranCode) {
		return success(null, tranCode);
	}

	public static Response<Object> failture(String tranCode) {
		return failture(null, tranCode);
	}

	public static Response<Object> failture() {
		return failture(null, null);
	}

	public static Response<Object> success(Object body, String tranCode) {
		Response<Object> response = new Response<>();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setTranCode(tranCode);
		responseHeader.setTranDate(FormatterUtils.dateFormat(new Date()));
		responseHeader.setTranTime(FormatterUtils.timeFormat(new Date()));
		responseHeader.setRetCode(Constants.SUCCESS_RET_CODE);
		responseHeader.setRetMsg(Constants.SUCCESS_RET_MSG);
		response.setResponseHeader(responseHeader);
		response.setBody(body);
		return response;
	}

	public static Response<Object> failture(Object body, String tranCode) {
		Response<Object> response = new Response<>();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setTranCode(tranCode);
		responseHeader.setTranDate(FormatterUtils.dateFormat(new Date()));
		responseHeader.setTranTime(FormatterUtils.timeFormat(new Date()));
		responseHeader.setRetCode(Constants.FAILED_RET_CODE);
		responseHeader.setRetMsg(Constants.FAILED_RET_MSG);
		response.setResponseHeader(responseHeader);
		response.setBody(body);
		return response;
	}

}
