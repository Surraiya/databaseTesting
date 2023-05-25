package PageObjects.Forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {

    private final IButton hide = getElementFactory().getButton(By.xpath("//*[@class='discrete']"), "Hide Help Form");

    public HelpForm(){
        super(By.xpath("//*[@class='help-form']"),"Help Form");
    }

    public void hideHelpForm(){
        hide.click();
    }
}
