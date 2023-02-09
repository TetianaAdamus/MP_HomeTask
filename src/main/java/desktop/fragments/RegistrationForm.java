package desktop.fragments;

import static constants.Constants.TIME_TO_WAIT;
import static driver.SingletonDriver.getDriver;

import abstractClasses.fragments.AbstractFragment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationForm extends AbstractFragment {

    @FindBy(css = "div[class$='delivery-address']")
    private WebElement rootElement;

    public Map<String, String> registrationFormErrorMessages() {
        Map<String, String> map = new HashMap<>();
        map.put("Email address", getEmailErrorMessage().getText().trim());
        map.put("Full name", getNameErrorMessage().getText().trim());
        map.put("Address line 1", getAdressErrorMessage().getText().trim());
        map.put("Town/City", getCityErrorMessage().getText().trim());
        map.put("Postcode/ZIP", getPostcodeErrorMessage().getText().trim());
        return map;
    }

    public List<WebElement> errorMessagesElements() {
        List<WebElement> list = new ArrayList<>();
        list.add(getEmailErrorMessage());
        list.add(getNameErrorMessage());
        list.add(getAdressErrorMessage());
        list.add(getCityErrorMessage());
        list.add(getPostcodeErrorMessage());
        return list;
    }

    public void clickOutside() {
        Actions action = new Actions(getDriver());
        action.pause(TIME_TO_WAIT).moveByOffset(10, 10).click().build().perform();
    }

    public void inputEmail(String email) {
        Actions action = new Actions(getDriver());
        action.moveToElement(emailAddressInputElement()).click(emailAddressInputElement()).sendKeys(email).build()
                .perform();
    }

    public void selectCountry(String country) {
        Select select = new Select(deliveryCountryElement());
        select.selectByVisibleText(country);
    }

    public WebElement fullNameElement() {
        return rootElement.findElement(By.cssSelector("input[id='delivery-fullName']"));
    }

    public WebElement deliveryCountryElement() {
        return rootElement.findElement(By.cssSelector("[name='deliveryCountry']"));
    }

    public WebElement addressLineOneElement() {
        return rootElement.findElement(By.cssSelector("input[id='delivery-addressLine1']"));
    }

    public WebElement addressLineTwoElement() {
        return rootElement.findElement(By.cssSelector("input[id='delivery-addressLine2']"));
    }

    public WebElement cityElement() {
        return rootElement.findElement(By.cssSelector("input[id='delivery-city']"));
    }

    public WebElement countryElement() {
        return rootElement.findElement(By.cssSelector("input[id='delivery-county']"));
    }

    public WebElement postcodeElement() {
        return rootElement.findElement(By.cssSelector("input[id='delivery-postCode']"));
    }

    private WebElement getEmailErrorMessage() {
        return rootElement.findElement(By.cssSelector("div[id='email-errors']"));
    }

    private WebElement getNameErrorMessage() {
        return rootElement.findElement(By.cssSelector("div[id='delivery-fullName-errors']"));
    }

    private WebElement getAdressErrorMessage() {
        return rootElement.findElement(By.cssSelector("div[id='delivery-addressLine1-errors']"));
    }

    private WebElement getCityErrorMessage() {
        return rootElement.findElement(By.cssSelector("div[id='delivery-city-errors']"));
    }

    private WebElement getPostcodeErrorMessage() {
        return rootElement.findElement(By.cssSelector("div[id='delivery-postCode-errors']"));
    }

    private WebElement emailAddressInputElement() {
        return rootElement.findElement(By.cssSelector("[name=emailAddress]"));
    }

    private WebElement phoneCodeElement() {
        return rootElement.findElement(By.cssSelector("[id='phonePrefix'] .hidden-select"));
    }

    private WebElement phoneNumberElement() {
        return rootElement.findElement(By.cssSelector("[name='delivery-telephone']"));
    }

}
