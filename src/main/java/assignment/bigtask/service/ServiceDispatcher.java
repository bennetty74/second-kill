package assignment.bigtask.service;

import assignment.bigtask.Constants;
import assignment.bigtask.xml.request.Request;
import assignment.bigtask.xml.response.Response;
import assignment.bigtask.xml.response.ResponseUtil;

public class ServiceDispatcher {

	private static ServiceDispatcher serviceDispatcher;

	private StockService stockService;

	private ServiceDispatcher(StockService stockService) {
		this.stockService = stockService;
	}

	/**
	 * Get Single instance of This class
	 * 
	 * @return
	 * @throws Exception
	 */
	public static synchronized ServiceDispatcher getInstance() throws Exception {
		if (serviceDispatcher == null) {
			serviceDispatcher = new ServiceDispatcher(new StockService());
		}
		return serviceDispatcher;
	}

	/**
	 * Dispatch The Request
	 * 
	 * @param requestService
	 * @param responseService
	 * @return
	 * @throws Exception
	 */
	public Response<?> dispatch(Request<?> request) throws Exception {
		if (Constants.BUY_CODE.equals(request.getTranCode())) {
			return stockService.buyGoods(request);
		} else if (Constants.QUERY_CODE.equals(request.getTranCode())) {
			return stockService.queryStocks(request);
		}
		return ResponseUtil.failture();
	}

}
