package assignment.bigtask.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import assignment.bigtask.dao.StockDao;
import assignment.bigtask.dao.StockDaoM;
import assignment.bigtask.entity.Order;
import assignment.bigtask.entity.Stock;
import assignment.bigtask.xml.request.Request;
import assignment.bigtask.xml.response.QueryResB;
import assignment.bigtask.xml.response.Response;
import assignment.bigtask.xml.response.ResponseUtil;

/**
 * Stock Service Deal with Stock events
 * 
 * @author Bennetty74
 *
 */
public class StockService {

	private StockDao stockDao;

	private static Logger logger = LoggerFactory.getLogger(StockService.class);

	public StockService() throws Exception {
		this.stockDao = new StockDaoM();
	}

	public StockService(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	/**
	 * Query stocks
	 * 
	 * @param requestService
	 * @return
	 * @throws Exception
	 */
	public Response<?> queryStocks(Request<?> request) throws Exception {

		String tranCode = request.getTranCode();

		try {
			List<Stock> stocks = stockDao.queryStocks();

			QueryResB resB = new QueryResB();
			resB.setNums(stocks.size());
			resB.setStocks(stocks);

			return ResponseUtil.success(resB, tranCode);

		} catch (Exception e) {
			logger.error("Error occurs {}", e);
			return ResponseUtil.failture(tranCode);
		}
	}

	/**
	 * Buy goods by code
	 * 
	 * @param requestService
	 * @return
	 * @throws Exception
	 */
	public Response<?> buyGoods(Request<?> request) throws Exception {

		String tranCode = request.getTranCode();
		// 获取买的商品
		Order order = (Order) request.getBody();
		// buy in MySQL
		try {
			stockDao.buyGoodsByCode(order.getStock().getGoodsCode(), order.getStock().getRemains() - 1);
			return ResponseUtil.success(tranCode);
		} catch (Exception e) {
			logger.error("Error occurs {}", e);
			return ResponseUtil.failture(tranCode);
		}
	}

}
