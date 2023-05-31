package Database.DbUtil;

import Database.DataEntity.Entities.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static Database.DataEntity.EntityColumns.TestColumns.*;

public class ResultSetUtil {

    public static List<Integer> getIntListFromResultSet(ResultSet resultSet, String columnName) {
        List<Integer> resultList = new ArrayList<>();
        try {
            while (resultSet.next()){
                int value = resultSet.getInt(columnName);
                resultList.add(value);
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve integer data from resultset", e);
        }
    }

    public static long getLongFromResultSet(ResultSet resultSet, int columnIndex) {
        try {
            if (resultSet.next())
                return resultSet.getLong(columnIndex);
            else return 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error in extracting long data", e);
        }
    }

    public static List<Test> extractTestsFromResultSet(ResultSet resultSet){
        List<Test> tests = new ArrayList<>();
        try {
            while (resultSet.next()){
                tests.add(extractTestFromResultSet(resultSet));
            }
            return tests;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot retrieve list of tests result from test table of database", e);
        }
    }

    public static Test extractTestFromResultSet(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
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
            } else return null;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot retrieve test result from test table of database", e);
        }
    }
}
