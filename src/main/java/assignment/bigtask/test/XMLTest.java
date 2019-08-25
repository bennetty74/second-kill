//package assignment.bigtask.test;
//
//import java.io.File;
//
//import javax.xml.bind.JAXBException;
//import assignment.bigtask.utils.XMLUtils;
//import assignment.bigtask.xml.request.QueryRequestBody;
//import assignment.bigtask.xml.request.QueryRequestService;
//import assignment.bigtask.xml.request.RequestHeader;
//
//public class XMLTest {
//
//	public static void main(String[] args) throws JAXBException {
//
//		/**
//		 * XML to object test
//		 */
//		File file = new File("src/main/resources/input.xml");
//		
//		String xmlString="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + 
//				"<requestService>\r\n" + 
//				"    <requestHeader>\r\n" + 
//				"        <tranCode>OR01</tranCode>\r\n" + 
//				"        <tranDate>1876.09.07</tranDate>\r\n" + 
//				"        <tranTime>13:12</tranTime>\r\n" + 
//				"    </requestHeader>\r\n" + 
//				"    <body/>\r\n" + 
//				"</requestService>";
//		
//		XMLUtils.transformToObject(new QueryRequestService(),xmlString);
//		System.out.println(XMLUtils.transformToObject(new QueryRequestService(), xmlString));
//
//		/**
//		 * object to xml test
//		 */
//		System.out.println("==========================================");
//		QueryRequestService requestService = new QueryRequestService();
//		RequestHeader requestHeader = new RequestHeader();
//		requestHeader.setTranCode("OR01");
//		requestHeader.setTranDate("1876.09.07");
//		requestHeader.setTranTime("13:12");
//		requestService.setRequestHeader(requestHeader);
//		QueryRequestBody requestBody = new QueryRequestBody();
//		requestService.setQueryStockRequestBody(requestBody);
//		
//		System.out.println(XMLUtils.transformToXML(requestService));
//		
//		XMLUtils.transformToXML(requestService, file);
//
//	}
//
//}
