package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.exceptions.RepositoryException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {

    private static PropertiesResolver propertiesResolver = new PropertiesResolver();

    private static Logger logger = Logger.getLogger(DatabaseConnectionManager.class);

    private static String databaseDriver;
    private static String databaseUrl;
    private static String databaseUserName;
    private static String databasePassword;
    private static String fileName = "connection.properties";

    private static void initDbProperties(String fileName) throws RepositoryException {
        final Properties dbProperties = propertiesResolver.getProperties(fileName);
        if (dbProperties.isEmpty()) {
            logger.error("Can`t get connection properties.");
            throw new RepositoryException("Can`t get connection properties.");
        }

        databaseDriver = dbProperties.getProperty("database.driver");
        databaseUrl = dbProperties.getProperty("database.url");
        databaseUserName = dbProperties.getProperty("database.username");
        databasePassword = dbProperties.getProperty("database.password");
    }


    public static Connection getConnection() throws RepositoryException {
        initDbProperties(fileName);
        try {
            Class.forName(databaseDriver);
            return DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
        } catch (SQLException e) {
            logger.error("Can`t establish a new connection to the database. ", e);
            throw new RepositoryException("Can`t establish connection with database. ", e);
        } catch (ClassNotFoundException e) {
            throw new RepositoryException("Can`t find the jdbc driver. ", e);
        }
    }
}
