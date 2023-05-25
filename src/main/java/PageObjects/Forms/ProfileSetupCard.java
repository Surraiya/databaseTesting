package PageObjects.Forms;

import Utilities.FileUploader;
import Utilities.JsonFileReader;
import Utilities.RandomGenerator;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.nio.file.Path;
import java.util.List;


public class ProfileSetupCard extends Form {

    private final IButton upload = getElementFactory().getButton(By.xpath("//a[@class='avatar-and-interests__upload-button']"),"Upload Image");
    private final ICheckBox unSelectAll = getElementFactory().getCheckBox(By.xpath("//label[@for='interest_unselectall']"), "Unselect All");
    private final List<ICheckBox> interestList = getElementFactory().findElements(By.xpath("//span[contains(@class,'checkbox__box')]"),"List of Interests", ICheckBox.class, ElementsCount.MORE_THAN_ZERO, ElementState.EXISTS_IN_ANY_STATE);
    private final IButton next = getElementFactory().getButton(By.xpath("//button[contains(@class,'button--stroked')]"),"Next");

    public ProfileSetupCard(){
        super(By.xpath("//div[@class='avatar-and-interests__form']"),"Card 2");
    }

    public void chooseRandomInterests(int numOfRandomInterests){
        unselectAllInterests();
        List<Integer> randomIntList = RandomGenerator.getUniqueRandomIntegers(numOfRandomInterests, 0, interestList.size() - 1);
        for (int randomInt : randomIntList) {
            ICheckBox interest = interestList.get(randomInt);
            interest.check();
        }
    }

    public void unselectAllInterests(){
        unSelectAll.getJsActions().scrollIntoView();
        unSelectAll.click();
    }

    public void uploadImage(String filename) {
        upload.clickAndWait();
        FileUploader.uploadFile(Path.of(filename));
    }

    public void clickNext(){
        next.click();
    }
}
