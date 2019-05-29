package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.DatabaseConnectionManager;
import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.database.JdbcTemplate;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.rowMapper.EmployeeRowMapper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDaoJDBC implements EmployeeDao {


    private JdbcTemplate<Employee> jdbcTemplate = new JdbcTemplate<>();
    private EmployeeRowMapper employeeRowMapper = new EmployeeRowMapper();
    private static Logger logger = Logger.getLogger(EmployeeDaoJDBC.class);

    @Override
    public boolean isExists(Employee employee) throws RepositoryException {
        String sql = "SELECT count(id) FROM employees WHERE email = ?";
        List<Object> params = new ArrayList<>();
        params.add(employee.getEmail());
        String log = "Can`t check the existence same email of the employee";
        return jdbcTemplate.isExist(sql, params, log);
    }

    @Override
    public void create(Employee employee) throws RepositoryException {
        String sql = "INSERT INTO employees (first_name, last_name, email, age, phone_number, employment_date, department_id) VALUES (?,?,?,?,?,?,?)";
        List<Object> params = getObjects(employee);
        String log = "Can`t create employee";
        jdbcTemplate.create(sql, params, log);
    }

    @Override
    public Employee getById(long id) throws RepositoryException {
        String sql = "SELECT * FROM employees join departments on employees.department_id = departments.id WHERE employees.id = ?";
        final Employee employee = jdbcTemplate.getById(sql, id, employeeRowMapper);
        return employee;
    }

    @Override
    public List<Employee> getAll() throws RepositoryException {
        String sql = "SELECT * FROM employees";
        final List<Employee> allEmployees = jdbcTemplate.getAll(sql, employeeRowMapper);
        return allEmployees;
    }


    @Override
    public Employee update(Employee employee) throws RepositoryException {
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, age = ?, phone_number = ?, employment_date = ?, department_id = ? WHERE id = ?";
        List<Object> params = getObjects(employee);
        params.add(employee.getId());
        String log = "Can`t to update employee";
        final Employee updatedEmployee = jdbcTemplate.update(sql, params, log);
        return updatedEmployee;
    }

    @Override
    public void deleteById(long id) throws RepositoryException {
        String sql = "DELETE FROM employees WHERE id = ?";
        jdbcTemplate.deleteById(sql, id);
    }


    @Override
    public List<Employee> getAllFromDepartment(long id) throws RepositoryException {
        Connection connection = DatabaseConnectionManager.getConnection();
        String sql = "SELECT * FROM employees join departments on employees.department_id = departments.id WHERE department_id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                employees.add(employeeRowMapper.extract(resultSet));
            }
            return employees;
        } catch (SQLException e) {
            logger.error("Can`t get all employees from department with id: " + id, e);
            throw new RepositoryException("Can`t get all employees");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    private List<Object> getObjects(Employee employee) {
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
}
