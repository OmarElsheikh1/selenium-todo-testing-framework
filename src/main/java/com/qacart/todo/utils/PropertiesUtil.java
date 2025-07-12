package com.qacart.todo.utils;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil {

    // Loads a .properties file from the given path and returns the Properties object
    public static Properties loadProperties(String filePath) throws IOException {

        // Define the file to be loaded
        File file = new File(filePath);

        // Create a Properties object to hold the key-value pairs
        Properties properties = new Properties();

        // Use try-with-resources to auto-close the stream
        try (InputStream inputStream = new FileInputStream(file)) {
            // Load the properties from the input stream
            properties.load(inputStream);
        }

        // Return the loaded properties
        return properties;
    }
}