package desktop.fragments;

import static driver.SingletonDriver.getDriver;

import abstractClasses.fragments.AbstractFragment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentForm extends AbstractFragment {

    @FindBy(css = "div[class$='card-form variant']")
    private WebElement rootElement;

    public String getPaymentErrorMessage(){
        return rootElement.findElement(By.cssSelector("div[class='buynow-error-msg']")).getText().trim();
    }

    public void inputCardNumber(String number){
        getDriver().switchTo().frame("braintree-hosted-field-number");
        cardNumberField().sendKeys(number);
        getDriver().switchTo().defaultContent();
    }

    public void inputCardDate(String month, String year){
        getDriver().switchTo().frame("braintree-hosted-field-expirationDate");
        cardDateField().sendKeys(month, year);
        getDriver().switchTo().defaultContent();
    }

    public void inputCvv(String cvv){
        getDriver().switchTo().frame("braintree-hosted-field-cvv");
        cvvField().sendKeys(cvv);
        getDriver().switchTo().defaultContent();
    }

    private WebElement cardNumberField(){
        return getDriver().findElement(By.cssSelector("input[id='credit-card-number']"));
    }

    private WebElement cardDateField(){
        return getDriver().findElement(By.cssSelector("input[id='expiration']"));
    }

    private WebElement cvvField(){
        return getDriver().findElement(By.cssSelector("input[id='cvv']"));
    }

}
