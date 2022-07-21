package pl.coderslab.seleniumcourse.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;


public class Exercise1Steps {

    private WebDriver driver;

    @Given("^Open page at address (.*)$")
    public void browser_with_open_page_at_address(String url) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.get(url);
    }

    @When("Entered {string} and {string}")
    public void entered_and(String email, String password) {
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(password);
    }

    @Then("Login")
    public void login() {
        WebElement searchButton = driver.findElement(By.id("submit-login"));
        searchButton.click();
    }

    @And("User click at addresses tab")
    public void user_click_at_addresses_tab() {
        String title = "Addresses";
        WebElement addresses_href = driver.findElement(By.cssSelector("[title^='"+title+"']"));
        addresses_href.click();
    }

    @And("User click at new address form")
    public void user_click_at_new_address_form() {
        String title = "add-address";
        WebElement addresses_href = driver.findElement(By.cssSelector("[data-link-action^='"+title+"']"));
        addresses_href.click();
    }

    @And("User fill alias {string}")
    public void user_fill_alias(String string) {
        WebElement aliasInput = driver.findElement(By.name("alias"));
        aliasInput.sendKeys(string);
    }
    @And("User fill address {string}")
    public void user_fill_address(String string) {
        WebElement addressInput = driver.findElement(By.name("address1"));
        addressInput.sendKeys(string);
    }
    @And("User fill city {string}")
    public void user_fill_city(String string) {
        WebElement cityInput = driver.findElement(By.name("city"));
        cityInput.sendKeys(string);
    }
    @And("User fill zip {string}")
    public void user_fill_zip(String string) {
        WebElement zipInput = driver.findElement(By.name("postcode"));
        zipInput.sendKeys(string);
    }
    @And("User fill country {string}")
    public void user_fill_country(String string) {
        Select drpCountry = new Select(driver.findElement(By.name("id_country")));
        drpCountry.selectByVisibleText(string);
    }
    @And("User fill phone {string}")
    public void user_fill_phone(String string) {
        WebElement phoneInput = driver.findElement(By.name("phone"));
        phoneInput.sendKeys(string);
    }

    @Then("User click save button")
    public void user_click_save_button() {
        try {
            WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
            saveButton.click();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        { //Executing same thing again because of StaleElementReference
            WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
            saveButton.click();
        }
    }

    @Then("Check if inserted data is correct {string} {string} {string} {string} {string} {string}")
    public void check_if_inserted_data_is_correct(String alias, String address, String city, String zip, String country, String phone) {
        String value = "edit-address";
        List<WebElement> all_hrefs = driver.findElements(By.xpath("//a[@data-link-action='edit-address']"));
        int count = all_hrefs.size();
        all_hrefs.get(count-1).click();

        WebElement aliasInput = driver.findElement(By.name("alias"));
        WebElement addressInput = driver.findElement(By.name("address1"));
        WebElement cityInput = driver.findElement(By.name("city"));
        WebElement zipInput = driver.findElement(By.name("postcode"));

        Select drpCountry = new Select(driver.findElement(By.name("id_country")));
        WebElement selectedCountry = drpCountry.getFirstSelectedOption();

        WebElement phoneInput = driver.findElement(By.name("phone"));



        assertTrue(aliasInput.getAttribute("value").equals(alias));
        assertTrue(addressInput.getAttribute("value").equals(address));
        assertTrue(cityInput.getAttribute("value").equals(city));
        assertTrue(zipInput.getAttribute("value").equals(zip));

        assertTrue(selectedCountry.getText().equals(country));
        assertTrue(phoneInput.getAttribute("value").equals(phone));

    }
}