package Database;

import Database.DataAccessObjects.Implementation.AuthorDao;
import Database.DataAccessObjects.Implementation.ProjectDao;
import Database.DataAccessObjects.Implementation.SessionDao;
import Database.DataAccessObjects.Implementation.TestDao;
import Database.DataEntity.Entities.Author;
import Database.DataEntity.Entities.Project;
import Database.DataEntity.Entities.Session;
import Database.DataEntity.Entities.Test;
import org.jetbrains.annotations.NotNull;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static Utilities.JsonFileReader.*;


public class DatabaseEntry {

    private final AuthorDao authorDao;
    private final ProjectDao projectDao;
    private final SessionDao sessionDao;
    private final TestDao testDao;
    private Session session;

    public DatabaseEntry(AuthorDao authorDao,
                         ProjectDao projectDao,
                         SessionDao sessionDao,
                         TestDao testDao){
        this.authorDao = authorDao;
        this.projectDao = projectDao;
        this.testDao = testDao;
        this.sessionDao = sessionDao;
    }

    public void saveTestResultIntoDB(ITestResult result, ITestContext context) throws SQLException {

        Author author1 = setAuthor1Entry();
        addAuthorEntryIntoDB(author1);

        Project project1 = setProject1Entry();
        addProjectEntryIntoDB(project1);

        session = setSessionEntry(context);
        addSessionEntryIntoDB(session);

        Test test1 = setTestEntry(result, author1, project1, session);
        addTestEntryIntoDB(test1);
    }

    public void insertNewTestsByChangingAuthorProject(List<Test> tests) throws SQLException {

        Author author2 = setAuthor2Entry();
        addAuthorEntryIntoDB(author2);

        Project project2 = setProject2Entry();
        addProjectEntryIntoDB(project2);

        addChangedAuthorProjectInTestTable(tests, author2, project2);
    }

    public void deleteNewlyAddedTests(List<Test> tests) throws SQLException {
        for (Test test : tests){
            testDao.deleteTest(test.getId());
        }
    }

    public void changeStatusId(List<Test> tests) throws SQLException {
        for (Test test : tests){
            testDao.updateStatusId(test, getIntValue("configData", "/statusId"));
        }
    }

    public List<Test> extractTestsByTwoRepeatingDigitId(int testNumber) throws SQLException {
        return testDao.getTestByTwoRepeatingDigitId(testNumber);
    }

    public void addChangedAuthorProjectInTestTable(List<Test> tests, Author author, Project project) throws SQLException {
        for (Test test : tests){
            test.setAuthor_id(author.getId());
            test.setProject_id(project.getId());
            testDao.addTest(test);
        }
    }

    public Author setAuthor1Entry(){
        return new Author(
                getStringValue("configData","/project/author1/name"),
                getStringValue("configData","/project/author1/login"),
                getStringValue("configData","/project/author1/email")
        );
    }

    public Author setAuthor2Entry(){
        return new Author(
                getStringValue("configData","/project/author2/name"),
                getStringValue("configData","/project/author2/login"),
                getStringValue("configData","/project/author2/email")
        );
    }

    public void addAuthorEntryIntoDB(Author author) throws SQLException {
        if (authorDao.ifAuthorExistsInDb(author.getLogin()))
            authorDao.getAuthorByLogin(author.getLogin());
        else authorDao.addAuthor(author);
    }

    public Project setProject1Entry(){
        return new Project(
                getStringValue("configData","/project/name1")
        );
    }

    public Project setProject2Entry(){
        return new Project(
                getStringValue("configData","/project/name2")
        );
    }

    public void addProjectEntryIntoDB(Project project) throws SQLException {
        if(projectDao.ifProjectExistsInDb(project.getName()))
            projectDao.getProjectByName(project.getName());
        else projectDao.addProject(project);
    }

    public Session setSessionEntry(@NotNull ITestContext context) {

        return new Session(
                getLongValue("configData","/session/buildNumber"),
                LocalDateTime.ofInstant(context.getStartDate().toInstant(),ZoneId.systemDefault()),
                getStringValue("configData","/session/key")
        );
    }

    public void addSessionEntryIntoDB(Session session) throws SQLException {
        if(sessionDao.ifSessionExistsInDb(session.getSession_key()))
            sessionDao.getSessionByKey(session.getSession_key());
        else sessionDao.addSession(session);
    }

    public Test setTestEntry(@NotNull ITestResult result, Author author, Project project, Session session){
        return new Test(
                result.getTestClass().getName(),
                author.getId(),
                project.getId(),
                session.getId(),
                getStringValue("configData","/project/env"),
                getStringValue("configData","/project/browser"),
                result.getMethod().getMethodName(),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(result.getStartMillis()),ZoneId.systemDefault()),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(result.getEndMillis()),ZoneId.systemDefault()),
                result.getStatus()
        );
    }

    public void addTestEntryIntoDB(Test test) throws SQLException {
        if (testDao.ifTestExistsInDb(test.getName()))
            testDao.getTestByName(test.getName());
        else testDao.addTest(test);
    }
}
