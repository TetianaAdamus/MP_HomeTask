package stepDefs;

import desktop.pages.SearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

public class SearchPageSteps {
    private final SearchPage searchPage = new SearchPage();

    @Then("I am redirected to a {string}")
    public void isSearchPageOpened(String pageName) {
        assertThat(searchPage.checkUrl(pageName)).isTrue();
    }

    @And("Search results contain the following products")
    public void searchResultsContainProducts(List<String> productList) {
        assertThat(searchPage.isProductsOnSearchPage(productList)).isTrue();
    }

    @And("I apply the following search filters")
    public void chooseSearchFilters(Map<String, String> filter) {
        searchPage.selectFilter(filter);
    }

    @Then("Search results contain only the following products")
    public void searchResultsContainOnlyProducts(List<String> bookList) {
        assertThat(bookList).isEqualTo(searchPage.searchResultBooksListText());
    }

    @When("I click {string} button for product with name {string}")
    public void addProductToBasket(String buttonName, String bookTitle) {
        searchPage.getAddProductToBasketButton(buttonName, bookTitle);
    }

    @And("I select {string} in basket pop-up")
    public void addToBasketConfirmation(String buttonName) {
        searchPage.getAddToBasketPopUp().addToBasketConfirmation(buttonName);
    }
}
