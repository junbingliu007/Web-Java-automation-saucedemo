package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DriverManager;
import utils.Log;

import java.util.List;

public class CartPage {

    private WebDriver driver;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(css = ".btn_secondary")
    private List<WebElement> removeButtons;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isCartPageDisplayed() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText().equals("Your Cart");
    }

    public int getCartItemsCount() {
        return cartItems.size();
    }

    public boolean isProductInCart(String productName) {
        for (WebElement item : cartItems) {
            WebElement itemName = item.findElement(By.className("inventory_item_name"));
            if (itemName.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public CheckoutPage clickCheckout() {
        checkoutButton.click();
        Log.info("Clicked checkout button");
        return new CheckoutPage(driver);
    }

    public void removeProduct(String productName) {
        for (int i = 0; i < cartItems.size(); i++) {
            WebElement itemName = cartItems.get(i).findElement(By.className("inventory_item_name"));
            if (itemName.getText().equals(productName)) {
                removeButtons.get(i).click();
                Log.info("Removed product from cart: " + productName);
                break;
            }
        }
    }
}