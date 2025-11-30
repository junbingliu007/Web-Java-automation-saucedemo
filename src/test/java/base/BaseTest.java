package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverManager;
import utils.Log;
import utils.ScreenshotUtil;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.getBaseUrl());
        Log.info("Navigated to: " + ConfigReader.getBaseUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver,
                    result.getMethod().getMethodName());
            Log.error("Test failed: " + result.getThrowable().getMessage());
            Log.error("Screenshot saved at: " + screenshotPath);
        }

        DriverManager.quitDriver();
    }
}