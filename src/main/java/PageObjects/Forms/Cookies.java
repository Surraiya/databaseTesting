package PageObjects.Forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class Cookies extends Form {

    private final IButton yes = getElementFactory().getButton(By.xpath("//*[contains(@class,'button--solid') and contains(@class,'button--transparent')]"), "Accept Cookies");

    public Cookies(){
        super(By.xpath("//*[@class= 'cookies__message']"),"Cookies");
    }

    public void acceptCookies(){
        yes.click();
    }
}
