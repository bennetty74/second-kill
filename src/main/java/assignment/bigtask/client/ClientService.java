package assignment.bigtask.client;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import assignment.bigtask.Constants;
import assignment.bigtask.entity.Order;
import assignment.bigtask.entity.Stock;
import assignment.bigtask.utils.FormatterUtils;
import assignment.bigtask.xml.request.BuyRequestBody;
import assignment.bigtask.xml.request.RequestBody;
import assignment.bigtask.xml.request.RequestHeader;
import assignment.bigtask.xml.request.RequestService;

public class ClientService {

	private String userName;

	public ClientService(String userName) {
		this.userName = userName;
	}

	public RequestService queryRequest() {
		RequestService requestService = new RequestService();
		RequestHeader requestHeader = new RequestHeader();

		requestHeader.setTranCode(Constants.QUERY_CODE);
		requestHeader.setTranDate(FormatterUtils.dateFormat(new Date()));
		requestHeader.setTranTime(FormatterUtils.timeFormat(new Date()));
		requestService.setRequestBody(new RequestBody());
		requestService.setRequestHeader(requestHeader);
		return requestService;
	}

	public RequestService randomBuyRequest(List<Stock> stocks) {
		// select goods random
		int buyIndex = (int) (Math.random() * stocks.size());
		// generate order
		Order order = generateOrder(stocks.get(buyIndex).getGoodsCode());
		// set request service
		RequestService requestService = new RequestService();
		// set request header
		RequestHeader requestHeader = new RequestHeader();
		requestHeader.setTranCode(Constants.BUY_CODE);
		requestHeader.setTranDate(FormatterUtils.dateFormat(new Date()));
		requestHeader.setTranTime(FormatterUtils.timeFormat(new Date()));
		requestService.setRequestHeader(requestHeader);

		// set request body
		RequestBody requestBody = new RequestBody();
		((BuyRequestBody) requestBody).setQty(order.getGoodsCount());
		((BuyRequestBody) requestBody).setOrderUser(order.getOrderUser());
		((BuyRequestBody) requestBody).setGoodsCode(order.getGoodsCode());
		requestService.setRequestBody(requestBody);

		return requestService;
	}

	private Order generateOrder(String goodsCode) {
		Order order = new Order();

		UUID uuid = UUID.randomUUID();
		String orderID = uuid.toString().replace("-", "");

		order.setOrderId(orderID);

		order.setOrderUser(userName);
		order.setOrderDate(FormatterUtils.dateFormat(new Date()));
		order.setGoodsCode(goodsCode);
		order.setGoodsCount(Constants.DEFAULT_BUY_SIZE);
		order.setOrderTime(FormatterUtils.timeFormat(new Date()));

		return order;
	}

}
