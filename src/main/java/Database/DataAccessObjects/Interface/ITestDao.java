package Database.DataAccessObjects.Interface;

import Database.DataEntity.Entities.Test;

import java.util.List;

public interface ITestDao {
    int addTest(Test test);
    List<Test> getAllTests();
    Test getTestById(long id);
    List<Test> getTestByTwoRepeatingDigitId(int testNumber);
    int updateStatusId(long testId, int currentStatusId, List<Integer> statusIds);
    int deleteTest(long id);
}
