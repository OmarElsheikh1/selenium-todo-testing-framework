package com.qacart.todo.api.requests;

import com.qacart.todo.api.models.User;
import com.qacart.todo.api.utils.UserUtils;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;


public class RegisterApi {

    private List<Cookie> restAssuredCookies;
    private String accessToken;
    private String userId;
    private String firstName;
    private String email;
    private User user;


    public String getToken() {
        return this.accessToken;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public List<Cookie> getCookies() {
        return this.restAssuredCookies;
    }

    public String getEmail() {
        return this.email;
    }

    public User getUser() {
        return this.user;
    }

    public void register() {

        // Generate a random user using the UserUtils class
        user = UserUtils.generateRandomUser();

        // Send a POST request to register the user
        Response response =
                given()
                        .baseUri("https://todo.qacart.com")
                        .header("Content-Type", "application/json")
                        .body(user)
                        .log().all()
                        .when()
                        .post("/api/v1/users/register")
                        .then()
                        .log().all()
                        .extract().response();

        if (response.statusCode() != 201) {
            throw new RuntimeException("Failed to register user: " + response.body().asString());
        }


        // Extract cookies, access token, user ID, and first name from the response
        restAssuredCookies = response.detailedCookies().asList();
        accessToken = response.path("access_token");
        userId = response.path("userID");
        firstName = response.path("firstName");
        email = response.path("email");
    }
}