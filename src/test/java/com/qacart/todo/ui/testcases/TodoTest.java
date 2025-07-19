package com.qacart.todo.ui.testcases;

import com.qacart.todo.api.requests.RegisterApi;
import com.qacart.todo.api.requests.TasksApi;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.pages.NewTodoPage;
import com.qacart.todo.pages.TodoPage;
import com.qacart.todo.utils.ConfigUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;


public class TodoTest extends BaseTest {

    @Test
    public void testTest() throws InterruptedException {

        driver.get("https://todo.qacart.com");

        driver.get("https://todo.qacart.com");
        driver.findElement(By.id("email")).sendKeys("omar1@example.com");
        driver.findElement(By.id("password")).sendKeys("Test1234");
        driver.findElement(By.id("submit")).click();


        // ✅ Wait until some element appears from the dashboard AFTER login
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid=\"welcome\"]")));

        // ✅ Now cookies should be available
        Set<Cookie> seleniumCookies = driver.manage().getCookies();


        if (seleniumCookies.isEmpty()) {
            System.out.println("❌ No cookies found after login.");
        } else {
            System.out.println("✅ Found cookies:");
            for (Cookie cookie : seleniumCookies) {
                System.out.println(cookie.getName() + " = " + cookie.getValue());
            }
        }
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }


    @Test
    public void shouldBeAbleToAddNewTodo() throws IOException, InterruptedException {

        RegisterApi registerApi = new RegisterApi();
        registerApi.register();


        NewTodoPage newTodoPage = new NewTodoPage(driver);
        newTodoPage.load();
        injectCookiesToBrowser(registerApi.getCookies());
        String actualResult = newTodoPage
                .load()
                .addNewTodo("Learn Selenium")
                .getTodoText();

        Assert.assertEquals(actualResult, "Learn Selenium",
                "The todo item text should match the expected value after adding a new todo");
    }


    @Test(enabled = true)
    public void shouldBeAbleToDeleteTodo() throws IOException {

        // Register a new user and get the cookies
        RegisterApi registerApi = new RegisterApi();
        registerApi.register();

        // Add a new task using the TasksApi
        TasksApi tasksApi = new TasksApi();
        tasksApi.addTask(registerApi.getToken());

        // Load the TodoPage and inject cookies to the browser
        TodoPage todoPage = new TodoPage(driver);
        todoPage.load();
        injectCookiesToBrowser(registerApi.getCookies());
        boolean isNoTodoDisplayed = todoPage
                .load()
                .clickDeleteTodo()
                .isWelcomeMessageDisplayed();

        // Assert that no todo item are displayed
        Assert.assertTrue(isNoTodoDisplayed,
                "No todo items should be displayed after deletion");

    }
}