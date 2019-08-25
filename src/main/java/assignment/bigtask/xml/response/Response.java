package assignment.bigtask.xml.response;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import assignment.bigtask.entity.Stock;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ArrayList.class,QueryResB.class,Stock.class})
public class Response<T> {
	
	@XmlElement(name = "responseHeader")
	private ResponseHeader responseHeader;
	
	@XmlAnyElement(lax = true)
	private T body;

	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Response [responseHeader=" + responseHeader + ", body=" + body + "]";
	}
	
	

}
