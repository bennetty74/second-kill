package assignment.bigtask.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import assignment.bigtask.Constants;
import assignment.bigtask.dao.StockDao;
import assignment.bigtask.entity.Stock;
import assignment.bigtask.utils.FormatterUtils;
import assignment.bigtask.xml.request.BuyRequestService;
import assignment.bigtask.xml.request.RequestService;
import assignment.bigtask.xml.response.BuyResponseBody;
import assignment.bigtask.xml.response.BuyResponseService;
import assignment.bigtask.xml.response.QueryResponseBody;
import assignment.bigtask.xml.response.QueryResponseService;
import assignment.bigtask.xml.response.ResponseHeader;
import assignment.bigtask.xml.response.ResponseService;

/**
 * Stock Service Deal with Stock events
 * 
 * @author Bennetty74
 *
 */
public class StockService {

	private StockDao stockDao;

	private static Logger logger = LoggerFactory.getLogger(StockService.class);

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
	public ResponseService queryStocks(RequestService requestService) throws Exception {

		QueryResponseService responseService = new QueryResponseService();
		String tranCode = requestService.getRequestHeader().getTranCode();

		try {
			List<Stock> stocks = stockDao.queryStocks();

			// package response
			ResponseHeader responseHeader = successResponseHeader(tranCode);
			responseService.setResponseHeader(responseHeader);

			QueryResponseBody responseBody = new QueryResponseBody();
			responseBody.setNums(stocks.size());
			responseBody.setRow(stocks);
			responseService.setBody(responseBody);

			return responseService;
		} catch (Exception e) {
			logger.error("Error occurs {}", e);
			responseService.setResponseHeader(failedResponseHeader(tranCode));
			return responseService;
		}
	}

	/**
	 * Buy goods by code
	 * 
	 * @param requestService
	 * @return
	 * @throws Exception
	 */
	public ResponseService buyGoods(RequestService requestService) throws Exception {

		BuyResponseService responseService = new BuyResponseService();
		String tranCode = requestService.getRequestHeader().getTranCode();

		String goodsCode = ((BuyRequestService)requestService).getBuyRequestBody().getGoodsCode();
		Integer remains =  ((BuyRequestService)requestService).getBuyRequestBody().getQty();
		// buy in mysql
		try {
			stockDao.buyGoodsByCode(goodsCode, remains - 1);
			ResponseHeader responseHeader = successResponseHeader(tranCode);
			responseService.setResponseHeader(responseHeader);
			responseService.setResponseBody(new BuyResponseBody());
			return responseService;
		} catch (Exception e) {
			logger.error("Error occurs {}", e);
			responseService.setResponseHeader(failedResponseHeader(tranCode));
			responseService.setResponseBody(new BuyResponseBody());
			return responseService;
		}
	}

	/**
	 * Return success response header
	 * 
	 * @param tranCode
	 * @return
	 */
	private ResponseHeader failedResponseHeader(String tranCode) {
		ResponseHeader responseHeader = new ResponseHeader();

		responseHeader.setTranCode(tranCode);
		responseHeader.setTranDate(FormatterUtils.dateFormat(new Date()));
		responseHeader.setTranTime(FormatterUtils.timeFormat(new Date()));
		responseHeader.setRetCode(Constants.FAILED_RET_CODE);
		responseHeader.setRetMsg(Constants.FAILED_RET_MSG);

		return responseHeader;
	}

	/**
	 * Return Success response header
	 * 
	 * @param tranCode
	 * @return
	 */
	private ResponseHeader successResponseHeader(String tranCode) {
		ResponseHeader responseHeader = new ResponseHeader();

		responseHeader.setTranCode(tranCode);
		responseHeader.setTranDate(FormatterUtils.dateFormat(new Date()));
		responseHeader.setTranTime(FormatterUtils.timeFormat(new Date()));
		responseHeader.setRetCode(Constants.SUCCESS_RET_CODE);
		responseHeader.setRetMsg(Constants.SUCCESS_RET_MSG);

		return responseHeader;
	}

}
