package Database.DataAccessObjects.Implementation;

import Database.DataAccessObjects.Interface.ITestDao;
import Database.DataEntity.Entities.Test;
import Database.DataEntity.EntityColumns.TestColumns;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Database.DataEntity.EntityColumns.TestColumns.*;
import static Database.DbUtil.ExecuteQueryUtil.*;
import static Database.DbUtil.ResultSetUtil.extractTestFromResultSet;
import static Database.DbUtil.ResultSetUtil.extractTestsFromResultSet;


public class TestDao implements ITestDao {

    private final String addTestQuery = String.format("INSERT INTO test(%s) VALUES (?,?,?,?,?,?,?,?,?,?)",
            Stream.of(TestColumns.values())
                    .filter(column -> column != ID)
                    .map(column -> column.columnName)
                    .collect(Collectors.joining(",")));
    private final String getTestByIdQuery = String.format("SELECT * FROM test WHERE %s = ?",
            ID.columnName);
    private final String getTestsByTwoRepeatingDigitIdQuery = String.format("SELECT * FROM test WHERE %s REGEXP '([0-9])\\\\1' ORDER BY RAND() LIMIT ?",
            ID.columnName);
    private final String deleteTestQuery = String.format("DELETE FROM test WHERE %s = ?",
            ID.columnName);
    private final String updateStatusIdQuery = String.format("UPDATE test SET %s = ? WHERE %s = ?",
            STATUS_ID.columnName,
            ID.columnName);


    @Override
    public int addTest(Test test) {
        return executeUpdate(addTestQuery, testParameters(test));
    }

    @Override
    public long addTestAndGetId(Test test){
        return executeAndGetGeneratedId(addTestQuery, testParameters(test));
    }

    private Object[] testParameters(Test test) {
        return new Object[] {
                test.getName(),
                test.getAuthor_id(),
                test.getProject_id(),
                test.getSession_id(),
                test.getEnv(),
                test.getBrowser(),
                test.getMethod_name(),
                test.getStart_time(),
                test.getEnd_time(),
                test.getStatus_id()
        };
    }

    @Override
    public Test getTestById(long id){
        ResultSet resultSet = executeQueryWithParameter(getTestByIdQuery, id);
        return resultSet != null ? extractTestFromResultSet(resultSet) : null;
    }

    @Override
    public int updateStatusId(long testId, int newStatusId) {
        return executeUpdate(updateStatusIdQuery, newStatusId, testId);
    }

    @Override
    public int deleteTest(long id) {
        return executeUpdate(deleteTestQuery, id);
    }

    @Override
    public List<Test> getTestByTwoRepeatingDigitId(int testNumber) {
        ResultSet resultSet = executeQueryWithParameter(getTestsByTwoRepeatingDigitIdQuery, testNumber);
        return resultSet != null ? extractTestsFromResultSet(resultSet) : null;
    }
}
