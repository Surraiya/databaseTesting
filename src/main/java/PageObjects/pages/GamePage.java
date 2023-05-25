package PageObjects.pages;

import PageObjects.Forms.*;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class GamePage extends Form {

    private final ILabel startTime = getElementFactory().getLabel(By.xpath("//*[contains(@class,'timer') and contains(@class,'timer--white')]"),"Start Time");

    public GamePage(){
        super(By.xpath("//*[@class='game view']"),"Game Page");
    }

    public RegistrationCard getRegistrationCard(){
        return new RegistrationCard();
    }

    public ProfileSetupCard getProfileSetupCard(){
        return new ProfileSetupCard();
    }

    public PersonalDetailsCard getPersonalDetailsCard(){
        return new PersonalDetailsCard();
    }

    public HelpForm getHelpForm(){
        return new HelpForm();
    }

    public Cookies getCookiesForm(){
        return new Cookies();
    }

    public String getTimerStartTime(){
        return startTime.getElement().getText();
    }
}
