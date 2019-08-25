package assignment.bigtask.client;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import assignment.bigtask.Constants;
import assignment.bigtask.entity.Order;
import assignment.bigtask.entity.Stock;
import assignment.bigtask.utils.FormatterUtils;
import assignment.bigtask.xml.request.Request;
import assignment.bigtask.xml.request.RequestUtil;

public class ClientService {

	private String userName;

	public ClientService(String userName) {
		this.userName = userName;
	}

	public Request<?> queryRequest() {
		return RequestUtil.request(Constants.QUERY_CODE);
	}

	public Request<?> randomBuyRequest(List<Stock> stocks) {
		// select goods random
		int buyIndex = (int) (Math.random() * stocks.size());
		// generate order
		Order order = generateOrder(stocks.get(buyIndex));
		
		// set request service
		return RequestUtil.request(order, Constants.BUY_CODE);
	}

	private Order generateOrder(Stock stock) {
		Order order = new Order();

		UUID uuid = UUID.randomUUID();
		String orderID = uuid.toString().replace("-", "");

		order.setStock(stock);
		order.setOrderId(orderID);
		order.setOrderUser(userName);
		order.setOrderDate(FormatterUtils.dateFormat(new Date()));
		order.setOrderTime(FormatterUtils.timeFormat(new Date()));
		return order;
	}

}
