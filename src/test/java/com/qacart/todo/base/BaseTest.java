package com.qacart.todo.base;

import com.qacart.todo.drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest class serves as a base class for all test classes in the application.
 * It initializes the WebDriver instance before each test method and quits it after each test method.
 */

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Create an instance of DriverFactory
        // Initialize the WebDriver instance using the non-static method
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        // Quit the driver after each test method
        if (driver != null) {
            driver.quit(); // Close the browser
        }
    }
}