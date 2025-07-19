package com.qacart.todo.api.requests;

import com.qacart.todo.api.models.Task;
import com.qacart.todo.utils.ConfigUtil;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TasksApi {

    // This method adds a new task to the todo list using the provided OAuth2 token.
    public void addTask(String token) throws IOException {

        Task task = new Task("Building Selenium", false);

        Response response = given()
                .baseUri(ConfigUtil.getInstance().getBaseUrl())
                .header("Content-Type", "application/json")
                .body(task)
                .auth().oauth2(token)
                .when()
                .post("api/v1/tasks")
                .then()
                .log().all()
                .extract().response();

        if (response.statusCode() != 201) {
            throw new RuntimeException("Failed to add task: " + response.body().asString());
        }
    }
}