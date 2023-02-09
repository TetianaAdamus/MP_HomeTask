package stepDefs;

import static driver.SingletonDriver.getDriver;
import static driver.SingletonDriver.quitDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.JavascriptExecutor;

public class RunnerHooks {

    @Before
    static void driverSetUp() {
        getDriver();

    }

    @After(order=100)
    static void clearCoolies() {
        getDriver().manage().deleteAllCookies();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("window.sessionStorage.clear()");
        javascriptExecutor.executeScript("window.localStorage.clear()");
    }

    @After(order=99)
    public void quitBrouser(){
        quitDriver();
    }

}
