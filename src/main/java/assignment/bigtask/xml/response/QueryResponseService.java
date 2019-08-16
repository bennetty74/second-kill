package assignment.bigtask.xml.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "responseService")
@XmlAccessorType(XmlAccessType.FIELD)
public class QueryResponseService extends ResponseService{
	@XmlElement(name = "responseHeader")
	private ResponseHeader responseHeader;
	@XmlElement(name = "body")
	private QueryResponseBody body;

	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}

	public QueryResponseBody getBody() {
		return body;
	}

	public void setBody(QueryResponseBody body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "ResponseService [responseHeader=" + responseHeader + ", body=" + body + "]";
	}

}
