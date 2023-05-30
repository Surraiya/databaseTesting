package Database.DataAccessObjects.ResultSetMapper;

import Database.DataEntity.Entities.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static Database.DataEntity.EntityColumns.TestColumns.*;

public class TestMapper {

    public static List<Test> extractTestsFromResultSet(ResultSet resultSet){
        List<Test> tests = new ArrayList<>();
        try {
            while (resultSet.next()){
                tests.add(extractTestFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot retrieve list of tests result from test table of database", e);
        }
        return tests;
    }

    public static Test extractTestFromResultSet(ResultSet resultSet) {
        try {
            return new Test(
                    resultSet.getLong(ID.columnName),
                    resultSet.getString(NAME.columnName),
                    resultSet.getLong(AUTHOR_ID.columnName),
                    resultSet.getLong(PROJECT_ID.columnName),
                    resultSet.getLong(SESSION_ID.columnName),
                    resultSet.getString(ENV.columnName),
                    resultSet.getString(BROWSER.columnName),
                    resultSet.getString(METHOD_NAME.columnName),
                    resultSet.getObject(START_TIME.columnName, LocalDateTime.class),
                    resultSet.getObject(END_TIME.columnName, LocalDateTime.class),
                    resultSet.getInt(STATUS_ID.columnName)
            );
        } catch (SQLException e) {
            throw new RuntimeException("Cannot retrieve test result from test table of database", e);
        }
    }
}
