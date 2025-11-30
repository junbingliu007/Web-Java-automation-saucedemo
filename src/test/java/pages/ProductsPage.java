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

public class ProductsPage {

    private WebDriver driver;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> productItems;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartIcon;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isProductsPageDisplayed() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText().equals("Products");
    }

    public void addProductToCart(String productName) {
        for (WebElement product : productItems) {
            WebElement productTitle = product.findElement(By.className("inventory_item_name"));
            if (productTitle.getText().equals(productName)) {
                WebElement addToCartButton = product.findElement(By.cssSelector(".btn_inventory"));
                addToCartButton.click();
                Log.info("Added product to cart: " + productName);
                break;
            }
        }
    }

    public CartPage clickCartIcon() {
        cartIcon.click();
        Log.info("Clicked cart icon");
        return new CartPage(driver);
    }

    public int getCartItemCount() {
        String countText = cartIcon.getText();
        return countText.isEmpty() ? 0 : Integer.parseInt(countText);
    }
}