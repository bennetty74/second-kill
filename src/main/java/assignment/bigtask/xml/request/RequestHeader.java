package assignment.bigtask.xml.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class RequestHeader {
	
	private String tranCode;
	
	private String tranDate;
	
	private String tranTime;

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranTime() {
		return tranTime;
	}

	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	@Override
	public String toString() {
		return "RequestHeader [tranCode=" + tranCode + ", tranDate=" + tranDate + ", tranTime=" + tranTime + "]";
	}
	
}
