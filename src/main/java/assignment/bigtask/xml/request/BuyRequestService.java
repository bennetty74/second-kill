package assignment.bigtask.xml.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Buy goods Request Service entity
 * 
 * @author Bennetty74
 *
 */
@XmlRootElement(name = "requestService")
@XmlAccessorType(XmlAccessType.FIELD)
public class BuyRequestService extends RequestService {

	@XmlElement(name = "requestHeader")
	private RequestHeader requestHeader;

	@XmlElement(name = "body")
	private BuyRequestBody buyRequestBody;

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public BuyRequestBody getBuyRequestBody() {
		return buyRequestBody;
	}

	public void setBuyRequestBody(BuyRequestBody buyRequestBody) {
		this.buyRequestBody = buyRequestBody;
	}

	@Override
	public String toString() {
		return "BuyRequestService [requestHeader=" + requestHeader + ", buyRequestBody=" + buyRequestBody + "]";
	}

}
