package com.qacart.todo.base;

import com.qacart.todo.api.utils.CookieUtils;
import com.qacart.todo.drivers.DriverFactory;
import io.restassured.http.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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
    public void tearDown() {
        // Quit the driver after each test method
        if (getDriver() != null) {
            getDriver().quit(); // Close the browser
            driver.remove(); // Remove the driver from ThreadLocal to prevent memory leaks
        }
    }

    public void injectCookiesToBrowser(List<io.restassured.http.Cookie> restAssuredCookies) {

        List<org.openqa.selenium.Cookie> seleniumCookies = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(restAssuredCookies);

        for (org.openqa.selenium.Cookie cookie : seleniumCookies) {
            // Add each Selenium cookie to the browser's cookie store
            getDriver().manage().addCookie(cookie);
        }
    }
}