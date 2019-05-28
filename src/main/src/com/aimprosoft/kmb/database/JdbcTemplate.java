package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.exceptions.RepositoryException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTemplate {

    private static Logger logger = Logger.getLogger(JdbcTemplate.class);

    /*public long create(String sql, PreparedStatement ps) throws RepositoryException {
        Connection connection = DatabaseConnectionManager.getConnection();
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();

            DatabaseConnectionManager.commit(connection);
            final ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Can`t create a new", e);
            DatabaseConnectionManager.rollback(connection);
            throw new RepositoryException(e);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return -1;
    }*/

    public void deleteById(String sql, long id) throws RepositoryException {
        Connection connection = DatabaseConnectionManager.getConnection();
        String sqlTemplate = "DELETE FROM " + sql + " WHERE id = ?";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sqlTemplate);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            DatabaseConnectionManager.commit(connection);
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Can`t delete", e);
            DatabaseConnectionManager.rollback(connection);
            throw new RepositoryException(e);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }
}
