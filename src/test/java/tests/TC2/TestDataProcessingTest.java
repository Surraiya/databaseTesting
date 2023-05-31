package tests.TC2;

import Database.DataEntity.Entities.Test;
import aquality.selenium.core.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

import static Database.DbUtil.DatabaseUtil.*;
import static Utilities.FileReader.FileKeyName.ConfigDataKeyName.*;
import static Utilities.FileReader.FileKeyName.ConfigDataKeyName.AUTHOR_EMAIL;
import static Utilities.FileReader.FileName.CONFIG_DATA;
import static Utilities.FileReader.JsonFileReader.getIntValue;
import static Utilities.FileReader.JsonFileReader.getStringValue;

public class TestDataProcessingTest {

    private static final Logger logger = Logger.getInstance();
    private static final List<Long> repeatingDigitTestIds = new ArrayList<>();
    private static final List<Long> copiedTestIds = new ArrayList<>();
    private static long authorId;
    private static long projectId;

    @BeforeMethod
    public void copyRepeatingDigitTestsWithAuthorAndProject() {

        logger.info("Select tests from the database where ID contains two random repeating digits");
        List<Test> testsByTwoRepeatingDigit = getTestsByTwoRepeatingDigitIdFromDB(getIntValue(CONFIG_DATA.fileName, TEST_NUMBER.key));

        authorId = saveAuthorAndRetrieveId(
                getStringValue(CONFIG_DATA.fileName, AUTHOR_NAME.key),
                getStringValue(CONFIG_DATA.fileName, AUTHOR_LOGIN.key),
                getStringValue(CONFIG_DATA.fileName, AUTHOR_EMAIL.key)
        );

        projectId = saveProjectAndRetrieveId(
                getStringValue(CONFIG_DATA.fileName, PROJECT_NAME.key)
        );

        logger.info("Copy tests with an indication of the current project and the author.");
        for (Test test : testsByTwoRepeatingDigit){
            test.setAuthor_id(authorId);
            test.setProject_id(projectId);
            repeatingDigitTestIds.add(test.getId());
            long copiedTestId = saveTestAndRetrieveId(test);
            copiedTestIds.add(copiedTestId);
        }
        logger.info("Repeating digit test ids: %s", repeatingDigitTestIds);
        logger.info("New Copied Test ids: %s", copiedTestIds);
    }

    @org.testng.annotations.Test
    public void testOftestDataProcessing(){
        logger.info("Simulate the launch of each tests. Update information about them in the database.");
        List<Integer> statusIds = getAllStatusIds();

        for (long testId : copiedTestIds){
            Test test = getTestById(testId);
            int currentStatusId = test.getStatus_id();
            updateStatusId(testId, selectRandomStatusId(currentStatusId, statusIds));
            test = getTestById(testId);
            Assert.assertNotEquals(currentStatusId, test.getStatus_id(), "New status id should not be equal to current status id");
        }
    }

    @AfterMethod
    protected void deleteCreatedRecords() {
        logger.info("Delete the records created in Preconditions from the database.");
        for (long testId : copiedTestIds){
            deleteTestRecord(testId);
        }
        deleteAuthor(authorId);
        deleteProject(projectId);
    }
}
