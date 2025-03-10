package com.MyStudentApp.model.security;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties = new Properties();

    public ConfigLoader(String FolderName) {
        try (InputStream input = getClass().getResourceAsStream("/" + FolderName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find admin.properties");
                return;
            }
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPropertyString(String key) {
        return properties.getProperty(key);
    }

    public Integer getPropertyInteger(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}
