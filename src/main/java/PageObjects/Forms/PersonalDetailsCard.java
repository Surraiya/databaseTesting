package PageObjects.Forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PersonalDetailsCard extends Form {

    public PersonalDetailsCard(){
        super(By.xpath("//div[@class='page-indicator' and contains(text(), '3 / 4')]"),"Personal Details Card");
    }
}
