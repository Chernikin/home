package kmb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {


    private final static String mysql_database_url = "jdbc:mysql://localhost:3306/kmb";
    private final static String mysql_database_username = "root";
    private final static String mysql_database_password = "ChernikinV12";

    public static Connection getConnection() throws SQLException {
        final Connection connection = DriverManager.getConnection(mysql_database_url, mysql_database_username, mysql_database_password);
        connection.setAutoCommit(false);
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void commit(Connection connection) {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
