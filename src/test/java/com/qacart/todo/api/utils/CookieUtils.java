package com.qacart.todo.api.utils;

import io.restassured.http.Cookie;
import java.util.ArrayList;
import java.util.List;

public class CookieUtils {

    public static List<org.openqa.selenium.Cookie> convertRestAssuredCookiesToSeleniumCookies(List<Cookie> restAssuredCookies) {

        // Create an empty List object to hold the converted Selenium cookies,
        // from type Cookie and stores it in a List
        List<org.openqa.selenium.Cookie> seleniumCookies = new ArrayList<>();

        // For each RestAssured Cookie in the list of restAssuredCookies,
        // we will convert it to a Selenium Cookie and add it to the list of seleniumCookies
        for (io.restassured.http.Cookie cookie : restAssuredCookies) {

            // Convert RestAssured Cookie to Selenium Cookie
            // Selenium Cookie = new Selenium Cookie(restAssured Cookie name, restAssured Cookie value)
            org.openqa.selenium.Cookie seleniumCookie = new org.openqa.selenium.Cookie(cookie.getName(), cookie.getValue());


            // Add the previous Selenium Cookie to the list of seleniumCookies (Object we created above)
            seleniumCookies.add(seleniumCookie);
        }
        return seleniumCookies;
    }
}