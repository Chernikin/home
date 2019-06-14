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

public class JdbcTemplate<T extends Entity, Id> {

    private static Logger logger = Logger.getLogger(JdbcTemplate.class);

    public void create(String sql, List<Object> params, String log) throws RepositoryException {
        getLogicForUpdate(sql, params, log);
    }

    public T getById(String sql, Id id, RowMapper<T> rowMapper) throws RepositoryException {
        T result = null;
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = rowMapper.extract(resultSet);
            }
        } catch (SQLException e) {
            logger.debug("Can`t get by id: " + id, e);
            throw new RepositoryException("Can`t get by id", e);
        }
        return result;
    }


    public List<T> getAll(String sql, RowMapper<T> rowMapper) throws RepositoryException {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<T> all = new ArrayList<>();
            while (resultSet.next()) {
                all.add(rowMapper.extract(resultSet));
            }
            return all;
        } catch (SQLException e) {
            logger.debug("Can`t get all. ", e);
            throw new RepositoryException("Can`t get all. ", e);
        }
    }

    public T update(String sql, List<Object> params, String log) throws RepositoryException {
        getLogicForUpdate(sql, params, log);
        return null;
    }


    private void getLogicForUpdate(String sql, List<Object> params, String log) throws RepositoryException {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(log, e);
            throw new RepositoryException("Can`t get logic for create/update. ", e);
        }
    }

    public boolean isExist(String sql, List<Object> params, String log) throws RepositoryException {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final int result = resultSet.getInt("count(id)");
                return result == 1;
            }
        } catch (SQLException e) {
            logger.debug(log);
            throw new RepositoryException("Can`t check items on exist", e);
        }
        return false;
    }


    public void deleteById(String sql, Id id) throws RepositoryException {

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.debug("Can`t delete by id: " + id, e);
            throw new RepositoryException("Can`t delete by id: " + id, e);
        }
    }


}
