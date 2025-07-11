package com.qacart.todo.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverFactory {

    private static WebDriver driver;

    // Static method to initialize the WebDriver instance
    public static WebDriver initializeDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    // Non-static method to initialize the WebDriver instance
    public WebDriver initDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public WebDriver getUrl(String url) {
        driver.get("https://todo.qacart.com/");
        return driver;
    }
}