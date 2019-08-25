//package assignment.bigtask.client;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.SelectionKey;
//import java.nio.channels.Selector;
//import java.nio.channels.SocketChannel;
//import java.nio.charset.Charset;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//import javax.xml.bind.JAXBException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import assignment.bigtask.Constants;
//import assignment.bigtask.entity.Stock;
//import assignment.bigtask.utils.XMLUtils;
//import assignment.bigtask.xml.request.RequestService;
//import assignment.bigtask.xml.response.QueryResponseService;
//
///**
// * This client will use single thread to deal with readable and writable events
// * 
// * @author Bennetty74
// *
// */
//public class RunnableClient implements Runnable {
//
//	private String userName;
//
//	private ClientService clientService;
//
//	private Selector selector;
//
//	private SocketChannel socketChannel;
//	
//	private StringBuffer response = new StringBuffer();
//
//	private static Logger logger = (Logger) LoggerFactory.getLogger(ClientReadHandler.class);
//
//	public RunnableClient(String userName) throws IOException, JAXBException {
//		this.userName = userName;
//		this.clientService = new ClientService(userName);
//		init();
//	}
//
//	public void init() throws IOException, JAXBException {
//
//		socketChannel = SocketChannel
//				.open(new InetSocketAddress(Constants.DEFAULT_HOST_ADDRESS, Constants.DEFAULT_PORT));
//
//		selector = Selector.open();
//		socketChannel.configureBlocking(false);
//		socketChannel.register(selector, SelectionKey.OP_READ);
//
//		// start read handler thread
////		new Thread(new ClientReadHandler(selector)).start();
////
////		RequestService requestService = clientService.queryRequest();
////
////		String xmlMsg = XMLUtils.transformToXML(requestService);
////
////		// send msg
////		socketChannel.write(Charset.forName(Constants.DEFAULT_EN_CODING).encode(xmlMsg));
//
//	}
//
//	@Override
//	public void run() {
//
//		try {
//			while (true) {
//				int readyChannels = selector.select();
//				if (readyChannels == 0)
//					continue;
//
//				Set<SelectionKey> selectionKeys = selector.selectedKeys();
//				Iterator<SelectionKey> iterator = selectionKeys.iterator();
//
//				while (iterator.hasNext()) {
//					SelectionKey selectionKey = (SelectionKey) iterator.next();
//					iterator.remove();
//
//					if (selectionKey.isReadable()) {
//						readHandler(selectionKey, selector);
//					}
//				}
//
//				//send request
//				Thread.sleep(3000);
//				RequestService requestService = clientService.queryRequest();
//				String xmlMsg = XMLUtils.transformToXML(requestService);
//				// write msg
//				socketChannel.write(Charset.forName(Constants.DEFAULT_EN_CODING).encode(xmlMsg));
//
//			}
//		} catch (IOException e) {
//			logger.error("Error occurs {}", e);
//		} catch (Exception e) {
//			logger.error("Error occurs {}", e);
//		}
//
//	}
//
//	private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException, JAXBException {
//
//		logger.info("Read Response Msg from Server");
//
//		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
//		ByteBuffer byteBuffer = ByteBuffer.allocate(10240);
//
//		response.delete(0, response.length());
//		while ( socketChannel.read(byteBuffer) > 0) {
//			byteBuffer.flip();
//			response.append(Charset.forName(Constants.DEFAULT_EN_CODING).decode(byteBuffer));
//		}
//	
//		socketChannel.register(selector, SelectionKey.OP_READ);
//		//read msg 
//		if (response.length() > 0) {
//			logger.info("{}", response);
//			QueryResponseService responseService = new QueryResponseService();
//			responseService = (QueryResponseService) XMLUtils.transformToObject(responseService, response.toString());
//			List<Stock> stocks =responseService.getBody().getRow();
//			logger.info("Query stocks is {}",stocks);
//		}
//	}
//
//	public static void main(String[] args) throws IOException, JAXBException {
//		new Thread(new RunnableClient("李四")).start();
//	}
//
//}
