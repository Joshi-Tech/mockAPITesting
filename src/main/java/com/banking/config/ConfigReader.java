package com.banking.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();
    private static final String ENV = System.getProperty("env", "dev");
    private static final String FILE_NAME = "config/" + ENV + ".properties";

    static {
        loadProperties();
    }

    private ConfigReader() {
    }

    private static void loadProperties() {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(FILE_NAME)) {

            if (inputStream == null) {
                throw new RuntimeException("Configuration file not found: " + FILE_NAME);
            }

            PROPERTIES.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + FILE_NAME, e);
        }
    }

    public static String getProperty(String key) {
        String value = PROPERTIES.getProperty(key);

        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("Property '" + key + "' is missing in file: " + FILE_NAME);
        }

        return value.trim();
    }
}