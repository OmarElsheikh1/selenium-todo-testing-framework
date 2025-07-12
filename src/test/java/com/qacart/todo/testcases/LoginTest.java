package com.qacart.todo.testcases;

import com.qacart.todo.base.BaseTest;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.pages.TodoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void shouldBeAbleToLoginWithEmailAndPassword() throws IOException {

        // Create an instance of the LoginPage class
        LoginPage loginPage = new LoginPage(driver);


        // Navigate to the application URL, and login using the combined method and clicks submit then check if a welcome message is displayed
        boolean isWelcomeDisplayed =
                loginPage
                        .load()
                        .login("omar1@example.com", "Test1234")
                        .isWelcomeMessageDisplayed();


        // Assert that welcome message is displayed after successful login
        Assert.assertTrue(isWelcomeDisplayed, "Welcome message should be displayed after successful login");
    }
}