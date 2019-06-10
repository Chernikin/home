package com.aimprosoft.kmb.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.log4j.Logger;

public class DatabaseConnectionManager {

    private static Logger logger = Logger.getLogger(DatabaseConnectionManager.class);

    public static Connection getConnection() throws RepositoryException {

        Properties properties = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("connection.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RepositoryException("Can`t get connection properties", e);

        }

        final String driver = properties.getProperty("jdbc.driver");
        final String url = properties.getProperty("jdbc.url");
        final String username = properties.getProperty("jdbc.username");
        final String password = properties.getProperty("jdbc.password");
        try {
            Class.forName(driver);
            final Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            logger.error("Can`t establish a new connection to the database");
            throw new RepositoryException("The database is unavailable", e);
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
