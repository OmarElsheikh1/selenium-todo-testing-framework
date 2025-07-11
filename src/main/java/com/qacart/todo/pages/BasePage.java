package com.qacart.todo.pages;

import org.openqa.selenium.WebDriver;

/**
 * BasePage class serves as a base class for all page classes in the application.
 * It initializes the WebDriver instance that can be used by derived page classes.
 */

public class BasePage {

    // Initializing the WebDriver instance
    protected final WebDriver driver;

    // Constructor that accepts a WebDriver instance
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}