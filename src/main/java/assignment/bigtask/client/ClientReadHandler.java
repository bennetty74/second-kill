package assignment.bigtask.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import assignment.bigtask.Constants;

public class ClientReadHandler implements Runnable {

	private Selector selector;

	private static Logger logger = (Logger) LoggerFactory.getLogger(ClientReadHandler.class);

	public ClientReadHandler(Selector selector) {
		this.selector = selector;
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

		String response = "";
		int read = 0;
		while ((read = socketChannel.read(byteBuffer)) > 0) {
			byteBuffer.flip();
			response += Charset.forName(Constants.DEFAULT_EN_CODING).decode(byteBuffer);
		}
		if(read==-1) {
			socketChannel.close();
			logger.error("Server closed ...");
		}

		socketChannel.register(selector, SelectionKey.OP_READ);
		if (response.length() > 0) {
			logger.info("query statue \n {}", response);
		}
	}

}
