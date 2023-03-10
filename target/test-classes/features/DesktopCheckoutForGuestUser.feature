@Runme
Feature: Desktop Checkout for Guest User
  As a customer
  I want to be able proceed to checkout
  So that I can specify my delivery and payment details and place the order

  Scenario: Proceed to checkout, final review and place order as guest user
    Given I am an anonymous customer with clear cookies
    When I open the "Initial home page"
    And I search for "Thinking in Java"
    And I am redirected to a "Search page"
    And Search results contain the following products
      | Thinking in Java       |
      | Thinking Java Part I   |
      | Core Java Professional |
    And I apply the following search filters
      | Price range  | 30 € +         |
      | Availability | In Stock (5)   |
      | Language     | English (17)   |
      | Format       | Paperback (22) |
    Then Search results contain only the following products
      | Thinking in Java                                                      |
      | Think Java                                                            |
      | Thinking Recursively - A 20th Anniversary Edition with Java (WSE)     |
      | Think Data Structures                                                 |
    When I click 'Add to basket' button for product with name "Thinking in Java"
    And I select 'Basket/Checkout' in basket pop-up
    Then I am redirected to a "Basket page"
    And Basket order summary is as following:
      | Delivery cost | Total   |
      | FREE          | 85,15 € |
    When I click 'Checkout' button on 'Basket' page
    Then I am redirected to the "Checkout" page
    When I click 'Buy now' button
    Then the following validation error messages are displayed on 'Delivery Address' form:
      | Form field name | validaton error message                               |
      | Email address   | Please enter your Email address                       |
      | Full name       | Please enter your Full name                           |
      | Address line 1  | Please enter your Address line 1                      |
      | Town/City       | Please enter your Town/City                           |
      | Postcode/ZIP    | Please enter your postcode/ZIP or write 'No Postcode' |
    And the following validation error messages are displayed on 'Payment' form:
      | Please enter your card number, Please enter your card's expiration date, Please enter your CVV |
    And Checkout order summary is as following:
      | Sub-total | Delivery | VAT     |  Total   |
      | 85,15 €   | FREE     | 0,00 €  | 85,15 €  |
    And I checkout as a new customer with email "test@user.com"
    When I fill delivery address information manually:
      | Full name | Delivery country | Address line 1   | Address line 2   | Town/City | County/State | Postcode |
      | John Adams     | Croatia          | Random address 1 | Random address 2 | Kyiv      | Random State | 12345    |
    Then there is no validation error messages displayed on 'Delivery Address' form
    And I enter my card details
      | cardNumber   | 4111111111111111 |
      | Expiry Month | 03               |
      | Expiry Year  | 2022             |
      | Cvv          | 123              |