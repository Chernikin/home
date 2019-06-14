package com.aimprosoft.kmb.database.jdbc;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertiesResolver {

    private static Logger logger = Logger.getLogger(PropertiesResolver.class);

    public Properties getProperties(String fileName) {
        final URL resource = this.getClass().getClassLoader().getResource(fileName);
        final Properties properties = new Properties();
        try {
            if (resource == null) {
                return properties;
            }
            FileReader fileReader = new FileReader(resource.getFile());
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            logger.error("Can`t find a properties file.");
            throw new RuntimeException("Can`t find properties file", e);
        } catch (IOException e) {
            logger.error("Can`t load a properties file.");
            throw new RuntimeException("Can`t load a properties file.", e);
        }
        return properties;
    }
}
