package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.rowMapper.RowMapper;
import com.aimprosoft.kmb.domain.Entity;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T extends Entity, R> {

    private static Logger logger = Logger.getLogger(JdbcTemplate.class);

    public void create(String sql, List<Object> params, String log) throws RepositoryException {
        getLogicForUpdate(sql, params, log);
    }

    public T getById(String sql, R id, RowMapper<T> rowMapper) throws RepositoryException {
//        Connection connection = null;
        PreparedStatement preparedStatement = null;
        T result = null;
        try {
            try (Connection connection = DatabaseConnectionManager.getConnection()) {
//                connection = DatabaseConnectionManager.getConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, id);
                final ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    result = rowMapper.extract(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Can`t get by id: " + id, e);
            throw new RepositoryException("Can`t get by id", e);
        }
//        try {
//            connection = DatabaseConnectionManager.getConnection();
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setObject(1, id);
//            final ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                result = rowMapper.extract(resultSet);
//            }
//            resultSet.close();
//        } catch (SQLException e) {
//            logger.error("Can`t get by id: " + id, e);
//            throw new RepositoryException("Can`t get by id", e);
//        } finally {
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//            } catch (SQLException e) {
//                logger.error("Can`t closed statement.");
//            }
//            DatabaseConnectionManager.closeConnection(connection);
//        }
        return result;
    }


    public List<T> getAll(String sql, RowMapper<T> rowMapper) throws RepositoryException {
//        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
//            connection = DatabaseConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<T> all = new ArrayList<>();
            while (resultSet.next()) {
                all.add(rowMapper.extract(resultSet));
            }
            resultSet.close();
            return all;
        } catch (SQLException e) {
            logger.error("Can`t get all", e);
            throw new RepositoryException("Can`t get all", e);
//        } finally {
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//            } catch (SQLException e) {
//                logger.error("Can`t closed statement.");
//            }
//            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    public T update(String sql, List<Object> params, String log) throws RepositoryException {
        getLogicForUpdate(sql, params, log);
        return null;
    }


    private void getLogicForUpdate(String sql, List<Object> params, String log) throws RepositoryException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
            preparedStatement.executeUpdate();
            DatabaseConnectionManager.commit(connection);
        } catch (SQLException e) {
            logger.error(log, e);
            DatabaseConnectionManager.rollback(connection);
            throw new RepositoryException("Can`t get logic", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("Can`t closed statement.");
            }
        }
        DatabaseConnectionManager.closeConnection(connection);
    }

    public boolean isExist(String sql, List<Object> params, String log) throws RepositoryException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final int result = resultSet.getInt("count(id)");
                return result == 1;
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(log);
            throw new RepositoryException("Can`t check items on exist", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("Can`t closed statement.");
            }
        }
        DatabaseConnectionManager.closeConnection(connection);
        return false;
    }


    public void deleteById(String sql, R id) throws RepositoryException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
            DatabaseConnectionManager.commit(connection);
        } catch (SQLException e) {
            logger.error("Can`t delete", e);
            DatabaseConnectionManager.rollback(connection);
            throw new RepositoryException("Can`t delete", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("Can`t closed statement.");
            }
            DatabaseConnectionManager.closeConnection(connection);
        }
    }


}
