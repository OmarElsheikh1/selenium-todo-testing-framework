package com.qacart.todo.api.utils;

import com.github.javafaker.Faker;
import com.qacart.todo.api.models.User;

public class UserUtils {

    public static User generateRandomUser() {

        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "_automation@" + "example.com"; // A fixed email format for testing purposes
        String password = "ILikeTesting1234"; // A fixed password for testing purposes

        // Return a new User object with the random generated details from the Faker library
        return new User(email, firstName, lastName, password);
    }
}