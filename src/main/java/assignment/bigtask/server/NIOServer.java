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
import assignment.bigtask.service.ServiceDispatcher;
import assignment.bigtask.utils.XMLUtils;
import assignment.bigtask.xml.request.Request;
import assignment.bigtask.xml.response.Response;

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

	private StringBuffer requestBuffer = new StringBuffer();

	public void start() throws Exception {
		// open selector
		Selector selector = Selector.open();
		// open server socket channel
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
			// get selection keys,which map to channel
			Set<SelectionKey> keys = selector.selectedKeys();
			// transform to iterator
			Iterator<SelectionKey> iterator = keys.iterator();

			// loop to deal with channel events
			while (iterator.hasNext()) {
				// get selection key
				SelectionKey selectionKey = (SelectionKey) iterator.next();
				// remove this selection key ,avoid duplicate
				iterator.remove();

				// acceptable event
				if (selectionKey.isAcceptable()) {
					acceptHandler(serverSocketChannel, selector);
				}
				// readable event
				if (selectionKey.isReadable()) {
					readHandler(selector, selectionKey);
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

		requestBuffer.delete(0, requestBuffer.length());
		while (socketChannel.read(byteBuffer) > 0) {
			// change to read mode
			byteBuffer.flip();
			// splice request msg
			requestBuffer.append(Charset.forName(Constants.DEFAULT_EN_CODING).decode(byteBuffer));
		}

		// register again and read other readable events
		socketChannel.register(selector, SelectionKey.OP_READ);

		/**
		 * Send Response
		 */
		Request<?> request = new Request<>();
		request = (Request<?>) XMLUtils.transformToObject(request, requestBuffer.toString());

		Response<?> response = ServiceDispatcher.getInstance().dispatch(request);
		String responseXML = XMLUtils.transformToXML(response);
		socketChannel.write(Charset.forName(Constants.DEFAULT_EN_CODING).encode(responseXML));

	}

	/**
	 * Test NIOServer
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new NIOServer().start();
	}

}
