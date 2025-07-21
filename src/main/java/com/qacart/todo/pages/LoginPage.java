package com.qacart.todo.pages;

import com.qacart.todo.utils.ConfigUtil;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.IOException;

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


    @Step("Load the login page")
    // Opens the login page using the base URL from the config file
    public LoginPage load() throws IOException {
        driver.get(ConfigUtil.getInstance().getBaseUrl());
        return this;
    }


    @Step
    // Individual actions
    // Enters the given email into the email field
    public LoginPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step
    // Enters the given password into the password field
    public LoginPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step
    // Clicks the login button
    public TodoPage clickSubmit() {
        driver.findElement(loginButton).click();
        return new TodoPage(driver);
    }


    @Step
    // Combined login method for common flows
    // This method allows you to log in by providing both email and password
    public TodoPage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return clickSubmit();
    }
}