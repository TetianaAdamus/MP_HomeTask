package gridTest;

import static constants.Constants.BOOK_PRODUCT_URL;
import static constants.Constants.BOOK_TITLE;
import static constants.Constants.HOME_PAGE_TITLE;
import static constants.Constants.SEARCH_PAGE_TITLE;
import static org.assertj.core.api.Assertions.assertThat;

import desktop.pageFactory.PageFactoryManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@Execution(ExecutionMode.CONCURRENT)
public class BookDepositoryGridTest {

    private WebDriver webDriver;
    private PageFactoryManager pageFactoryManager;
    String hubURL =  "http://192.168.1.215:4444/wd/hub";

    @BeforeEach
    public void driverSetUp() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setBrowserName("chrome");
        webDriver = new RemoteWebDriver(new URL(hubURL),desiredCapabilities);
        pageFactoryManager = new PageFactoryManager(webDriver);

    }


    @Test
    public void searchTest(){
        webDriver.get(BOOK_PRODUCT_URL);
        assertThat(pageFactoryManager.getHomePage().checkUrl(HOME_PAGE_TITLE)).isTrue();
        pageFactoryManager.getHomePage().search(BOOK_TITLE);
        assertThat(pageFactoryManager.getHomePage().checkUrl(SEARCH_PAGE_TITLE)).isTrue();
    }

    @Test
    public void openProductPage(){
        webDriver.get(BOOK_PRODUCT_URL);
        pageFactoryManager.getBasePage().acceptCookie();
        assertThat(pageFactoryManager.getProductPage().productName()).isEqualTo(BOOK_TITLE);
    }

    @AfterEach
    public void quitDriver() {
        webDriver.quit();
    }

}
