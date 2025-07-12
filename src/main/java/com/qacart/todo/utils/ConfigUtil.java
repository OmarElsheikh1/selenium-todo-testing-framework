package com.qacart.todo.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Loads configuration values (like baseUrl, email, password) from a properties file.
 * <p>
 * Follows the Singleton pattern to ensure properties are read once and reused across the app.
 */

public class ConfigUtil {

    private Properties properties;
    private static ConfigUtil configUtil;

    // Private constructor to load the properties only once
    private ConfigUtil() throws IOException {

        properties = PropertiesUtil.loadProperties("src/test/resources/config/production.properties");
    }

    // Returns a shared instance of ConfigUtil
    public static ConfigUtil getInstance() throws IOException {
        if (configUtil == null) {
            configUtil = new ConfigUtil();
        }
        return configUtil;
    }

    // Returns the base URL, or throws an exception if missing
    public String getBaseUrl() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null) {
            return prop;
        }
        throw new RuntimeException("Base URL not found in properties file");
    }

    // Returns the email, or throws an exception if missing
    public String getEmail() {
        String prop = properties.getProperty("email");
        if (prop != null) {
            return prop;
        }
        throw new RuntimeException("Email not found in properties file");
    }

    // Returns the password, or throws an exception if missing
    public String getPassword() {
        String prop = properties.getProperty("password");
        if (prop != null) {
            return prop;
        }
        throw new RuntimeException("Password not found in properties file");
    }
}