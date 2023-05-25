package PageObjects.pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HomePage extends Form {

    private final ILink clickHere = getElementFactory().getLink(By.xpath("//a[@class='start__link']"), "Please click Here to go to Next Page");

    public HomePage(){
        super(By.xpath("//*[@id='app']"),"Home Page");
    }

    public void gotoNextPage(){
        clickHere.clickAndWait();
    }
}
