package tests.TC1;

import Utilities.FileReader.JsonFileReader;
import aquality.selenium.core.logging.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static Database.DbUtil.DatabaseUtil.*;
import static Utilities.FileReader.FileKeyName.ConfigDataKeyName.*;
import static Utilities.FileReader.FileKeyName.SettingsKeyName.START_URL;
import static Utilities.FileReader.FileName.CONFIG_DATA;
import static Utilities.FileReader.FileName.SETTINGS;
import static Utilities.FileReader.JsonFileReader.getLongValue;
import static Utilities.FileReader.JsonFileReader.getStringValue;
import static aquality.selenium.browser.AqualityServices.getBrowser;

public abstract class BaseTest {
    protected Logger logger = Logger.getInstance();

    @BeforeMethod
    protected void initializeBrowser() {
        getBrowser().maximize();
        getBrowser().goTo(JsonFileReader.getStringValue(SETTINGS.fileName, START_URL.key));
        getBrowser().waitForPageToLoad();
    }

    @AfterMethod
    protected void afterTestExecution(ITestResult result, ITestContext context) {
        getBrowser().quit();
        
        logger.info("Add a result of each completed test in the database as a new record in the Test table");
        long authorId = saveAuthorAndRetrieveId(
                getStringValue(CONFIG_DATA.fileName, AUTHOR_NAME.key),
                getStringValue(CONFIG_DATA.fileName, AUTHOR_LOGIN.key),
                getStringValue(CONFIG_DATA.fileName, AUTHOR_EMAIL.key)
        );
        long projectId = saveProjectAndRetrieveId(
                getStringValue(CONFIG_DATA.fileName, PROJECT_NAME.key)
        );
        long sessionId = saveSessionAndRetrieveId(
                getLongValue(CONFIG_DATA.fileName, SESSION_BUILDNUMBER.key),
                LocalDateTime.ofInstant(context.getStartDate().toInstant(),ZoneId.systemDefault()),
                getStringValue(CONFIG_DATA.fileName, SESSION_KEY.key)
        );

        int effectedRow = saveTestResult(
                result.getTestClass().getName(),
                authorId,
                projectId,
                sessionId,
                getStringValue(CONFIG_DATA.fileName, ENV.key),
                getStringValue(CONFIG_DATA.fileName, BROWSER.key),
                result.getMethod().getMethodName(),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(result.getStartMillis()), ZoneId.systemDefault()),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(result.getEndMillis()),ZoneId.systemDefault()),
                result.getStatus()
        );
        logger.info("test name: %s", result.getTestClass().getName());
        logger.info("Author id: %d", authorId);
        logger.info("project id: %d", projectId);
        logger.info("session id: %d", sessionId);
        logger.info("method name: %s", result.getMethod().getMethodName());
        logger.info("start time: %s", LocalDateTime.ofInstant(Instant.ofEpochMilli(result.getStartMillis()), ZoneId.systemDefault()));
        logger.info("end time: %s", LocalDateTime.ofInstant(Instant.ofEpochMilli(result.getEndMillis()),ZoneId.systemDefault()));
        logger.info("status id: %d", result.getStatus());

        Assert.assertEquals(effectedRow, 1, "Information should be added in Test table as a new row.");
    }
}
