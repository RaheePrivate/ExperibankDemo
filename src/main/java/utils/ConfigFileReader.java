package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath = "configuration.properties";

    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getAccessKey() {
        String driverPath = properties.getProperty("accessKey");
        if (driverPath!= null) {
            return driverPath;
        } else {
            throw new RuntimeException("accessKey not specified in the configuration.properties file.");
        }
    }

    public String getCloudURL() {
        String url = properties.getProperty("cloudURL");
        if (url != null) {
            return url;
        } else {
            throw new RuntimeException("cloudURL not specified in the configuration.properties file.");
        }
    }

    public String getLocalURL() {
        String url = properties.getProperty("localURL");
        if (url != null) {
            return url;
        } else {
            throw new RuntimeException("localURL not specified in the configuration.properties file.");
        }
    }
}
