package Database.DbUtil;

import Database.DataAccessObjects.Implementation.*;
import Database.DataEntity.Entities.Author;
import Database.DataEntity.Entities.Project;
import Database.DataEntity.Entities.Session;
import Database.DataEntity.Entities.Test;
import aquality.selenium.core.logging.Logger;

import java.time.LocalDateTime;
import java.util.List;

import static Utilities.RandomGenerator.getRandomInteger;

public class DatabaseUtil {

    private static final AuthorDao authorDao = new AuthorDao();
    private static final ProjectDao projectDao = new ProjectDao();
    private static final SessionDao sessionDao = new SessionDao();
    private static final StatusDao statusDao = new StatusDao();
    private static final TestDao testDao = new TestDao();

    private static final Logger logger = Logger.getInstance();

    public static long saveAuthorAndRetrieveId(String name, String login, String email) {
        long authorId;
        if (authorDao.ifAuthorExistsInDb(login)) {
            authorId = authorDao.getAuthorIdByLogin(login);
            logger.info("Author already exists. Author id is %d", authorId);
            return authorId;
        } else {
            authorId = authorDao.addAuthorAndGetId(new Author(name, login, email));
            logger.info("Insert new Author and generated author id is %d", authorId);
            return authorId;
        }
    }

    public static long saveProjectAndRetrieveId(String name){
        long id;
        if (projectDao.ifProjectExistsInDb(name)) {
            id = projectDao.getProjectIdByName(name);
            logger.info("Project Already exist. Project id is %d", id);
            return id;
        } else{
            id = projectDao.addProjectAndGetId(new Project(name));
            logger.info("Insert new Project and generated author id is %d", id);
            return id;
        }
    }

    public static long saveSessionAndRetrieveId(long buildNumber, LocalDateTime createdTime, String key){
        long id = sessionDao.addSessionAndGetId(new Session(buildNumber, createdTime, key));
        logger.info("Insert new Session and generated session id is %d", id);
        return id;
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
        return testDao.addTest(new Test(
                name,
                author_id,
                project_id,
                session_id,
                env,
                browser,
                method_name,
                start_time,
                end_time,
                status_id
        ));
    }

    public static long saveTestAndRetrieveId(Test test){
        long id = testDao.addTestAndGetId(test);
        logger.info("Insert new test and generated id is %d", id);
        return id;
    }

    public static List<Test> getTestsByTwoRepeatingDigitIdFromDB(int testNumber){
        return testDao.getTestByTwoRepeatingDigitId(testNumber);
    }

    public static Test getTestById(long testId){
        Test test = testDao.getTestById(testId);
        logger.info(String.format("Test ID: %d, Test: %s", testId, test));
        return test;
    }

    public static List<Integer> getAllStatusIds(){
        return statusDao.getAllStatusIds();
    }

    public static int selectRandomStatusId(int currentStatusId, List<Integer> statusIds){
        List<Integer> remainingStatusIds = statusIds.stream()
                .filter(id -> id != currentStatusId)
                .toList();

        int newStatusId = remainingStatusIds.get(getRandomInteger(remainingStatusIds.size()));
        logger.info(String.format("New status id: %d", newStatusId));
        return newStatusId;
    }

    public static void updateStatusId(long testId, int newStatusId){
        if (testDao.updateStatusId(testId, newStatusId) == 1)
            logger.info("Status id has been updated");
    }

    public static void deleteTestRecord(long testId){
        testDao.deleteTest(testId);
    }

    public static void deleteAuthor(long authorId){
        authorDao.deleteAuthorById(authorId);
    }

    public static void deleteProject(long projectId){
        projectDao.deleteProjectById(projectId);
    }
}
