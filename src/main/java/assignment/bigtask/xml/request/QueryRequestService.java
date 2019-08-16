package assignment.bigtask.xml.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Query Stock Request Service
 * 
 * @author Bennetty74
 *
 */
@XmlRootElement(name = "requestService")
@XmlAccessorType(XmlAccessType.FIELD)
public class QueryRequestService extends RequestService {

	@XmlElement(name = "requestHeader")
	private RequestHeader requestHeader;

	@XmlElement(name = "body")
	private QueryRequestBody queryRequestBody;

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public QueryRequestBody getQueryRequestBody() {
		return queryRequestBody;
	}

	public void setQueryStockRequestBody(QueryRequestBody queryRequestBody) {
		this.queryRequestBody = queryRequestBody;
	}

	@Override
	public String toString() {
		return "QueryStockRequestService [requestHeader=" + requestHeader + ", queryStockRequestBody="
				+ queryRequestBody + "]";
	}

}
