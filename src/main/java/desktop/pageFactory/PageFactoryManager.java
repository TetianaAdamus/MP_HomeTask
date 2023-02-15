package desktop.pageFactory;

import desktop.HomePage;
import desktop.SearchPage;
import org.openqa.selenium.WebDriver;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public SearchPage getSearchPage(){
        return new SearchPage(driver);
    }

}
