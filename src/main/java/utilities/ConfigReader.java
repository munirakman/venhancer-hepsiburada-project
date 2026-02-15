package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            properties = new Properties();
            properties.load(file);
        } catch (Exception e) {
            throw new RuntimeException("Config file not found!");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
