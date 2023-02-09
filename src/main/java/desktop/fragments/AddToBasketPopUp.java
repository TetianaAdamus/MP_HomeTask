package desktop.fragments;

import static java.lang.String.format;

import abstractClasses.fragments.AbstractFragment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddToBasketPopUp extends AbstractFragment {

    @FindBy(css = "div.modal-content")
    private WebElement rootElement;

    public void addToBasketConfirmation(String button){
        rootElement.findElement(By.cssSelector(format("a[data-default-localized-pattern='%s']",
                button.replace("/", " / ")))).click();
    }

}
