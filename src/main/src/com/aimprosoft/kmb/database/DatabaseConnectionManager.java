package com.aimprosoft.kmb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.aimprosoft.kmb.exceptions.RepositoryException;
import org.apache.log4j.Logger;

public class DatabaseConnectionManager {


    private final static String mysql_database_url = "jdbc:mysql://localhost:3306/kmb";
    private final static String mysql_database_username = "root";
    private final static String mysql_database_password = "ChernikinV12";

    private static Logger logger = Logger.getLogger(DatabaseConnectionManager.class);

    public static Connection getConnection() throws RepositoryException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            final Connection connection = DriverManager.getConnection(mysql_database_url, mysql_database_username, mysql_database_password);
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
