package Database;

import Database.Connection.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DbExecuteQueryUtil {
    private static final Connection connection = DatabaseConnection.getDatabaseConnection();

    public static int executeUpdate(String query, Object... parameters) {
        try(PreparedStatement statement = preparedStatementWithoutGeneratedKeys(query, parameters)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ResultSet executeQueryWithoutParameter(String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet executeQueryWithParameter(String query, Object... parameters) {
        try (PreparedStatement statement = preparedStatementWithoutGeneratedKeys(query, parameters)) {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Integer> executeAndGetGeneratedIds(String query, Object... parameters) {
        List<Integer> generatedIds = new ArrayList<>();
        try (PreparedStatement statement = prepareStatementWithGeneratedKeys(query, parameters)) {
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                generatedIds.add(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedIds;
    }

    public static int executeAndGetGeneratedId(String query, Object... parameters) {
        try (PreparedStatement statement = prepareStatementWithGeneratedKeys(query, parameters)) {
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static PreparedStatement prepareStatementWithGeneratedKeys(String query, Object... parameters) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setParameters(statement, parameters);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to prepare statement with generated keys: " + query,  e);
        }
        return statement;
    }

    private static PreparedStatement preparedStatementWithoutGeneratedKeys(String query, Object... parameters){
        PreparedStatement statement;
        try{
            statement = connection.prepareStatement(query);
            setParameters(statement, parameters);
        } catch (SQLException e){
            throw new RuntimeException("Failed to prepare statement without generated keys: " + query, e);
        }
        return statement;
    }

    public static void setParameters(PreparedStatement statement, Object... parameters) {
        IntStream.range(0, parameters.length)
                .forEach(i -> {
                    try {
                        statement.setObject(i + 1, parameters[i]);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }
}
