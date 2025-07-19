package com.qacart.todo.pages;

import com.qacart.todo.api.config.Endpoints;
import com.qacart.todo.utils.ConfigUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class NewTodoPage extends BasePage {

    // Constructor - accepts the WebDriver instance from the test class
    // This allows the page class to interact with the same browser session
    public NewTodoPage(WebDriver driver) {
        super(driver);
    }


    // Locators for the New Todo page elements
    private final By enterNewTodo = By.cssSelector("[data-testid=\"new-todo\"]");
    private final By createTodoButton = By.cssSelector("[data-testid=\"submit-newTask\"]");



    // Method to navigate to the New Todo page
    public NewTodoPage load() throws IOException {
        driver.get(ConfigUtil.getInstance().getBaseUrl() + Endpoints.NEW_TODO_END_POINT);
        return this;
    }

    // Method to add a new todo item and create it
    public TodoPage addNewTodo(String todoText) {
        driver.findElement(enterNewTodo).sendKeys(todoText);
        driver.findElement(createTodoButton).click();
        return new TodoPage(driver);
    }

    // Method to click the "Create Todo" button separately
    public void clickCreateTodoButton() {
        driver.findElement(createTodoButton).click();
    }
}