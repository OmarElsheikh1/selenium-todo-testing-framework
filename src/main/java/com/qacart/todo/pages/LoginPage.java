package com.qacart.todo.pages;

import com.qacart.todo.utils.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;

import java.io.IOException;
import java.util.Properties;

public class LoginPage extends BasePage {

    // Constructor - accepts the WebDriver instance from the test class
    // This allows the page class to interact with the same browser session
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    // Locators for the Todo login page elements
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("submit");


    // Loads the login page using the URL from the properties file (supports method chaining)
    public LoginPage load() throws IOException {

        // Step 1: Load the properties from the production.properties file
        Properties props = PropertiesUtil.loadProperties("src/test/resources/config/production.properties");

        // Step 2: Read the base URL from the loaded properties and open it in the browser
        driver.get(props.getProperty("baseUrl"));

        // Step 3: Return the current LoginPage instance for method chaining
        return this;
    }


    // Individual actions
    // Enters the given email into the email field
    public LoginPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    // Enters the given password into the password field
    public LoginPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    // Clicks the login button
    public TodoPage clickSubmit() {
        driver.findElement(loginButton).click();
        return new TodoPage(driver);
    }


    // Combined login method for common flows
    // This method allows you to log in by providing both email and password
    public TodoPage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return clickSubmit();
    }
}