package Database;

import Database.DataAccessObjects.Implementation.AuthorDao;
import Database.DataAccessObjects.Implementation.ProjectDao;
import Database.DataAccessObjects.Implementation.SessionDao;
import Database.DataAccessObjects.Implementation.TestDao;

public class DatabaseEntryProvider {

    private final AuthorDao authorDao;
    private final ProjectDao projectDao;
    private final SessionDao sessionDao;
    private final TestDao testDao;

    public DatabaseEntryProvider(){
        authorDao = new AuthorDao();
        projectDao = new ProjectDao();
        sessionDao = new SessionDao();
        testDao = new TestDao();
    }

    public DatabaseEntry getDatabaseEntry(){
        return new DatabaseEntry(
                authorDao,
                projectDao,
                sessionDao,
                testDao
        );
    }
}
