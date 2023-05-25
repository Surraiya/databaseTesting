package tests.TC1;

import Database.DatabaseEntry;
import Database.DatabaseEntryProvider;
import Utilities.JsonFileReader;
import aquality.selenium.core.logging.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.sql.SQLException;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public abstract class BaseTest {
    private DatabaseEntry databaseEntry;
    protected Logger logger = Logger.getInstance();

    @BeforeMethod
    protected void beforeTestExecution() {
        DatabaseEntryProvider databaseEntryProvider = new DatabaseEntryProvider();
        databaseEntry = databaseEntryProvider.getDatabaseEntry();
        initializeBrowser();
    }

    public void initializeBrowser() {
        getBrowser().maximize();
        getBrowser().goTo(JsonFileReader.getStringValue("settings","/startUrl"));
        getBrowser().waitForPageToLoad();
    }

    @AfterMethod
    protected void afterTestExecution(ITestResult result, ITestContext context) throws SQLException {
        closeBrowser();
        databaseEntry.saveTestResultIntoDB(result, context);
    }

    public void closeBrowser() {
        getBrowser().quit();
    }
}
