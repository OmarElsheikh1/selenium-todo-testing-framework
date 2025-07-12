package com.qacart.todo.testcases;

import com.qacart.todo.base.BaseTest;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.pages.NewTodoPage;
import com.qacart.todo.pages.TodoPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TodoTest extends BaseTest {

    @Test
    public void shouldBeAbleToAddNewTodo() {

        // Create an instance of the LoginPage class
        LoginPage loginPage = new LoginPage(driver);


        // Navigate to the application URL
        String actualResult =
                loginPage
                        .load()
                        .login("omar1@example.com", "Test1234")
                        .clickAddTodoButton()
                        .addNewTodo("Learn Selenium")
                        .getTodoText();


        // Assert that the new todo item is displayed in the list
        Assert.assertEquals(actualResult, "Learn Selenium", "The new todo item should be displayed in the list");
    }

    @Test (enabled = false)
    public void shouldBeAbleToDeleteTodo() {

        // Create an instance of the LoginPage class
        LoginPage loginPage = new LoginPage(driver);


        // Navigate to the application URL
        boolean isNoTodoDisplayed =
                loginPage
                        .load()
                        .login("omar1@example.com", "Test1234")
                        .clickAddTodoButton()
                        .addNewTodo("Learn Selenium").clickDeleteTodo().isNoTodoMessageDisplayed();


        // Assert that no todo item are displayed
        Assert.assertTrue(isNoTodoDisplayed, "No todo items should be displayed after deletion");
    }
}