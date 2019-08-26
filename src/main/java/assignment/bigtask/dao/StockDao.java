package assignment.bigtask.dao;

import java.util.List;

import assignment.bigtask.entity.Stock;

public interface StockDao {
	
	public List<Stock> queryStocks() throws Exception;
	
	public void buyGoodsByCode(String goodsCode, Integer remains) throws Exception;

}
