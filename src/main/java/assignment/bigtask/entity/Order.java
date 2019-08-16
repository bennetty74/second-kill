package assignment.bigtask.entity;

public class Order {
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 商品编号
	 */
	private String goodsCode;
	/**
	 * 商品数量
	 */
	private Integer goodsCount;
	/**
	 * 订单日期
	 */
	private String orderDate;
	/**
	 * 订单时间
	 */
	private String orderTime;
	/**
	 * 下单用户
	 */
	private String orderUser;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public Integer getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderUser() {
		return orderUser;
	}
	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", goodsCode=" + goodsCode + ", goodsCount=" + goodsCount + ", orderDate="
				+ orderDate + ", orderTime=" + orderTime + ", orderUser=" + orderUser + "]";
	}

}
