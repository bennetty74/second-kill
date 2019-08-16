package assignment.bigtask.service;

import assignment.bigtask.Constants;
import assignment.bigtask.dao.StockDao;
import assignment.bigtask.xml.request.RequestService;
import assignment.bigtask.xml.response.ResponseService;

public class ServiceDispatcher{

	private static ServiceDispatcher serviceDispatcher;

	/**
	 * Get Single instance of This class
	 * 
	 * @return
	 */
	public static synchronized ServiceDispatcher getInstance() {
		if (serviceDispatcher == null) {
			return new ServiceDispatcher();
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
	@SuppressWarnings("unchecked")
	public ResponseService dispatch(RequestService requestService) throws Exception {
		String tranCode=requestService.getRequestHeader().getTranCode();
		//buy
		if(Constants.BUY_CODE.equals(tranCode)) {
			return new StockService(new StockDao()).buyGoods(requestService);
		}else if(Constants.QUERY_CODE.equals(tranCode)) {
			return new StockService(new StockDao()).queryStocks(requestService);
		}
		return new ResponseService();
	}

}
