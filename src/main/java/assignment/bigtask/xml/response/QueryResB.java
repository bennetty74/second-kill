package assignment.bigtask.xml.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import assignment.bigtask.entity.Stock;

@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.FIELD)
public class QueryResB {
	@XmlElement(name = "nums")
	private int nums;
	@XmlElement(name = "row")
	private List<Stock> stocks;

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	@Override
	public String toString() {
		return "QueryResB [nums=" + nums + ", stocks=" + stocks + "]";
	}
	
	
	
}
