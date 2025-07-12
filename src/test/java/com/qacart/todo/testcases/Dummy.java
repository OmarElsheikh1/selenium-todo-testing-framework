package com.qacart.todo.testcases;

import java.io.*;
import java.util.Properties;

public class Dummy {

    public static void main(String[] args) throws IOException {
        // This is a fake class to ensure the package structure is valid.
        // It can be used for testing purposes or as a placeholder.
        System.out.println("This is a dummy class in the test cases package.");


        // Create a File object pointing to the properties file
        // This represents the actual file path in the test resources folder
        File file = new File("src/test/resources/config/production.properties");

        // Create a FileInputStream to read from the file
        // This opens a stream to the file we want to read
        InputStream inputStream = new FileInputStream(file);

        // Create a Properties object which can load and store key-value pairs
        Properties properties = new Properties();

        // Load the properties from the input stream (i.e., from the file)
        // This reads the content of the file and fills the Properties object
        properties.load(inputStream);

        // Retrieve the value associated with the key "baseUrl"
        // It will return the value like "https://todo.qacart.com" if present in the file
        String baseUrl = properties.getProperty("baseUrl");

        // Print out the value of the baseUrl to confirm it's read correctly
        System.out.println("Base URL from properties file: " + baseUrl);
    }
}