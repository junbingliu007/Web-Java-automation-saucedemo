package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DriverManager;
import utils.Log;

public class CheckoutPage {

    private WebDriver driver;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        DriverManager.getWait().until(ExpectedConditions.visibilityOf(firstNameInput));

        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);

        Log.info("Entered checkout information");
    }

    public void clickContinue() {
        continueButton.click();
        Log.info("Clicked continue button");
    }

    public void clickFinish() {
        finishButton.click();
        Log.info("Clicked finish button");
    }

    public boolean isCheckoutComplete() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOf(completeHeader));
        return completeHeader.getText().equals("Thank you for your order!");
    }

    public void completeCheckout(String firstName, String lastName, String postalCode) {
        enterCheckoutInformation(firstName, lastName, postalCode);
        clickContinue();
        clickFinish();
    }
}