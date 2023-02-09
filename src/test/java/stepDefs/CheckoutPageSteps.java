package stepDefs;

import static org.assertj.core.api.Assertions.assertThat;

import desktop.fragments.PaymentForm;
import desktop.fragments.RegistrationForm;
import desktop.pages.CheckoutPage;
import dto.DeliveryAddress;
import dto.OrderSummary;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class CheckoutPageSteps {

    private final CheckoutPage checkoutPage = new CheckoutPage();
    private RegistrationForm registrationForm = checkoutPage.getRegistrationForm();
    private PaymentForm paymentForm = checkoutPage.getPaymentForm();

    @Then("I am redirected to the {string} page")
    public void isSearchPageOpened(String pageName) {
        assertThat(checkoutPage.checkUrl(pageName)).isTrue();
    }

    @When("I click 'Buy now' button")
    public void buyNowButtonClick() {
        checkoutPage.scrollToPageBottom();
        checkoutPage.buyNowButtonClick();
    }

    @Then("the following validation error messages are displayed on 'Delivery Address' form:")
    public void verifyRegistraionFormErrorMessages(
            List<Map<String, String>> registrationFormList) {
        registrationFormList.forEach((listElement) ->
                assertThat(
                        registrationForm.registrationFormErrorMessages()
                                .get(listElement.get("Form field name"))).contains(
                        listElement.get("validaton error message")));
    }

    @And("the following validation error messages are displayed on 'Payment' form:")
    public void verifyPaymentFormErrorMessage(String messages) {
        List<String> list = java.util.Arrays.asList(messages.split(", "));
        list.forEach((string) -> assertThat(paymentForm.getPaymentErrorMessage()).contains(string));
    }

    @DataTableType
    public OrderSummary orderSummaryInstance(Map<String, String> entry) {
        return new OrderSummary(
                entry.get("Sub-total"),
                entry.get("Delivery"),
                entry.get("VAT"),
                entry.get("Total"));
    }

    @And("Checkout order summary is as following:")
    public void verifyCheckoutOrderSummary(OrderSummary orderSummary) {
                assertThat(orderSummary.getSubTotal())
                .as("Sub-total value is not equal to " + orderSummary.getSubTotal())
                .isEqualTo(checkoutPage.getSubTotalValue());
        assertThat(orderSummary.getDelivery())
                .as("Delivery value is not equal to " + orderSummary.getDelivery())
                .isEqualTo(checkoutPage.getDeliveryValue());
        assertThat(orderSummary.getVat()).as("VAT value is not equal to " + orderSummary.getVat())
                .isEqualTo(checkoutPage.getVatValue());
        assertThat(orderSummary.getTotal()).as("Total value is not equal to " + orderSummary.getTotal())
                .isEqualTo(checkoutPage.getTotalValue());
    }

    @And("I checkout as a new customer with email {string}")
    public void checkoutWithEmail(String email) {
        registrationForm.inputEmail(email);
    }

    @DataTableType
    public DeliveryAddress deliveryAddressInstance (Map<String, String> entry) {
        return new DeliveryAddress(
                entry.get("Full name"),
                entry.get("Delivery country"),
                entry.get("Address line 1"),
                entry.get("Address line 2"),
                entry.get("Town/City"),
                entry.get("County/State"),
                entry.get("Postcode"));
        }

    @When("I fill delivery address information manually:")
    public void inputDeliveryAddress(DeliveryAddress deliveryAddress) {
        registrationForm.fullNameElement().sendKeys(deliveryAddress.getFullName());
        registrationForm.selectCountry(deliveryAddress.getDeliveryCountry());
        registrationForm.addressLineOneElement().sendKeys(deliveryAddress.getAddressOne());
        registrationForm.addressLineTwoElement().sendKeys(deliveryAddress.getAddressTwo());
        registrationForm.cityElement().sendKeys(deliveryAddress.getCity());
        registrationForm.countryElement().sendKeys(deliveryAddress.getCountry());
        registrationForm.postcodeElement().sendKeys(deliveryAddress.getPostcode());
        registrationForm.clickOutside();
    }

    @Then("there is no validation error messages displayed on 'Delivery Address' form")
    public void verifyDeliveryFormErrorMessagesAreNotDisplayed() {
        registrationForm.errorMessagesElements().forEach(message -> assertThat(message.isDisplayed()).isFalse());
    }

    @And("I enter my card details")
    public void inputCardDetails(Map<String, String> cardDetails) {
        paymentForm.inputCardNumber(cardDetails.get("cardNumber"));
        paymentForm.inputCardDate(cardDetails.get("Expiry Month"), cardDetails.get("Expiry Year"));
        paymentForm.inputCvv(cardDetails.get("Cvv"));
    }
}

