package tests.TC1;

import PageObjects.pages.GamePage;
import PageObjects.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utilities.FileReader.FileKeyName.TimerKeyName.TIMER_START_TIME;
import static Utilities.FileReader.FileName.TIMER;
import static Utilities.FileReader.JsonFileReader.getStringValue;

public class StartTimeOfTimerTest extends BaseTest {

    @Test
    public void testStartTimeOfTimer(){

        logger.info("Home Page is Open");
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.state().isDisplayed(), "Home Page is not open.");
        homePage.gotoNextPage();

        logger.info("Next Page which is Game Page, is Open");
        GamePage gamePage = new GamePage();

        logger.info("Time started from 00:00:00");
        Assert.assertEquals(getStringValue(TIMER.fileName, TIMER_START_TIME.key), gamePage.getTimerStartTime(), "Timer did not start from 00:00:00");
    }
}
