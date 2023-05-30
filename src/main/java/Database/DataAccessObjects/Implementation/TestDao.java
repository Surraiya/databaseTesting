package Database.DataAccessObjects.Implementation;

import Database.DataAccessObjects.Interface.ITestDao;
import Database.DataEntity.Entities.Test;
import Database.DataEntity.EntityColumns.TestColumns;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Database.DataAccessObjects.ResultSetMapper.TestMapper.*;
import static Database.DataEntity.EntityColumns.TestColumns.*;
import static Database.DbExecuteQueryUtil.*;
import static Utilities.RandomGenerator.getRandomInteger;


public class TestDao implements ITestDao {

    private final String addTestQuery = String.format("INSERT INTO test(%s) VALUES (?,?,?,?,?,?,?,?,?,?)",
            Stream.of(TestColumns.values())
                    .filter(column -> column != ID)
                    .map(column -> column.columnName)
                    .collect(Collectors.joining(",")));
    private final String getAllTestsQuery = "SELECT * FROM test";
    private final String getTestByIdQuery = String.format("SELECT * FROM test WHERE %s = ?", ID.columnName);
    private final String getTestByTwoRepeatingDigitIdQuery = String.format("SELECT * FROM test WHERE %s REGEXP '[0-9]*([0-9])\\1[0-9]*' ORDER BY RAND() LIMIT ?", ID.columnName);
    private final String deleteTestQuery = String.format("DELETE FROM test WHERE %s = ?",ID.columnName);
    private final String updateStatusIdQuery = String.format("UPDATE test SET %s = ? WHERE %s = ?",
            STATUS_ID.columnName,
            ID.columnName);


    @Override
    public int addTest(Test test) {
        Object[] parameters = {
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
        return executeAndGetGeneratedId(addTestQuery, parameters);
    }

    @Override
    public List<Test> getAllTests() {
        ResultSet resultSet = executeQueryWithoutParameter(getAllTestsQuery);
        return extractTestsFromResultSet(resultSet);
    }

    @Override
    public Test getTestById(long id){
        ResultSet resultSet = executeQueryWithoutParameter(getTestByIdQuery);
        return extractTestFromResultSet(resultSet);
    }

    @Override
    public int updateStatusId(long testId, int currentStatusId, List<Integer> statusIds) {
        List<Integer> remainingStatusIds = statusIds.stream()
                .filter(id -> id != currentStatusId)
                .collect(Collectors.toList());

        int newStatusId = remainingStatusIds.get(getRandomInteger(remainingStatusIds.size()));

        Object[] parameters = {
                newStatusId,
                testId
        };

        return executeUpdate(updateStatusIdQuery, parameters);
    }

    @Override
    public int deleteTest(long id) {
        Object[] parameters ={
                id
        };
        return executeUpdate(deleteTestQuery, parameters);
    }

    @Override
    public List<Test> getTestByTwoRepeatingDigitId(int testNumber) {
        Object[] parameters = {
                testNumber
        };
        ResultSet resultSet = executeQueryWithParameter(getTestByTwoRepeatingDigitIdQuery, parameters);
        return extractTestsFromResultSet(resultSet);
    }

    public boolean ifTestExistsInDb(long id) {
        List<Test> tests = getAllTests();
        return tests.stream()
                .anyMatch(test -> test.getId() == id);
    }
}
