package assignment.bigtask.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import assignment.bigtask.Constants;
import assignment.bigtask.entity.Stock;
import assignment.bigtask.utils.XMLUtils;
import assignment.bigtask.xml.request.Request;
import assignment.bigtask.xml.response.QueryResB;
import assignment.bigtask.xml.response.Response;

/**
 * NIO client send request to server
 *
 */
public class NIOClient implements Runnable {

	private String userName;

	private ClientService clientService;

	private Selector selector;

	private SocketChannel socketChannel;

	private StringBuffer response = new StringBuffer();

	public static BlockingQueue<List<Stock>> stockBlockingQueue = new LinkedBlockingQueue<>();

	private static Logger logger = LoggerFactory.getLogger(NIOClient.class);

	public NIOClient(String userName) throws Exception {
		this.setUserName(userName);
		this.clientService = new ClientService(userName);
		init();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void init() throws Exception {

		socketChannel = SocketChannel
				.open(new InetSocketAddress(Constants.DEFAULT_HOST_ADDRESS, Constants.DEFAULT_PORT));

		selector = Selector.open();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_WRITE);
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

					if (selectionKey.isWritable()) {
						writeHandler(selectionKey, selector);
					}

					if (selectionKey.isReadable()) {
						readHandler(selectionKey, selector);
					}
//					Thread.sleep(1000);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void readHandler(SelectionKey selectionKey, Selector selector) throws Exception {

		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(10240);

		response.delete(0, response.length());
		while (socketChannel.read(byteBuffer) > 0) {
			byteBuffer.flip();
			response.append(Charset.forName(Constants.DEFAULT_EN_CODING).decode(byteBuffer));
		}
		socketChannel.register(selector, SelectionKey.OP_WRITE);

		// 获取响应对象
		Response<?> responseObject = new Response<>();
		if (response.length() > 0) {
			String responseXML = response.toString();
			responseObject = (Response<?>) XMLUtils.transformToObject(responseObject, responseXML);
		}

		String tranCode = responseObject.getResponseHeader().getTranCode();
		// 打印响应结果
		if (Constants.QUERY_CODE.equals(tranCode)) {
			QueryResB resB = (QueryResB) responseObject.getBody();
			NIOClient.stockBlockingQueue.offer(resB.getStocks());
			logger.info("{} 查询响应： {}", userName, resB.getStocks());
		} else if (Constants.BUY_CODE.equals(tranCode)) {
			logger.info("{} 购买响应： {}", userName, responseObject.toString());
		}

	}

	private void writeHandler(SelectionKey selectionKey, Selector selector) throws Exception {

		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

		if (stockBlockingQueue.size() <= 0) {
			// query
			Request<?> request = clientService.queryRequest();
			String queryXML = XMLUtils.transformToXML(request);
			socketChannel.write(Charset.forName(Constants.DEFAULT_EN_CODING).encode(queryXML));
			logger.info("{} 查询请求", userName);
		} else {
			// buy
			List<Stock> stocks = stockBlockingQueue.poll();
			if (stocks != null) {
				logger.info("{} 随机买请求", userName);
				Request<?> buyRequest = clientService.randomBuyRequest(stocks);
				String buyXML = XMLUtils.transformToXML(buyRequest);

				socketChannel.write(Charset.forName(Constants.DEFAULT_EN_CODING).encode(buyXML));
			}
		}

		socketChannel.register(selector, SelectionKey.OP_READ);

	}

	/**
	 * NIO Client Test
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ExecutorService executor = new ThreadPoolExecutor(5, 10, 0, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		for (int i = 0; i < 200; i++) {
			executor.submit(new NIOClient("User-" + i));
		}
	}

}
