package tests.TC2;

import Database.DataEntity.Entities.Test;
import Database.DatabaseEntry;
import Database.DatabaseEntryProvider;
import Utilities.JsonFileReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    private DatabaseEntry databaseEntry;
    private List<Test> tests = new ArrayList<>();

    @BeforeTest
    protected void beforeTestExecution() {
        DatabaseEntryProvider databaseEntryProvider = new DatabaseEntryProvider();
        databaseEntry = databaseEntryProvider.getDatabaseEntry();
    }

    @AfterTest
    protected void afterTestExecution() throws SQLException {
        tests = databaseEntry.extractTestsByTwoRepeatingDigitId(JsonFileReader.getIntValue("configData","/testNumber"));
        databaseEntry.insertNewTestsByChangingAuthorProject(tests);
        databaseEntry.deleteNewlyAddedTests(tests);
    }
}
