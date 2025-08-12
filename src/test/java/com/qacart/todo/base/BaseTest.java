package com.qacart.todo.base;

import com.qacart.todo.api.utils.CookieUtils;
import com.qacart.todo.drivers.DriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * BaseTest class serves as a base class for all test classes in the application.
 * It initializes the WebDriver instance before each test method and quits it after each test method.
 */
public class BaseTest {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public WebDriver getDriver() {
        return this.driver.get();
    }

    @BeforeMethod
    public void setUp() {
        // Create an instance of DriverFactory
        // Initialize the WebDriver instance using the non-static method
        WebDriver driver = new DriverFactory().initDriver();
        setDriver(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {

        // Capture the name of the test case from the ITestResult object
        String testCaseName = result.getMethod().getMethodName();
        // Create a destination file path for the screenshot
        File destFile = new File("target" + File.separator + "screenshots" + File.separator + testCaseName + ".png");
        // Use the takeScreenshot method to capture a screenshot after each test
        takeScreenshot(destFile);

        // Quit the driver after each test method
        if (getDriver() != null) {
            getDriver().quit(); // Close the browser
            driver.remove(); // Remove the driver from ThreadLocal to prevent memory leaks
        }
    }

    // Method to inject cookies into the browser
    @Step
    public void injectCookiesToBrowser(List<io.restassured.http.Cookie> restAssuredCookies) {

        List<org.openqa.selenium.Cookie> seleniumCookies = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(restAssuredCookies);

        for (org.openqa.selenium.Cookie cookie : seleniumCookies) {
            // Add each Selenium cookie to the browser's cookie store
            getDriver().manage().addCookie(cookie);
        }
    }

    // Method to take a screenshot after each test
    public void takeScreenshot(File destinationFile) throws IOException {

        // Capture a screenshot after each test by casting WebDriver to TakesScreenshot.
        // The screenshot is returned as a File and saved to the default temp location.
        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, destinationFile);

        // Attach the screenshot to Allure reports
        InputStream inputStream = new FileInputStream(destinationFile);
        Allure.addAttachment("screenshot", inputStream);
    }
}