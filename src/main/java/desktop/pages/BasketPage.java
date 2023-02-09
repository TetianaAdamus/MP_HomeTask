package desktop.pages;

import static driver.SingletonDriver.getDriver;
import static utils.WebDriverWaiter.waitForPageLoadComplete;

import abstractClasses.page.AbstractPage;
import driver.SingletonDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.WebDriverWaiter;

public class BasketPage extends AbstractPage {

    @FindBy(css = "[class=' js'] div.page-slide")
    private WebElement rootElement;

    public String getDeliveryCostAmount(){
        return rootElement.findElement(By.cssSelector(".delivery-text dd")).getText();
    }

    public String getTotalAmount(){
        return rootElement.findElement(By.cssSelector(".total dd")).getText();
    }

    public void checkoutButtonClick(){
        WebElement checkoutButton = rootElement.findElement(By.cssSelector("div.basket-checkout-btn-wrap "
                + "a[class*='checkout']"));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(checkoutButton).click(checkoutButton).build().perform();
        waitForPageLoadComplete();
    }

}


