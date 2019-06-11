package com.aimprosoft.kmb.database;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesResolver {

    private static Logger logger = Logger.getLogger(PropertiesResolver.class);

    private String fileName = "/home/user/projects/java-blc-v.chernikin/src/main/resources/connection.properties";

    public Properties getProperties() {
        final Properties properties = new Properties();
        try {
            FileReader fileReader = new FileReader(fileName);
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("Can`t load a properties file.");
            e.printStackTrace();
        }
        return properties;
    }
}
