package desktop;

import static constants.Constants.BOOK_DEPOSITORY;
import static constants.Constants.IMPLICITLY_WAIT_TIMEOUT;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class HomePage extends BasePage {

    private @FindBy(css = ".text-input")
    WebElement searchInputField;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(){
        driver.get(BOOK_DEPOSITORY);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_TIMEOUT));
    }

    public void search(String keyword) {
        searchInputField.sendKeys(keyword, Keys.ENTER);
    }

}
