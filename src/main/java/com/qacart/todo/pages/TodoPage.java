package com.qacart.todo.pages;

import com.qacart.todo.api.config.Endpoints;
import com.qacart.todo.utils.ConfigUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class TodoPage extends BasePage {

    // Constructor - accepts the WebDriver instance from the test class
    // This allows the page class to interact with the same browser session
    // super(driver) is used so the driver value is taken from here and is sent to the parent class
    public TodoPage(WebDriver driver) {
        super(driver);
    }


    // Locators for the Todo page elements
    private final By addTodoButton = By.cssSelector("[data-testid=\"add\"]");
    private final By completeTask = By.cssSelector("[data-testid=\"complete-task\"]");
    private final By deleteTodo = By.cssSelector("[data-testid=\"delete\"]");
    private final By welcomeMessage = By.cssSelector("[data-testid='welcome']");
    private final By todoItem = By.cssSelector("[data-testid=\"todo-item\"]");
    private final By todoList = By.cssSelector("[data-testid=\"no-todos\"]");


    // Opens the Todo page using the base URL from the config file
    public TodoPage load() throws IOException {
        driver.get(ConfigUtil.getInstance().getBaseUrl() + Endpoints.TODO_END_POINT);
        return this;
    }

    // Clicks the "Add Todo" button
    public NewTodoPage clickAddTodoButton() {
        driver.findElement(addTodoButton).click();
        return new NewTodoPage(driver);
    }

    // Clicks the "Complete Task" button
    public void clickCompleteTask() {
        driver.findElement(completeTask).click();
    }

    // Clicks the "Delete Todo" button
    public TodoPage clickDeleteTodo() {
        driver.findElement(deleteTodo).click();
        return this;
    }

    // Checks if the welcome message is displayed
    public boolean isWelcomeMessageDisplayed() {
        return driver.findElement(welcomeMessage).isDisplayed();
    }

    // Retrieves the text of a todo item
    public String getTodoText() {
        return driver.findElement(todoItem).getText();
    }

    // Checks if the "No Available Todos" message is displayed
    public boolean isNoTodoMessageDisplayed() {
        return driver.findElement(todoList).isDisplayed();
    }
}