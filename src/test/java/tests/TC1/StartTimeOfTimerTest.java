package tests.TC1;

import PageObjects.pages.GamePage;
import PageObjects.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utilities.JsonFileReader.getStringValue;

public class StartTimeOfTimerTest extends BaseTest {

    @Test
    public void testStartTimeOfTimer(){

        logger.info("Home Page is Open");
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.state().isDisplayed(),"Home Page is not open.");
        homePage.gotoNextPage();

        logger.info("Next Page which is Game Page, is Open");
        GamePage gamePage = new GamePage();

        logger.info("Time started from 00:00:00");
        Assert.assertEquals(getStringValue("timerTestData","/timerStartTime"), gamePage.getTimerStartTime(), "Timer did not start from 00:00:00");
    }
}
