package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;
import utils.Log;

public class SauceDemoTest extends BaseTest {

    @Test(description = "Successful login test")
    public void testSuccessfulLogin() {
        Log.info("Starting successful login test");

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(
                ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password")
        );

        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "Products page should be displayed after successful login");
    }

    @Test(description = "Failed login test")
    public void testFailedLogin() {
        Log.info("Starting failed login test");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.getProperty("invalid.username"));
        loginPage.enterPassword(ConfigReader.getProperty("invalid.password"));
        loginPage.clickLogin();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Username and password do not match"),
                "Error message should be displayed for invalid credentials");
    }

    @Test(description = "Complete purchase flow test")
    public void testCompletePurchaseFlow() {
        Log.info("Starting complete purchase flow test");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(
                ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password")
        );

        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "Should be on products page after login");

        // Add products to cart
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");

        Assert.assertEquals(productsPage.getCartItemCount(), 2,
                "Cart should have 2 items");

        // Go to cart and checkout
        CartPage cartPage = productsPage.clickCartIcon();
        Assert.assertTrue(cartPage.isCartPageDisplayed(),
                "Should be on cart page");
        Assert.assertEquals(cartPage.getCartItemsCount(), 2,
                "Cart should contain 2 items");

        // Checkout
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        checkoutPage.completeCheckout("John", "Doe", "12345");

        Assert.assertTrue(checkoutPage.isCheckoutComplete(),
                "Checkout should be completed successfully");
    }

    @Test(description = "Add and remove items from cart")
    public void testAddRemoveItemsFromCart() {
        Log.info("Starting add/remove items test");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(
                ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password")
        );

        // Add items
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");
        Assert.assertEquals(productsPage.getCartItemCount(), 2,
                "Should have 2 items in cart");

        // Remove one item from cart
        CartPage cartPage = productsPage.clickCartIcon();
        cartPage.removeProduct("Sauce Labs Backpack");

        Assert.assertEquals(cartPage.getCartItemsCount(), 1,
                "Should have 1 item left in cart");
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Bike Light"),
                "Bike Light should still be in cart");
    }
}