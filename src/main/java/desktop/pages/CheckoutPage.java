package desktop.pages;

import abstractClasses.page.AbstractPage;
import desktop.fragments.PaymentForm;
import desktop.fragments.RegistrationForm;
import io.cucumber.java.it.Ma;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class CheckoutPage extends AbstractPage {

    @FindBy(css = "div[id='root']")
    private WebElement rootElement;

    public void buyNowButtonClick(){
        rootElement.findElement(By.cssSelector("div[id='root'] button[id='buyNowButton']")).click();
    }

    public RegistrationForm getRegistrationForm(){
        return new RegistrationForm();
    }

    public PaymentForm getPaymentForm(){
        return new PaymentForm();
    }

    public String getSubTotalValue(){
        return rootElement.findElement(By.cssSelector("div[aria-label^='Sub-total'] .text-right")).getText().trim();
    }

    public String getDeliveryValue(){
        return rootElement.findElement(By.cssSelector("div[aria-label^='Delivery'] .text-right")).getText().trim();
    }

    public String getVatValue(){
        return rootElement.findElement(By.cssSelector("div[aria-label^='VAT'] .text-right")).getText().trim();
    }

    public String getTotalValue(){
        return rootElement.findElement(By.cssSelector("div[aria-label^='Total'] .text-right")).getText().trim();
    }


}
