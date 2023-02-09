package abstractClasses.page;

import static constants.Constants.BOOK_DEPOSITORY;
import static driver.SingletonDriver.getDriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class AbstractPage {

    private WebElement rootElement;

    public AbstractPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void clearCookies() {
        getDriver().manage().deleteAllCookies();
    }

    public Map<String, String> pagesNamesMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Initial home page", BOOK_DEPOSITORY);
        map.put("Basket page", BOOK_DEPOSITORY + "basket");
        map.put("Search page", BOOK_DEPOSITORY + "search");
        map.put("Checkout", BOOK_DEPOSITORY + "payment");
        return map;
    }

    public String pageUrl(String page) {
        return pagesNamesMap().get(page);
    }

    public boolean checkUrl(String pageName) {
        return getDriver().getCurrentUrl().contains(pageUrl(pageName));
    }

    public void scrollToPageBottom(){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

}
