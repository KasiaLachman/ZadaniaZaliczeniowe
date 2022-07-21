Feature: Create order

 Scenario Outline: new order creation
    Given Page at address https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account
    When  User entered "<email>" and "<password>"
    Then Login user
    Then User open clothes tab
    Then User click on desired tshirt
    Then User select size M and quantity of "<quantity>"
    Then User click add to cart
    Then User click proceed to checkout
    Then User click proceed to checkout again
    Then User confirms address and shipping method
    Then User clicks PayByCheck option and agrees with terms of service
    Then User clicks confirmation with obligation
    Then Take a screenshot

    Examples:
     |email                     |password    | quantity |
     |kesihed499@logodez.com    |test123     | 5        |


