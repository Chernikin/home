package com.aimprosoft.kmb.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.aimprosoft.kmb.exceptions.RepositoryException;
import org.apache.log4j.Logger;

public class JdbcTemplate {

    private static Logger logger = Logger.getLogger(JdbcTemplate.class);

    public void deleteById(Connection connection, String sql, long id) throws RepositoryException {

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Can`t delete", e);
            throw new RepositoryException(e);
        }
    }
}
