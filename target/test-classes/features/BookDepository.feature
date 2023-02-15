@Runme
Feature: Desktop Checkout for Guest User

  Scenario: Proceed to checkout, final review and place order as guest user
    Given I open Book Depository
    And I am on the "Home page"
    When I search for "Thinking in Java"
    Then I am on the "Search page"
    And Search results contain the following products
      | Thinking in Java       |
      | Thinking Java Part I   |
      | Core Java Professional |
    When I apply the following search filters
      | Price range  | 30 € +         |
      | Availability | In Stock (5)   |
      | Language     | English (17)   |
      | Format       | Paperback (22) |
    Then Search results contain only the following products
      | Thinking in Java                                                      |
      | Think Java                                                            |
      | Thinking Recursively - A 20th Anniversary Edition with Java (WSE)     |
      | Think Data Structures                                                 |