package assignment.bigtask.xml.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import assignment.bigtask.entity.Order;

@XmlRootElement(name = "requestService")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Order.class})
public class Request <T>{
	
	@XmlElement(name="requestHeader")
	private RequestHeader requestHeader = new RequestHeader();
	
	@XmlElement(name="body")
	private T body;
	
	public void setTranCode(String tranCode) {
		this.requestHeader.setTranCode(tranCode);
	}
	
	public void setTranDate(String tranDate) {
		this.requestHeader.setTranDate(tranDate);
	}
	
	public void setTranTime(String tranTime) {
		this.requestHeader.setTranTime(tranTime);
	}
	
	public String getTranCode() {
		return this.requestHeader.getTranCode();
	}
	
	public String getTranDate() {
		return this.requestHeader.getTranDate();
	}
	
	public String getTranTime() {
		return this.requestHeader.getTranTime();
	}
	
	public void setBody(T body) {
		this.body=body;
	}
	
	public T getBody() {
		return body;
	}

}
