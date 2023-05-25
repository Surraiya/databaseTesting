package tests.TC1;

import PageObjects.pages.GamePage;
import PageObjects.pages.HomePage;
import Utilities.JsonFileReader;
import Utilities.RegistrationCardDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utilities.JsonFileReader.getIntValue;

public class RegistrationAndProfileSetupTest extends BaseTest {

    @Test
    public void testRegistrationAndProfileSetupWithValidData() throws IllegalArgumentException {

        logger.info("1. Navigate to HomePage");
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.state().isDisplayed(), "Home Page is not open.");


        logger.info("2. Click the link (in text 'Please click HERE to GO to the next page') to navigate the next page.");
        homePage.gotoNextPage();
        GamePage gamePage = new GamePage();
        logger.info("The Card 1 is Open");
        Assert.assertTrue(gamePage.getRegistrationCard().state().isDisplayed(), "The '1' card should be open");


        logger.info("3. Input random valid password, email, accept the terms of use and click 'next' button");
        String email = RegistrationCardDataGenerator.getEmailName(
                getIntValue("registrationData","/minEmailChar"),
                getIntValue("registrationData","/maxEmailChar")
        );
        String password = RegistrationCardDataGenerator.getRandomPassword(
                getIntValue("registrationData","/minPassLength"),
                email,
                getIntValue("registrationData","/minCapitalLetter"),
                getIntValue("registrationData","/minNumeralChar"),
                getIntValue("registrationData","/minCyrillicChar")
        );

        gamePage.getRegistrationCard().setPassword(password);
        gamePage.getRegistrationCard().setEmailName(email);
        gamePage.getRegistrationCard().setDomain(RegistrationCardDataGenerator.getEmailDomain());
        gamePage.getRegistrationCard().selectDropdown();
        gamePage.getRegistrationCard().acceptTermsOfUse();
        gamePage.getRegistrationCard().clickNext();

        logger.info("The Card 2 is Open");
        gamePage.getProfileSetupCard().state().waitForDisplayed();
        Assert.assertTrue(gamePage.getProfileSetupCard().state().isDisplayed(), "The '2' card is not Open.");


        logger.info("4. Choose 2 random interests, upload image, click 'Next' button.");
        gamePage.getProfileSetupCard().chooseRandomInterests(getIntValue("registrationData","/numOfRandomInterest"));
        gamePage.getProfileSetupCard().uploadImage(JsonFileReader.getStringValue("registrationData","/filename"));
        gamePage.getProfileSetupCard().clickNext();

        logger.info("The Card 3 is Open");
        Assert.assertTrue(gamePage.getPersonalDetailsCard().state().isDisplayed(), "The '3' card is not Open");
    }
}
