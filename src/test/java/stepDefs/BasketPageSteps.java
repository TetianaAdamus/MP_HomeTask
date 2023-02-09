package stepDefs;

import static org.assertj.core.api.Assertions.assertThat;

import desktop.pages.BasketPage;
import dto.BasketSummary;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.util.Map;

public class BasketPageSteps {

    private final BasketPage basketPage = new BasketPage();

    @DataTableType
    public BasketSummary basketSummaryInstance(Map<String, String> entry) {
        return new BasketSummary(
                entry.get("Delivery cost"),
                entry.get("Total"));
    }

    @And("Basket order summary is as following:")
    public void verifyOrderSummery(BasketSummary basketSummary) {
        assertThat(basketSummary.getDeliveryCost()).as(
                        "Delivery cost is not equal to " + basketSummary.getDeliveryCost())
                .isEqualTo(basketPage.getDeliveryCostAmount());
        assertThat(basketSummary.getTotal()).as("Total amount is not equal to " + basketSummary.getTotal())
                .isEqualTo(basketPage.getTotalAmount());
    }

    @When("I click 'Checkout' button on 'Basket' page")
    public void checkoutButtonClick() {
        basketPage.checkoutButtonClick();
    }

}
