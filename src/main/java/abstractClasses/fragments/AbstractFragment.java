package abstractClasses.fragments;

import static driver.SingletonDriver.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AbstractFragment {

    WebElement rootElement;

    public AbstractFragment(){
        PageFactory.initElements(getDriver(), this);
    }

}
