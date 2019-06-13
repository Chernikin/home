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

    private static void initDbProperties() throws RepositoryException {
        final Properties dbProperties = propertiesResolver.getProperties();
        if (dbProperties.isEmpty()) {
            logger.error("Database properties is empty.");
            throw new RepositoryException("Database properties is empty.");
        }

        databaseDriver = dbProperties.getProperty("database.driver");
        databaseUrl = dbProperties.getProperty("database.url");
        databaseUserName = dbProperties.getProperty("database.username");
        databasePassword = dbProperties.getProperty("database.password");
    }


    public static Connection getConnection() throws RepositoryException {
        initDbProperties();
        try {
            Class.forName(databaseDriver);
            final Connection connection = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            logger.error("Can`t establish a new connection to the database");
            throw new RepositoryException("Can`t establish connection with database. ", e);
        } catch (ClassNotFoundException e) {
            throw new RepositoryException("Can`t find the jdbc driver", e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Can`t close the connection", e);
                e.printStackTrace();
            }
        }
    }

    public static void commit(Connection connection) {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                logger.error("Can`t do commit", e);
                e.printStackTrace();
            }
        }
    }

    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error("Can`t do rollback", e);
                e.printStackTrace();
            }
        }
    }

}
