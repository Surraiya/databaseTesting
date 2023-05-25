package Database.DataAccessObjects.Interface;

import Database.DataEntity.Entities.Test;

import java.sql.SQLException;
import java.util.List;

public interface ITestDao {
    boolean addTest(Test test) throws SQLException;
    List<Test> getAllTests() throws SQLException;
    Test getTestByName(String name) throws SQLException;
    List<Test> getTestByTwoRepeatingDigitId(int testNumber) throws SQLException;
    boolean updateStatusId(Test test, int statusId) throws SQLException;
    boolean deleteTest(long id) throws SQLException;
}
