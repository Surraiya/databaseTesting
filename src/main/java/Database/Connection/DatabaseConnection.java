package Database.Connection;

import aquality.selenium.core.logging.Logger;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

import static Utilities.JsonFileReader.getStringValue;


public class DatabaseConnection {
    private static final String JDBC_URL = String.format("%s%s",getStringValue("configData","/db/url"),getStringValue("configData","/db/database"));
    private static final String USERNAME = getStringValue("configData","/db/username");
    private static final String PASSWORD = getStringValue("configData","/db/password");
    private static Connection connection;

    private DatabaseConnection(){}

    @SneakyThrows
    public static Connection getDatabaseConnection(){
        if(connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }
        return connection;
    }
}
