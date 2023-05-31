package Database.DbUtil;

import Database.Connection.DatabaseConnection;
import aquality.selenium.core.logging.Logger;

import java.sql.*;
import java.util.stream.IntStream;

public class ExecuteQueryUtil {
    private static final Logger logger = Logger.getInstance();
    private static final Connection connection = DatabaseConnection.getDatabaseConnection();

    public static int executeUpdate(String query, Object... parameters) {
        int rowCount = 0;
        try (PreparedStatement statement = prepareStatementWithoutGeneratedKeys(query, parameters)) {
            rowCount = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to execute update query: " + query);
        }
        return rowCount;
    }

    public static int executeQueryAndGetRowCount(String query, Object... parameters){
        int rowCount = 0;
        try (PreparedStatement statement = prepareStatementWithoutGeneratedKeys(query, parameters)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                rowCount++;
        }catch (SQLException e) {
            logger.error("Failed to execute query and get row count: " + query);
        }
        return rowCount;
    }

    public static ResultSet executeQueryWithoutParameter(String query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            logger.error("Failed to execute query without parameters: " + query);
        }
        return null;
    }

    public static ResultSet executeQueryWithParameter(String query, Object... parameters) {
        try {
            PreparedStatement statement = prepareStatementWithoutGeneratedKeys(query, parameters);
            return statement.executeQuery();
        } catch (SQLException e) {
            logger.error("Failed to execute query with parameters: " + query);
        }
        return null;
    }

    public static int executeAndGetGeneratedId(String query, Object... parameters) {
        int id = 0;
        try (PreparedStatement statement = prepareStatementWithGeneratedKeys(query, parameters)) {
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys != null && generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Failed to execute update and get generated ID: " + query);
        }
        return id;
    }

    private static PreparedStatement prepareStatementWithGeneratedKeys(String query, Object... parameters) {
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setParameters(statement, parameters);
            return statement;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to prepare statement with generated keys: " + query, e);
        }
    }

    private static PreparedStatement prepareStatementWithoutGeneratedKeys(String query, Object... parameters) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            setParameters(statement, parameters);
            return statement;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to prepare statement without generated keys: " + query, e);
        }
    }

    public static void setParameters(PreparedStatement statement, Object... parameters) {
        IntStream.range(0, parameters.length)
                .forEach(i -> {
                    try {
                        statement.setObject(i + 1, parameters[i]);
                    } catch (SQLException e) {
                        logger.error("Failed to set parameter for statement: " + statement);
                    }
                });
    }
}
