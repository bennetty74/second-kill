package assignment.bigtask.xml.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import assignment.bigtask.entity.Stock;

@XmlAccessorType(XmlAccessType.FIELD)
public class QueryResponseBody extends ResponseBody {

	private Integer nums;

	private List<Stock> row;

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public List<Stock> getRow() {
		return row;
	}

	public void setRow(List<Stock> row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "ResponseBody [nums=" + nums + ", row=" + row + "]";
	}

}
