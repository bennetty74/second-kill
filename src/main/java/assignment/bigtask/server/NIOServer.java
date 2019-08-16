package assignment.bigtask.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import assignment.bigtask.Constants;
import assignment.bigtask.dao.StockDao;
import assignment.bigtask.service.ServiceDispatcher;
import assignment.bigtask.service.StockService;
import assignment.bigtask.utils.XMLUtils;
import assignment.bigtask.xml.request.RequestService;
import assignment.bigtask.xml.response.ResponseService;

/**
 * NIO Server Used to deal with client connection event,acceptable
 * event,readable event and writable event.
 * 
 * @author Bennetty74
 *
 */
public class NIOServer {

	/**
	 * log
	 */
	private static Logger logger = LoggerFactory.getLogger(NIOServer.class);

	/**
	 * Default datagram size
	 */
	private static final int DEFAULT_DATAGRAM_SIZE = 6;

	private StockService stockService;

	public NIOServer(StockService stockService) {
		super();
		this.stockService = stockService;
	}

	public void start() throws Exception {
		// open selector
		Selector selector = Selector.open();
		// open serversocketchannel
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.bind(new InetSocketAddress(8000));
		// block is false
		serverSocketChannel.configureBlocking(false);
		// register connection event
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		logger.info("NIO Server Started");

		while (true) {
			// get ready channels
			int readyChannels = selector.select();
			// has no ready events,continue loop
			if (readyChannels == 0)
				continue;
			// get selectionkeys,which map to channel
			Set<SelectionKey> keys = selector.selectedKeys();
			// transform to iterator
			Iterator iterator = keys.iterator();

			// loop to deal with channel events
			while (iterator.hasNext()) {
				// get selectionkey
				SelectionKey selectionKey = (SelectionKey) iterator.next();
				// remove this selectionkey ,avoid duplicate
				iterator.remove();

				// acceptable event
				if (selectionKey.isAcceptable()) {
					acceptHandler(serverSocketChannel, selector);
				}
				// readable event
				if (selectionKey.isReadable()) {
					readHandler(selector, selectionKey);
				}
				// writable event
				if (selectionKey.isWritable()) {
					writeHandler();
				}

			}

		}

	}

	/**
	 * used to accept connection events from client
	 * 
	 * @param serverSocketChannel
	 * @param selector
	 * @throws IOException
	 */
	private void acceptHandler(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
		// accept socketchannel connection
		SocketChannel socketChannel = serverSocketChannel.accept();
		// non blocking
		socketChannel.configureBlocking(false);
		// register and listen to readable event
		socketChannel.register(selector, SelectionKey.OP_READ);
		// response connection info
		socketChannel.write(Charset.forName("UTF-8").encode("Connect successful"));
	}

	/**
	 * 
	 * @param selector
	 * @param selectionKey
	 * @throws Exception
	 */
	private void readHandler(Selector selector, SelectionKey selectionKey) throws Exception {
		// get socket channel
		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		String request = "";
		int read = 0;
		while ((read = socketChannel.read(byteBuffer)) > 0) {
			// change to read mode
			byteBuffer.flip();
			// splice request msg
			request += Charset.forName("UTF-8").decode(byteBuffer);
		}

		if (read == -1) {
			socketChannel.close();
			
		}

		// register again and read other readable events
		socketChannel.register(selector, SelectionKey.OP_READ);

		/**
		 * TODO parse xml msg , get RequestService details Server will do operation
		 * according to request details
		 */

		RequestService requestService = new RequestService();

		// convert xml string to RequestService Object
		requestService = (RequestService) XMLUtils.transformToObject(requestService, request);

		// dispacth requestService
//		ResponseService responseService = stockService.dispatchService(requestService);
		logger.info("Request Service Info : {}", requestService);

		socketChannel.write(Charset.forName(Constants.DEFAULT_EN_CODING)
				.encode(XMLUtils.transformToXML(ServiceDispatcher.getInstance().dispatch(requestService))));
	}

	private void writeHandler() {

	}


	/**
	 * Test NIOServer
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		StockService stockService = new StockService(new StockDao());

		new NIOServer(stockService).start();
	}

}
