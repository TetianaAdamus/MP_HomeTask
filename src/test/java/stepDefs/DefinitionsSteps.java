package stepDefs;

import static constants.Constants.BOOK_PRODUCT_URL;
import static org.assertj.core.api.Assertions.assertThat;

import desktop.pageFactory.PageFactoryManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Map;

public class DefinitionsSteps {

    WebDriver driver;
    PageFactoryManager pageFactoryManager;

    @Before
    public void driverSetUp() {
        String browser = System.getProperty("browser");
        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }


    @Given("I open Book Depository")
    public void openHomePage() {
        pageFactoryManager.getHomePage().openHomePage();
    }

    @Given("I open Thinking in Java product")
    public void openProduct() {
        driver.get(BOOK_PRODUCT_URL);
    }

    @Given("I manually accept cookie")
    public void acceptCookie() {
        pageFactoryManager.getBasePage().acceptCookie();
    }

    @When("I apply the following search filters")
    public void chooseSearchFilters(Map<String, String> filter) {
        pageFactoryManager.getSearchPage().selectFilter(filter);
    }

    @When("I search for {string}")
    public void search(String keyword) {
        pageFactoryManager.getHomePage().search(keyword);
    }

    @Then("Search results contain only the following products")
    public void searchResultsContainOnlyProducts(List<String> bookList) {
        assertThat(bookList).isEqualTo(pageFactoryManager.getSearchPage().searchResultBooksListText());
    }


   @Then("I am on the {string} product page")
    public void verifyProductPage(String productName) {
        assertThat(pageFactoryManager.getProductPage().productName()).isEqualTo(productName);

    }

    @Then("I open category {string}")
    public void iOpenCategory(String categoryName) {
        pageFactoryManager.getHomePage().openCategory(categoryName);
    }

    @Then("I am on the {string}")
    public void verifyPage(String page) {
        assertThat(pageFactoryManager.getHomePage().checkUrl(page)).isTrue();
    }

    @Then("Search results contain the following products")
    public void searchResultsContainProducts(List<String> productList) {
        assertThat(pageFactoryManager.getSearchPage().isProductsOnSearchPage(productList)).isTrue();
    }

    @After(order = 100)
    public void clearCoolies() {
        driver.manage().deleteAllCookies();
    }

    @After(order = 99)
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
