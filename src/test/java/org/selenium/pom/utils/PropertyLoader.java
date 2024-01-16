package org.selenium.pom.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    public static Properties loadProperties(String fileName) {

        Properties properties = new Properties();
        try (InputStream input = PropertyLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}
