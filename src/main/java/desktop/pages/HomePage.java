package desktop.pages;

import static utils.WebDriverWaiter.waitForPageLoadComplete;

import abstractClasses.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    private @FindBy(css = "[class=' page-type-home js'] div.page-slide")
    WebElement rootElement;

    public void search(String keyword) {
        rootElement.findElement(By.cssSelector(".text-input")).sendKeys(keyword, Keys.ENTER);
        waitForPageLoadComplete();
    }

}
