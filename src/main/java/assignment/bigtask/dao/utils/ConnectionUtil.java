package assignment.bigtask.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tool class
 * Used to create and close database connection
 * @author Bennetty74
 *
 */
public class ConnectionUtil {

	private static String userName = "root";

	private static String password = "lian19961128";

	private static String url = "jdbc:mysql://144.202.121.88:3306/spdb";

	private static Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("Load MySQL Driver Failed {}", e);
		}

	}

	/**
	 * Used to get DB Connection
	 * @return java.sql.Connection
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Connection connection = null;
		connection = DriverManager.getConnection(url, userName, password);
		if (null != connection) {
			logger.info("Connection is successful");
			return connection;
		} else {
			throw new Exception("Connection Failed");
		}
	}

	/**
	 * Used to close DB connection
	 * @param connection
	 */
	public static void close(Connection connection) {
		if(null!=connection) {
			try {
				connection.close();
			}catch (Exception e) {
				logger.error("Close DB Connection failed");
			}
		}
	}

}
