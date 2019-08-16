package assignment.bigtask.xml.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "responseService")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseService {

	@XmlElement(name = "responseHeader")
	private ResponseHeader responseHeader;
	@XmlElement(name = "body")
	private BuyResponseBody responseBody;

	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}

	public BuyResponseBody getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(BuyResponseBody responseBody) {
		this.responseBody = responseBody;
	}

}
