package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.database.rowMapper.EmployeeRowMapper;
import com.aimprosoft.kmb.database.rowMapper.RowMapper;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDaoJdbc extends AbstractDaoJdbc<Employee, Long> implements EmployeeDao {

    private static Logger logger = Logger.getLogger(EmployeeDaoJdbc.class);

    private static final String GET_ALL_FROM_DEPARTMENT = "SELECT * FROM employees JOIN departments ON employees.department_id = departments.id WHERE department_id = ?";
    private static final String DELETE_ALL_FROM_DEPARTMENT = "DELETE FROM employees WHERE department_id = ?";
    private static final String CHECK_ON_EXIST = "SELECT count(id) FROM employees WHERE email = ?";
    private static final String GET_BY_EMAIL = "SELECT * FROM employees JOIN departments ON employees.department_id = departments.id WHERE email = ?";


    @Override
    public List<Employee> getAllFromDepartment(Long id) throws RepositoryException {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_FROM_DEPARTMENT)) {
            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                employees.add(getRowMapper().extract(resultSet));
            }
            return employees;
        } catch (SQLException e) {
            logger.debug("Can`t get all employees from department with id: " + id, e);
            throw new RepositoryException("Can`t get all employees from department with id: " + id, e);
        }
    }

    @Override
    public Employee getByEmail(String email) throws RepositoryException {
        Employee result = null;
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = getRowMapper().extract(resultSet);
            }
        } catch (SQLException e) {
            logger.debug("Can`t get employee by email. ", e);
            throw new RepositoryException("Can`t get employee by email. ", e);
        }
        return result;
    }


    @Override
    public boolean isExists(Employee employee) throws RepositoryException {
        List<Object> params = new ArrayList<>();
        params.add(employee.getEmail());
        String log = "Can`t check the existence same email of the employee";
        return getJdbcTemplate().isExist(CHECK_ON_EXIST, params, log);
    }

    @Override
    public void deleteAllFromDepartment(Long id) throws RepositoryException {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_FROM_DEPARTMENT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.debug("Can`t delete all from department with id: " + id, e);
            throw new RepositoryException("Can`t delete all from department with id: " + id, e);
        }
    }

    @Override
    protected List<Object> getObjects(Employee employee) {
        List<Object> params = new ArrayList<>();
        params.add(employee.getFirstName());
        params.add(employee.getLastName());
        params.add(employee.getEmail());
        params.add(employee.getAge());
        params.add(employee.getPhoneNumber());
        params.add(new Date(employee.getEmploymentDate().getTime()));
        params.add(employee.getDepartment().getId());
        return params;
    }

    @Override
    protected final String getQueryForCreate() {
        return "INSERT INTO employees(first_name, last_name, email, age, phone_number, employment_date, department_id) VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    protected final String getQueryForGetById() {
        return "SELECT * FROM employees join departments on employees.department_id = departments.id WHERE employees.id = ?";
    }

    @Override
    protected final String getQueryForUpdate() {
        return "UPDATE employees SET first_name = ?, last_name = ?, email = ?, age = ?, phone_number = ?, " +
                "employment_date = ?, department_id = ? WHERE id = ?";
    }

    @Override
    protected final String getQueryForGetAll() {
        return "SELECT * FROM employees";
    }


    @Override
    protected final String getQueryForDeleteById() {
        return "DELETE FROM employees WHERE id = ?";
    }


    @Override
    protected Long getIdForUpdate(Employee employee) {
        return employee.getId();
    }

    @Override
    protected RowMapper<Employee> getRowMapper() {
        return new EmployeeRowMapper();
    }


}
