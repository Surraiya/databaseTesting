package Database.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static Utilities.FileReader.FileKeyName.DbKeyName.*;
import static Utilities.FileReader.FileName.DATABASE;
import static Utilities.FileReader.JsonFileReader.getStringValue;


public class DatabaseConnection {
    private static final String JDBC_URL = String.format(
            "%s%s",
            getStringValue(DATABASE.fileName, DB_URL.key),
            getStringValue(DATABASE.fileName,DB_NAME.key)
    );
    private static final String USERNAME = getStringValue(DATABASE.fileName, DB_USERNAME.key);
    private static final String PASSWORD = getStringValue(DATABASE.fileName, DB_PASSWORD.key);
    private static Connection connection;

    private DatabaseConnection(){}

    public static Connection getDatabaseConnection(){

        try {
            if(connection == null || connection.isClosed())
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish database connection.", e);
        }

        return connection;
    }
}
