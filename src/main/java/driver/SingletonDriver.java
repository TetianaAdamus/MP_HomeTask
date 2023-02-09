package driver;

import static constants.Constants.IMPLICITLY_WAIT_TIMEOUT;
import static driver.CapabilitiesHelper.getChromeOptions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SingletonDriver {
    private static WebDriver instance;

    public static WebDriver getDriver() {
        if (instance == null) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            instance = new ChromeDriver(getChromeOptions());
            instance.manage().window().maximize();
            instance.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
        }
        return instance;
    }

    public static void openPage(String url) {
        instance.get(url);
    }

    public static void quitDriver() {
        if (instance != null) {
            instance.quit();
        }
    }

}
