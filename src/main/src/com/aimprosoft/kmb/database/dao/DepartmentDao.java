package com.aimprosoft.kmb.database.dao;

import com.aimprosoft.kmb.database.JdbcTemplate;
import com.aimprosoft.kmb.domain.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepartmentDao implements GenericDao<Department> {

    final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public long create(Connection connection, Department department) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Department getById(Connection connection, long id) {
        String sql = "SELECT * FROM departments WHERE id = ?";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractDepartmentFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Department> getAll(Connection connection) {
        String sql = "SELECT * FROM departments";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<Department> departments = new ArrayList<>();
            while (resultSet.next()) {
                departments.add(extractDepartmentFromResultSet(resultSet));
            }
            return departments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Department update(Connection connection, Department department) {

        String sql = "UPDATE departments SET department_name = ?, comments = ? WHERE id = ?";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setString(2, department.getComments());
            preparedStatement.setLong(3, department.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public void deleteById(Connection connection, long id) {
        String sql = "DELETE FROM departments WHERE id = ?";
        jdbcTemplate.deleteById(connection, sql, id);

    }

    private Department extractDepartmentFromResultSet(ResultSet resultSet) throws SQLException {

        final Department department = new Department();
        department.setId(resultSet.getLong("id"));
        department.setDepartmentName(resultSet.getString("department_name"));
        department.setComments(resultSet.getString("comments"));
        return department;
    }
}
