package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.DatabaseConnectionManager;
import com.aimprosoft.kmb.database.GenericDao;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao<T> {

    private static Logger logger = Logger.getLogger(DepartmentDaoJDBC.class);

    public List<String> objects = new ArrayList<>();

    public long create(T object, String sql,) throws RepositoryException {
        Connection connection = DatabaseConnectionManager.getConnection();
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            prepareStatementForCreate(object, preparedStatement);
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
    }




   /* private void prepareStatementForCreate(T object, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, object.getDepartmentName());
        preparedStatement.setString(2, object.getComments());
    }*/


}
