Feature: Create new address

 Scenario Outline: new address creation
    Given Open page at address https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account
    When Entered "<email>" and "<password>"
    Then Login
    And User click at addresses tab
    And User click at new address form
    And User fill alias "<alias>"
    And User fill address "<address>"
    And User fill city "<city>"
    And User fill zip "<zip>"
    And User fill country "<country>"
    And User fill phone "<phone>"
    Then User click save button
    Then Check if inserted data is correct "<alias>" "<address>" "<city>" "<zip>" "<country>" "<phone>"

    Examples:
     |email                     |password    | alias      | address      | city      | zip      | country      | phone |
     |kesihed499@logodez.com    |test123     | alias_data3 | addressData | cityData | zipData | United Kingdom | 123   |


