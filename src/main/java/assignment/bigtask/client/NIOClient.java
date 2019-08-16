package assignment.bigtask.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

import javax.xml.bind.JAXBException;

import assignment.bigtask.Constants;
import assignment.bigtask.utils.XMLUtils;
import assignment.bigtask.xml.request.RequestService;

/**
 * NIO client send request to server
 * 
 * @author Bennetty74
 *
 */
public class NIOClient {

	private String userName;

	private ClientService clientService;

	public NIOClient(String userName) {
		this.userName = userName;
		this.clientService = new ClientService(userName);
	}

	public void start() throws IOException, JAXBException {

		SocketChannel socketChannel = SocketChannel
				.open(new InetSocketAddress(Constants.DEFAULT_HOST_ADDRESS, Constants.DEFAULT_PORT));

		Selector selector = Selector.open();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);

		// start read handler thread
		new Thread(new ClientReadHandler(selector)).start();

		RequestService requestService = clientService.queryRequest();

		String xmlMsg = XMLUtils.transformToXML(requestService);

		// send msg
		socketChannel.write(Charset.forName(Constants.DEFAULT_EN_CODING).encode(xmlMsg));

	}

	/**
	 * NIO Client Test
	 * 
	 * @param args
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static void main(String[] args) throws IOException, JAXBException {
		new NIOClient("李四").start();
	}

}
