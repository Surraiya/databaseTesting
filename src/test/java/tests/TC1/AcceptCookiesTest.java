package tests.TC1;

import PageObjects.pages.GamePage;
import PageObjects.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AcceptCookiesTest extends BaseTest {

    @Test
    public void testAcceptCookies(){
        logger.info("Home Page is Open");
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.state().isDisplayed(),"Home Page is not open.");
        homePage.gotoNextPage();

        logger.info("Next Page which is Game Page, is Open");
        GamePage gamePage = new GamePage();

        logger.info("Cookies Form is Displayed");
        gamePage.getCookiesForm().state().waitForDisplayed();
        Assert.assertTrue(gamePage.getCookiesForm().state().isDisplayed(), "Cookies Form is not displayed");

        logger.info("Accept cookies");
        gamePage.getCookiesForm().acceptCookies();

        logger.info("Cookies Form is not Displayed anymore");
        gamePage.getCookiesForm().state().waitForNotDisplayed();
        Assert.assertFalse(gamePage.getCookiesForm().state().isDisplayed(), "Cookies Form is still displayed");
    }
}
