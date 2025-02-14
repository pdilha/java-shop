package shop.dao;

import shop.data.DbFunctions;
import shop.model.Trade;
import shop.model.TradeType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TradeDAO {

    public List<Trade> allTrades() {

        List<Trade> trades = new ArrayList<>();

        String sql = "SELECT * FROM trades";

        try (Connection connection = DbFunctions.connectToDb(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String tradeTypeString = resultSet.getString("trade_type");

                TradeType tradeType = TradeType.valueOf(tradeTypeString);

                Trade trade = new Trade(resultSet.getInt("trade_id"), tradeType, resultSet.getInt("quantity"), resultSet.getFloat("price"));
                trades.add(trade);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return trades;
    }

    public void addTrade(Trade trade) {

        String sql = "INSERT INTO trades (trade_type, quantity, price) VALUES (?, ?, ?)";

        try (Connection connection = DbFunctions.connectToDb(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {


            preparedStatement.setObject(1, trade.getTradeType(), java.sql.Types.OTHER);
            preparedStatement.setInt(2, trade.getQuantity());
            preparedStatement.setFloat(3, trade.getPrice());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
