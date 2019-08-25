package assignment.bigtask.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import assignment.bigtask.Constants;
import assignment.bigtask.entity.Stock;
import assignment.bigtask.utils.ClientLock;
import assignment.bigtask.utils.XMLUtils;
import assignment.bigtask.xml.request.Request;
import assignment.bigtask.xml.response.QueryResB;
import assignment.bigtask.xml.response.Response;

public class ClientReadHandler implements Runnable {

	private Selector selector;

	private ClientService clientService;

	private StringBuffer response = new StringBuffer();

	private static Logger logger = (Logger) LoggerFactory.getLogger(ClientReadHandler.class);

	public ClientReadHandler(Selector selector, ClientService clientService) {
		this.selector = selector;
		this.clientService = clientService;
	}

	@Override
	public void run() {
		try {
			while (true) {
				int readyChannels = selector.select();
				if (readyChannels == 0)
					continue;

				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();

				while (iterator.hasNext()) {

					SelectionKey selectionKey = (SelectionKey) iterator.next();
					iterator.remove();

					if (selectionKey.isReadable()) {
						readHandler(selectionKey, selector);
					}

					if (selectionKey.isWritable()) {
//						writeHandler(selectionKey, selector);
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {

		logger.info("Read Response Msg from Server");

		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(10240);

		response.delete(0, response.length());
		while (socketChannel.read(byteBuffer) > 0) {
			byteBuffer.flip();
			response.append(Charset.forName(Constants.DEFAULT_EN_CODING).decode(byteBuffer));
		}

		socketChannel.register(selector, SelectionKey.OP_READ);

		// 显示服务端返回信息
		Response<?> responseObject = new Response<>();
		if (response.length() > 0) {
			String responseXML = response.toString();
			logger.info(responseXML);
			try {
				responseObject = (Response<?>) XMLUtils.transformToObject(responseObject, responseXML);
				logger.info("客户端接收的响应：{}", responseObject.toString());
			} catch (JAXBException e) {
				e.printStackTrace();
			}

		}

		String tranCode = responseObject.getResponseHeader().getTranCode();
		// 查询返回的结果
		if (Constants.QUERY_CODE.equals(tranCode)) {
			QueryResB resB = (QueryResB) responseObject.getBody();
			NIOClient.stockBlockingQueue.offer(resB.getStocks());
		} else if (Constants.BUY_CODE.equals(tranCode)) {
			logger.info("购买响应： {}", responseObject.toString());
		}

	}

	@SuppressWarnings("unused")
	private void writeHandler(SelectionKey selectionKey, Selector selector) {

		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

		// buy goods MSG
		List<Stock> stocks = new ArrayList<Stock>();
		Stock stock = new Stock();
		stock.setGoodsCode("00000001");
		Request<?> request = clientService.randomBuyRequest(stocks);
		String xmlMsg;
		try {
			xmlMsg = XMLUtils.transformToXML(request);
			socketChannel.write(Charset.forName(Constants.DEFAULT_EN_CODING).encode(xmlMsg));

			socketChannel.register(selector, SelectionKey.OP_WRITE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
