package com.qacart.todo.api.models;

public class User {

    private String email;
    private String firstName;
    private String lastName;
    private String password;


    // Constructor used to initialize all fields when creating a new User object
    // Useful when we want to set values directly at the time of object creation
    public User(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }


    // Getters and Setters:
    // These methods provide controlled access to the private fields of this class.
    // - Getters allow other classes to read field values safely.
    // - Setters allow updating field values in a controlled way.
    // This follows the principle of encapsulation in object-oriented programming,
    // where direct access to fields is restricted to protect data integrity.
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}