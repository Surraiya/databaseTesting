package Database.DbUtil;

import Database.DataAccessObjects.Implementation.*;
import Database.DataEntity.Entities.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static Utilities.RandomGenerator.getRandomInteger;

public class DatabaseUtil {

    private static final AuthorDao authorDao = new AuthorDao();
    private static final ProjectDao projectDao = new ProjectDao();
    private static final SessionDao sessionDao = new SessionDao();
    private static final StatusDao statusDao = new StatusDao();
    private static final TestDao testDao = new TestDao();


    public static long saveAuthorAndRetrieveId(String name, String login, String email){
        if (authorDao.ifAuthorExistsInDb(login))
            return authorDao.getAuthorIdByLogin(login);
        else return authorDao.addAuthorAndGetId(name, login, email);
    }

    public static void deleteAuthor(long authorId){
        authorDao.deleteAuthorById(authorId);
    }

    public static void deleteProject(long projectId){
        projectDao.deleteProjectById(projectId);
    }

    public static long saveProjectAndRetrieveId(String name){
        if (projectDao.ifProjectExistsInDb(name))
            return projectDao.getProjectIdByName(name);
        else return projectDao.addProjectAndGetId(name);
    }

    public static long saveSessionAndRetrieveId(long buildNumber, LocalDateTime createdTime, String key){
        if (sessionDao.ifSessionExistsInDb(key))
            return sessionDao.getSessionIdByKey(key);
        else return sessionDao.addSessionAndGetId(buildNumber, createdTime, key);
    }

    public static int saveTestResult(String name,
                                     long author_id,
                                     long project_id,
                                     long session_id,
                                     String env,
                                     String browser,
                                     String method_name,
                                     LocalDateTime start_time,
                                     LocalDateTime end_time,
                                     int status_id){
        return testDao.addTest(name, author_id, project_id, session_id, env, browser, method_name, start_time, end_time, status_id);
    }

    public static long saveTestAndRetrieveId(Test test){
        return testDao.addTestAndGetId(test);
    }

    public static List<Test> getTestsByTwoRepeatingDigitIdFromDB(int testNumber){
        return testDao.getTestByTwoRepeatingDigitId(testNumber);
    }

    public static Test getTestById(long testId){
        return testDao.getTestById(testId);
    }

    public static List<Integer> getAllStatusIds(){
        return statusDao.getAllStatusIds();
    }

    public static int selectRandomStatusId(int currentStatusId, List<Integer> statusIds){
        List<Integer> remainingStatusIds = statusIds.stream()
                .filter(id -> id != currentStatusId)
                .collect(Collectors.toList());

        return remainingStatusIds.get(getRandomInteger(remainingStatusIds.size()));
    }

    public static void updateStatusId(long testId, int newStatusId){
        testDao.updateStatusId(testId, newStatusId);
    }

    public static void deleteTestRecord(long testId){
        testDao.deleteTest(testId);
    }
}
