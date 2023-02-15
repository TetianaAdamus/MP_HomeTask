package stepDefs;

import static org.assertj.core.api.Assertions.assertThat;

import desktop.pageFactory.PageFactoryManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Map;

public class DefinitionsSteps {

    WebDriver driver;
    PageFactoryManager pageFactoryManager;

    @Before
    public void driverSetUp() {
        String browser = System.getProperty ("browser");
        if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }


//        @Before
//    public void driverSetUp() {
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        driver = new ChromeDriver(getChromeOptions());
//        driver.manage().window().maximize();
//        pageFactoryManager = new PageFactoryManager(driver);
//    }

    @Given("I open Book Depository")
    public void openHomePage() {
        pageFactoryManager.getHomePage().openHomePage();
    }

    @Then("I am on the {string}")
    public void verifyPage(String page) {
        assertThat(pageFactoryManager.getHomePage().checkUrl(page)).isTrue();
    }

    @And("Search results contain the following products")
    public void searchResultsContainProducts(List<String> productList) {
        assertThat(pageFactoryManager.getSearchPage().isProductsOnSearchPage(productList)).isTrue();
    }

    @And("I apply the following search filters")
    public void chooseSearchFilters(Map<String, String> filter) {
        pageFactoryManager.getSearchPage().selectFilter(filter);
    }

    @Then("Search results contain only the following products")
    public void searchResultsContainOnlyProducts(List<String> bookList) {
        assertThat(bookList).isEqualTo(pageFactoryManager.getSearchPage().searchResultBooksListText());
    }


    @When("I search for {string}")
    public void search(String keyword) {
        pageFactoryManager.getHomePage().search(keyword);
    }


    @After(order = 100)
    public void clearCoolies() {
        driver.manage().deleteAllCookies();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("window.sessionStorage.clear()");
        javascriptExecutor.executeScript("window.localStorage.clear()");
    }

    @After(order = 99)
    public void quitDriver() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("--ignore-certificate-errors");
        return chromeOptions;
    }

}
