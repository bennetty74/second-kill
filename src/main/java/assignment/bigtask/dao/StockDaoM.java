package assignment.bigtask.dao;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import assignment.bigtask.entity.Stock;

public class StockDaoM implements StockDao {

	private static List<Stock> stocks = new CopyOnWriteArrayList<>();
	
	private static Logger logger = LoggerFactory.getLogger(StockDaoM.class);
	
	public StockDaoM() throws Exception {
		stocks = new StockDaoDB().queryStocks();
		logger.info("Stocks Memory is {}",stocks);
	}

	@Override
	public List<Stock> queryStocks() throws Exception {
		return stocks;
	}

	@Override
	public void buyGoodsByCode(String goodsCode, Integer remains) throws Exception {

		for (Stock stock : stocks) {
			if (stock.getGoodsCode().equals(goodsCode)) {
				// remains -1
				stock.setRemains(stock.getRemains() - 1);
			}
		}
	}

}
