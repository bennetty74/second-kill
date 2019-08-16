package assignment.bigtask.entity;

/**
 * 库存
 * @author Bennetty74
 *
 */
public class Stock {

	/**
	 * 商品代码
	 */
	private String goodsCode;
	/**
	 * 库存数量
	 */
	private Integer remains;
	
	
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public Integer getRemains() {
		return remains;
	}
	public void setRemains(Integer remains) {
		this.remains = remains;
	}
	@Override
	public String toString() {
		return "Stock [goodsCode=" + goodsCode + ", remains=" + remains + "]";
	}
	
}
