package junitTest;

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
import org.openqa.selenium.chrome.ChromeDriver;

@Execution(ExecutionMode.CONCURRENT)
public class JunitTest {
    WebDriver driver;
    PageFactoryManager pageFactoryManager;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @Test
    public void searchTest(){
        driver.get(BOOK_PRODUCT_URL);
        assertThat(pageFactoryManager.getHomePage().checkUrl(HOME_PAGE_TITLE)).isTrue();
        pageFactoryManager.getHomePage().search(BOOK_TITLE);
        assertThat(pageFactoryManager.getHomePage().checkUrl(SEARCH_PAGE_TITLE)).isTrue();
    }

    @Test
    public void openProductPage(){
        driver.get(BOOK_PRODUCT_URL);
        pageFactoryManager.getBasePage().acceptCookie();
        assertThat(pageFactoryManager.getProductPage().productName()).isEqualTo(BOOK_TITLE);
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

}
