package Database.DataAccessObjects.Interface;

import Database.DataEntity.Entities.Test;

import java.util.List;

public interface ITestDao {
    int addTest(Test test);
    long addTestAndGetId(Test test);
    Test getTestById(long id);
    List<Test> getTestByTwoRepeatingDigitId(int testNumber);
    int updateStatusId(long testId, int newStatusId);
    int deleteTest(long id);
}
