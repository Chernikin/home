package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.exceptions.RepositoryException;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesResolver {

    private static Logger logger = Logger.getLogger(PropertiesResolver.class);

    private String fileName = "/home/user/projects/java-blc-v.chernikin/src/main/resources/connection.properties";

    public Properties getProperties() throws RepositoryException {
        final Properties properties = new Properties();
        try {
            FileReader fileReader = new FileReader(fileName);
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
           throw new RepositoryException("Can`t find properties file", e);
        } catch (IOException e) {
            logger.error("Can`t load a properties file.");
            throw new RepositoryException("Can`t load a properties file.", e);
        }
        return properties;
    }
}
