package assignment.bigtask.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLUtils {

	private static JAXBContext jaxbContext;

	private static Marshaller marshaller;

	private static Unmarshaller unmarshaller;

	/**
	 * log
	 */
	private static Logger logger = LoggerFactory.getLogger(XMLUtils.class);

	/**
	 * parse object to xml in xmlString
	 * 
	 * @param o
	 * @param outputStream
	 * @throws JAXBException
	 */
	public static String transformToXML(Object o) throws JAXBException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		jaxbContext = JAXBContext.newInstance(o.getClass());
		marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(o, byteArrayOutputStream);

		return new String(byteArrayOutputStream.toByteArray());
	}

	/**
	 * parse object to xml in file
	 * 
	 * @param o
	 * @param file
	 * @throws JAXBException
	 */
	public static void transformToXML(Object o, File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(o.getClass());
		marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(o, file);
	}

	/**
	 * parse String XML to object in inputstream
	 * 
	 * @param o
	 * @param inputStream
	 * @return
	 * @throws JAXBException
	 */
	public static Object transformToObject(Object o, String xml) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(o.getClass());
		unmarshaller = jaxbContext.createUnmarshaller();
		InputStream inputStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		o = unmarshaller.unmarshal(inputStream);
		logger.info("XML to Object is {}", o);
		return o;
	}

	/**
	 * parse xml to object in file
	 * 
	 * @param o
	 * @param file
	 * @return
	 * @throws JAXBException
	 */
	public static Object transformToObject(Object o, File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(o.getClass());
		unmarshaller = jaxbContext.createUnmarshaller();
		o = unmarshaller.unmarshal(file);
		logger.info("XML to Object is {}", o);
		return o;
	}

}
