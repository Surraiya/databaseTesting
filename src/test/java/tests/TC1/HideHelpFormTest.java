package tests.TC1;

import PageObjects.pages.GamePage;
import PageObjects.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HideHelpFormTest extends BaseTest {

    @Test
    public void testHideHelpForm(){

        logger.info("Home Page is Open");
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.state().isDisplayed(),"Home Page is not open.");
        homePage.gotoNextPage();

        logger.info("Next Page which is Game Page, is Open");
        GamePage gamePage = new GamePage();

        logger.info("Help Form is Displayed");
        Assert.assertTrue(gamePage.getHelpForm().state().isDisplayed(),"Help Form is not Displayed");

        logger.info("Help form is hidden");
        gamePage.getHelpForm().hideHelpForm();
        gamePage.getHelpForm().state().waitForNotDisplayed();
        Assert.assertFalse(gamePage.getHelpForm().state().isDisplayed(),"Help Form is still Displayed");
    }
}
