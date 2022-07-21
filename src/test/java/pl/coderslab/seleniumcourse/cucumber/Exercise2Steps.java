package pl.coderslab.seleniumcourse.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

public class Exercise2Steps {

    private WebDriver driver;

    @Given("^Page at address (.*)$")
    public void browser_with_open_page_at_address(String url) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.get(url);
    }

    @When("User entered {string} and {string}")
    public void entered_and(String email, String password) {
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(password);
    }

    @Then("Login user")
    public void login() {
        WebElement searchButton = driver.findElement(By.id("submit-login"));
        searchButton.click();
    }

    @Then("User open clothes tab")
    public void user_open_clothes_tab() {
        WebElement clothesTabButton = driver.findElement(By.xpath("//a[contains(text(),'Clothes')]"));
        clothesTabButton.click();
    }

    @Then("User click on desired tshirt")
    public void user_click_on_desired_tshirt() {
        WebElement tshirtButton = driver.findElement(By.xpath("//a[contains(text(),'Hummingbird printed t-shirt')]"));
        tshirtButton.click();
    }

    @Then("User select size M and quantity of {string}")
    public void user_select_size_m_and_quantity_of(String quantity) {
        WebElement size_element = driver.findElement(By.xpath("//select[@id='group_1']/option[text()='M']"));
        size_element.click();


        WebElement quantityInput = driver.findElement(By.id("quantity_wanted"));
        for(int i=0; i < 3; i++) { //inserting 3 times makes sure value gets inserted. It doesnt in first two times...
            quantityInput.clear();
            quantityInput.sendKeys(quantity);
        }
    }

    @Then("User click add to cart")
    public void user_click_add_to_cart() {
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@class='btn btn-primary add-to-cart']"));
        addToCartButton.click();
    }

    @Then("User click proceed to checkout")
    public void user_click_proceed_to_checkout() {
        Duration dur = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait (driver, dur);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Proceed to checkout')]")));

        WebElement proceedButton = driver.findElement(By.xpath("//a[contains(text(),'Proceed to checkout')]"));
        proceedButton.click();
    }

    @Then("User click proceed to checkout again")
    public void user_click_proceed_to_checkout_again() {
        WebElement proceedButton = driver.findElement(By.xpath("//a[contains(text(),'Proceed to checkout')]"));
        proceedButton.click();
    }

    @Then("User confirms address and shipping method")
    public void user_confirms_address_and_shipping_method() {
        WebElement confirmAddressesButton = driver.findElement(By.name("confirm-addresses"));
        confirmAddressesButton.click();

        WebElement confirmDeliveryButton = driver.findElement(By.name("confirmDeliveryOption"));
        confirmDeliveryButton.click();
    }

    @Then("User clicks PayByCheck option and agrees with terms of service")
    public void user_clicks_pay_by_check_option_and_agrees_with_terms_of_service() {
        WebElement paymentCheckBox = driver.findElement(By.id("payment-option-1"));
        paymentCheckBox.click();

        WebElement termsOfServiceCheckBox = driver.findElement(By.id("conditions_to_approve[terms-and-conditions]"));
        termsOfServiceCheckBox.click();
    }

    @Then("User clicks confirmation with obligation")
    public void user_clicks_confirmation_with_obligation() {
        WebElement confirmOrderButton = driver.findElement(By.xpath("//button[contains(text(),'Order with an obligation to pay')]"));
        confirmOrderButton.click();
    }

    @Then("Take a screenshot")
    public void saveScreenshot() throws IOException {
        File tmpScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String currentDateTime = LocalDateTime.now().toString().replaceAll(":", "_");
        Files.copy(tmpScreenshot.toPath(), Paths.get("/Users/katarzynalachman/Desktop/", "ddg-search-"+currentDateTime+".png"));
    }
}