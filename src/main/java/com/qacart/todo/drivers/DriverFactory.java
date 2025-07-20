package com.qacart.todo.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {

    private WebDriver driver;

    // Non-static method to initialize the WebDriver instance
    public WebDriver initDriver() {

        // Determine the browser type from system properties or default to Chrome and used toUpperCase for consistency
        String browser = System.getProperty("browser", "CHROME").toUpperCase();

        // Initialize the WebDriver based on the browser type
        switch (browser) {
            case "CHROME":
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        // Set up the driver with common configurations
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public WebDriver openUrl(String url) {
        driver.get("https://todo.qacart.com");
        return driver;
    }
}