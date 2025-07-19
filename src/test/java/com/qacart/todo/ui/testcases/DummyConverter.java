package com.qacart.todo.ui.testcases;

import com.qacart.todo.api.requests.RegisterApi;
import com.qacart.todo.utils.ConfigUtil;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;

public class DummyConverter {

    public static void main(String[] args) throws IOException, InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get(ConfigUtil.getInstance().getBaseUrl());
        driver.manage().window().maximize();

        RegisterApi registerApi = new RegisterApi();
        registerApi.register();

        List<io.restassured.http.Cookie> restAssuredCookies = registerApi.getCookies();

        for (io.restassured.http.Cookie cookie : restAssuredCookies) {
            // Convert RestAssured Cookie to Selenium Cookie
            Cookie seleniumCookie = new Cookie(cookie.getName(), cookie.getValue());
            driver.manage().addCookie(seleniumCookie);
        }

        driver.get(ConfigUtil.getInstance().getBaseUrl());

        Thread.sleep(60000);

        driver.quit();
    }
}