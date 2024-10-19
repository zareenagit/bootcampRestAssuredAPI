package apiUtilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {
    private Properties properties;

    public PropertiesFile() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config/config.properties")) {
            if (inputStream == null) {
                throw new IOException("Property file not found in the classpath");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            // Consider using a logging framework instead of printStackTrace
            e.printStackTrace();
        }
    }
   
    public String getUsername() {
        return properties.getProperty("username", "defaultUsername");
    }

    public String getPassword() {
        return properties.getProperty("password", "defaultPassword");
    }

    
}
