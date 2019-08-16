package assignment.bigtask.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.jdbc.PreparedStatement;

import assignment.bigtask.dao.utils.ConnectionUtil;
import assignment.bigtask.entity.Stock;

/**
 * Server Operation Class Used to operate stock table in MySQL
 * 
 * @author Bennetty74
 *
 */
public class StockDao {

	/**
	 * log
	 */
	private static Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);

	/**
	 * Used to query stock which has remains
	 * 
	 * @return stocks
	 * @throws Exception
	 */
	public List<Stock> queryStocks() throws Exception {

		logger.info("Strat Query");

		Connection connection = ConnectionUtil.getConnection();
		String query = "SELECT * FROM STOCK WHERE REMAINS > 0";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);

		ResultSet rs = statement.executeQuery();
		List<Stock> stocks = new ArrayList<>();
		Stock stock = null;
		while (rs.next()) {
			stock = new Stock();
			stock.setRemains(rs.getInt("REMAINS"));
			stock.setGoodsCode(rs.getString("GOODS_CODE"));
			stocks.add(stock);
		}
		ConnectionUtil.close(connection);
		logger.info("Stock List {}", stocks);
		return stocks;
	}

	/**
	 * Buy goods by goodsCode
	 * 
	 * @return response msg
	 * @throws Exception
	 */
	public void buyGoodsByCode(String goodsCode, Integer remains) throws Exception {

		logger.info("Strat Updating");

		Connection connection = ConnectionUtil.getConnection();

		String buy = "UPDATE STOCK SET REMAINS = ? WHERE GOODS_CODE = ?";

		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(buy);

		preparedStatement.setInt(1, remains);
		preparedStatement.setString(2, goodsCode);

		preparedStatement.executeUpdate();
		ConnectionUtil.close(connection);
		logger.info("Update Successful");
	}

}
