package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.database.JdbcTemplate;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.domain.Department;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private static Logger logger = Logger.getLogger(DepartmentDaoJDBC.class);

    @Override
    public long create(Connection connection, Department department) throws RepositoryException {
        String sql = "INSERT INTO departments(department_name, comments) VALUES (?, ?)";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setString(2, department.getComments());
            preparedStatement.executeUpdate();
            final ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Can`t create a new department", e);
            throw new RepositoryException(e);
        }
        return -1;
    }

    @Override
    public Department getById(Connection connection, long id) throws RepositoryException {
        String sql = "SELECT * FROM departments WHERE id = ?";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractDepartmentFromResultSet(resultSet);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Can`t get department by id: " + id, e);
            throw new RepositoryException(e);
        }
        return null;
    }

    @Override
    public List<Department> getAll(Connection connection) throws RepositoryException {
        String sql = "SELECT * FROM departments";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<Department> departments = new ArrayList<>();
            while (resultSet.next()) {
                departments.add(extractDepartmentFromResultSet(resultSet));
            }
            preparedStatement.close();
            return departments;
        } catch (SQLException e) {
            logger.error("Can`t get all the departments", e);
            throw new RepositoryException(e);
        }
        //return Collections.emptyList();
    }

    @Override
    public Department update(Connection connection, Department department) throws RepositoryException {
        String sql = "UPDATE departments SET department_name = ?, comments = ? WHERE id = ?";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setString(2, department.getComments());
            preparedStatement.setLong(3, department.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Can`t to update the department with id: " + department.getId(), e);
            throw new RepositoryException(e);
        }
        return department;
    }

    @Override
    public void deleteById(Connection connection, long id) throws RepositoryException {
        String sql = "DELETE FROM departments WHERE id = ?";
        jdbcTemplate.deleteById(connection, sql, id);

    }

    @Override
    public boolean isDepartmentExists(Connection connection, Department department) throws RepositoryException {
        String sql = "SELECT count(id) FROM departments WHERE department_name = ?";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, department.getDepartmentName());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final int result = resultSet.getInt("count(id)");
                return result == 1;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Can`t check the existence same name of the department");
            throw new RepositoryException(e);
        }
        return false;
    }

    private Department extractDepartmentFromResultSet(ResultSet resultSet) throws SQLException {

        final Department department = new Department();
        department.setId(resultSet.getLong("id"));
        department.setDepartmentName(resultSet.getString("department_name"));
        department.setComments(resultSet.getString("comments"));
        return department;
    }
}
