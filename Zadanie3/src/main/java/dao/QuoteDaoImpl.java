package dao;

import model.Quote;

import java.sql.*;
import java.util.Optional;

public class QuoteDaoImpl implements QuoteDao {
    private Connection connection;

    public QuoteDaoImpl(ConnectionProvider connectionProvider) {
        try {
            this.connection = connectionProvider.getConnection();
            createTable();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void save(Quote quote) {
        final String saveQuery = "INSERT INTO QUOTES (quote) VALUES(?)";
        try(PreparedStatement statement = connection.prepareStatement(saveQuery)) {
            statement.setString(1,quote.getQuote());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Quote> findByQuote(String quote)  {
        final String findQuery = "SELECT quote FROM quotes WHERE quote = (?)";
        try(PreparedStatement statement = connection.prepareStatement(findQuery)) {
            statement.setString(1,quote);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String quoteContent = resultSet.getString("quote");
                return Optional.of(new Quote(quoteContent));

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int countQuotes() {
        String countQuery = "SELECT COUNT(*) AS countQuotes FROM quotes";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(countQuery);
            resultSet.next();
            return resultSet.getInt("countQuotes");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public void deleteAllQuotes() {
        final String deleteQuery = "DELETE FROM quotes";
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(deleteQuery);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    private void createTable() throws SQLException {
        final String createTableQuery = "CREATE TABLE IF NOT EXISTS QUOTES (quote VARCHAR(1000) PRIMARY KEY NOT NULL)";

        Statement statement = connection.createStatement();
        statement.executeUpdate(createTableQuery);
    }




}
