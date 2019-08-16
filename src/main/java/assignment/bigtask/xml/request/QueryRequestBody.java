package assignment.bigtask.xml.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class QueryRequestBody extends RequestBody{

	protected String goodsCode;

	protected String qty;

	protected String orderUser;

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
	}

	@Override
	public String toString() {
		return "QueryStockRequestBody [goodsCode=" + goodsCode + ", qty=" + qty + ", orderUser=" + orderUser + "]";
	}

}
