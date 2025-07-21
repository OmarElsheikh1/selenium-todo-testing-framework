package com.qacart.todo.ui.testcases;

import com.qacart.todo.base.BaseTest;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.utils.ConfigUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

@Feature("Auth Feature")
public class LoginTest extends BaseTest {

    @Story("Login with Email & Password")
    @Description("It will login by filling the email and password and navigate to the Todo page")
    @Test(description = "Test that login functionality using valid email and password")
    public void shouldBeAbleToLoginWithEmailAndPassword() throws IOException {

        // Create an instance of the LoginPage class
        LoginPage loginPage = new LoginPage(getDriver());


        // Navigate to the application URL, and login using the combined method and clicks submit then check if a welcome message is displayed
        boolean isWelcomeDisplayed =
                loginPage
                        .load()
                        .login(ConfigUtil.getInstance().getEmail(), ConfigUtil.getInstance().getPassword())
                        .isWelcomeMessageDisplayed();


        // Assert that welcome message is displayed after successful login
        Assert.assertTrue(isWelcomeDisplayed, "Welcome message should be displayed after successful login");
    }
}