package utils;

import static constants.Constants.TIME_TO_WAIT;
import static driver.SingletonDriver.getDriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWaiter {

    public static void waitForPageLoadComplete() {
        new WebDriverWait(getDriver(), TIME_TO_WAIT).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals(
                "complete"));
    }

}
