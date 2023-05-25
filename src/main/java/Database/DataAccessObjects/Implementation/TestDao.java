package Database.DataAccessObjects.Implementation;

import Database.Connection.DatabaseConnection;
import Database.DataAccessObjects.Interface.ITestDao;
import Database.DataEntity.Entities.Test;
import Database.DataEntity.EntityColumns.TestColumns;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Database.DataAccessObjects.EntityMapper.TestMapper.*;
import static Database.DataEntity.EntityColumns.Columns.FIRST;
import static Database.DataEntity.EntityColumns.Columns.SECOND;
import static Database.DataEntity.EntityColumns.TestColumns.*;


public class TestDao implements ITestDao {

    private final Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final String addTestQuery = String.format("INSERT INTO test(%s) VALUES (?,?,?,?,?,?,?,?,?,?)",
            Stream.of(TestColumns.values())
                    .filter(column -> column != ID)
                    .map(column -> column.columnName)
                    .collect(Collectors.joining(",")));
    private final String getAllTestsQuery = "SELECT * FROM test";
    private final String getTestByNameQuery = String.format("SELECT * FROM test WHERE %s = ?", NAME.columnName);
    private final String getTestByTwoRepeatingDigitIdQuery = String.format("SELECT * FROM test WHERE %s REGEXP '[0-9]*([0-9])\\1[0-9]*' ORDER BY RAND() LIMIT ?", ID.columnName);
    private final String deleteTestQuery = String.format("DELETE FROM test WHERE %s = ?",ID.columnName);
    private final String updateStatusIdQuery = String.format("UPDATE test SET %s = ? WHERE %s = ?",
            STATUS_ID.columnName,
            ID.columnName);

    public TestDao(){
        connection = DatabaseConnection.getDatabaseConnection();
    }

    @Override
    public boolean addTest(Test test) throws SQLException {
        preparedStatement = connection.prepareStatement(addTestQuery);
        mapTestToInsertStatement(preparedStatement,test);
        return preparedStatement.execute();
    }

    @Override
    public List<Test> getAllTests() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(getAllTestsQuery);
        List<Test> tests = new ArrayList<>();
        while (resultSet.next()){
            tests.add(extractTestFromResultSet(resultSet));
        }
        return tests;
    }

    @Override
    public Test getTestByName(String name) throws SQLException {
        preparedStatement = connection.prepareStatement(getTestByNameQuery);
        preparedStatement.setString(FIRST.index, name);
        resultSet = preparedStatement.executeQuery();

        return resultSet.next() ? extractTestFromResultSet(resultSet) : null;
    }

    @Override
    public boolean updateStatusId(Test test, int statusId) throws SQLException {
        preparedStatement = connection.prepareStatement(updateStatusIdQuery);
        preparedStatement.setInt(FIRST.index,statusId);
        preparedStatement.setLong(SECOND.index,test.getId());
        return preparedStatement.execute();
    }

    @Override
    public boolean deleteTest(long id) throws SQLException {
        preparedStatement = connection.prepareStatement(deleteTestQuery);
        preparedStatement.setLong(FIRST.index, id);
        return preparedStatement.execute();
    }

    @Override
    public List<Test> getTestByTwoRepeatingDigitId(int testNumber) throws SQLException {
        preparedStatement = connection.prepareStatement(getTestByTwoRepeatingDigitIdQuery);
        preparedStatement.setInt(FIRST.index, testNumber);
        resultSet = preparedStatement.executeQuery();

        List<Test> tests = new ArrayList<>();
        while (resultSet.next()) {
            tests.add(extractTestFromResultSet(resultSet));
        }
        return tests;
    }

    public boolean ifTestExistsInDb(String name) throws SQLException {
        List<Test> tests = getAllTests();
        return tests.stream().anyMatch(test -> test.getName().equals(name));
    }
}
