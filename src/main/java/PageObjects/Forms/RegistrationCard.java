package PageObjects.Forms;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;

import static Utilities.RandomGenerator.getRandomInteger;

public class RegistrationCard extends Form {

    private static final Logger logger = Logger.getInstance();
    private final ITextBox password = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Choose Password']"),"Choose Password");
    private final ITextBox emailName = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Your email']"),"Your email");
    private final ITextBox domain = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Domain']"),"Email Domain");
    private final IComboBox dropdown = getElementFactory().getComboBox(By.xpath("//div[@class='dropdown__field']"),"Other");
    private final List<IComboBox> mailSuffixes = getElementFactory().findElements(By.className("dropdown__list-item"), IComboBox.class, ElementsCount.MORE_THAN_ZERO, ElementState.EXISTS_IN_ANY_STATE);
    private final ICheckBox termCondition = getElementFactory().getCheckBox(By.xpath("//*[contains(@class,'checkbox__box')]"), "Accept term and condition");
    private final IButton next = getElementFactory().getButton(By.xpath("//a[@class='button--secondary']"),"Next");

    public RegistrationCard(){
        super(By.xpath("//*[@class='login-form__container-copy-1']"), "Card 1");
    }

    public void setPassword(String pass){
        logger.info("Passed password is :" + pass);
        password.getJsActions().scrollIntoView();
        password.clearAndType(String.valueOf(pass));
    }

    public void setEmailName(String email){
        logger.info("Passed email is: "+ email);
        emailName.getJsActions().scrollIntoView();
        emailName.clearAndType(email);
    }

    public void setDomain(String dom){
        domain.clearAndType(dom);
    }

    public void selectDropdown(){
        dropdown.click();
        mailSuffixes.get(getRandomInteger(mailSuffixes.size())).click();
    }

    public void acceptTermsOfUse(){
        termCondition.click();
    }

    public void clickNext(){
        next.click();
    }
}
