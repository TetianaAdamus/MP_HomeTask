package desktop.pages;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;
import static utils.WebDriverWaiter.waitForPageLoadComplete;

import abstractClasses.page.AbstractPage;
import desktop.fragments.AddToBasketPopUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class SearchPage extends AbstractPage {

    @FindBy(css = "[class=' js'] div.page-slide")
    private WebElement rootElement;

    public List<WebElement> getSearchResultBooksList() {
        return rootElement.findElements(By.cssSelector(".book-item"));
    }

    public List<String> searchResultBooksListText() {
        List<String> searchResultTextList = new ArrayList<>();
        List<WebElement> searchResultList = getSearchResultBooksList();
        searchResultList.forEach(webElement -> searchResultTextList.add(webElement.findElement(By.cssSelector("h3"
                + ".title")).getText().trim()));
        return searchResultTextList;
    }

    public boolean isProductsOnSearchPage(List<String> products) {
        return new HashSet<>(searchResultBooksListText()).containsAll(products);
    }

    private Map<String, WebElement> facetMap() {
        Map<String, WebElement> facetMap = new HashMap<>();
        facetMap.put("Price range", rootElement.findElement(By.cssSelector("div.form-group select[name='price']")));
        facetMap.put("Availability",
                rootElement.findElement(By.cssSelector("div.form-group select[name='availability']")));
        facetMap.put("Language", rootElement.findElement(By.cssSelector("div.form-group select[name='searchLang']")));
        facetMap.put("Format", rootElement.findElement(By.cssSelector("div.form-group select[name='format']")));
        return facetMap;
    }

    public void selectFilter(Map<String, String> filter) {
        for (Map.Entry<String, String> entry : filter.entrySet()) {
            Select selectFilter = new Select(facetMap().get(entry.getKey()));
            selectFilter.selectByVisibleText(entry.getValue());
        }
        rootElement.findElement(By.cssSelector("[class=' js'] div.page-slide .filter-menu button")).click();
        waitForPageLoadComplete();
    }

    public void getAddProductToBasketButton(String buttonName, String productName) {
        getSearchResultBooksList().stream()
                .filter(product -> product.findElement(By.cssSelector("h3.title")).getText().trim().equals(productName))
                .collect(toList()).get(INTEGER_ZERO)
                .findElement(By.cssSelector(format("a[class*='%s']", buttonName.toLowerCase().replace(" ", "-"))))
                .click();
        waitForPageLoadComplete();
    }

    public AddToBasketPopUp getAddToBasketPopUp() {
        return new AddToBasketPopUp();
    }

}
