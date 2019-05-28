package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.DatabaseConnectionManager;
import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.database.JdbcTemplate;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDaoJDBC implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private static Logger logger = Logger.getLogger(EmployeeDaoJDBC.class);

    @Override
    public long create(Employee employee) throws RepositoryException {
        Connection connection = DatabaseConnectionManager.getConnection();
        String sql = "INSERT INTO employees (first_name, last_name, email, age, phone_number, employment_date, department_id) VALUES (?,?,?,?,?,?,?)";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            setEmployeeStatement(employee, preparedStatement);
            preparedStatement.executeUpdate();
            DatabaseConnectionManager.commit(connection);
            final ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Can`t create a new employee", e);
            DatabaseConnectionManager.rollback(connection);
            throw new RepositoryException(e);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return -1;
    }

    @Override
    public Employee getById(long id) throws RepositoryException {
        Connection connection = DatabaseConnectionManager.getConnection();
        String sql = "SELECT * FROM employees join departments on employees.department_id = departments.id WHERE employees.id = ?";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractEmployeeFromResultSet(resultSet);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Can`t get employee by id: " + id, e);
            throw new RepositoryException(e);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<Employee> getAll() throws RepositoryException {
        Connection connection = DatabaseConnectionManager.getConnection();
        String sql = "SELECT * FROM employees join departments on employees.department_id = departments.id";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                employees.add(extractEmployeeFromResultSet(resultSet));
            }
            preparedStatement.close();
            return employees;
        } catch (SQLException e) {
            logger.error("Can`t get all employees", e);
            throw new RepositoryException(e);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    @Override
    public List<Employee> getAllFromDepartment(long departmentId) throws RepositoryException {
        Connection connection = DatabaseConnectionManager.getConnection();
        String sql = "SELECT * FROM employees join departments on employees.department_id = departments.id WHERE department_id = ?";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, departmentId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                employees.add(extractEmployeeFromResultSet(resultSet));
            }
            preparedStatement.close();
            return employees;
        } catch (SQLException e) {
            logger.error("Can`t get all employees from department with id: " + departmentId, e);
            throw new RepositoryException(e);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    @Override
    public boolean isEmailExists(Employee employee) throws RepositoryException {
        Connection connection = DatabaseConnectionManager.getConnection();
        String sql = "SELECT count(id) FROM employees WHERE email = ?";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getEmail());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final int result = resultSet.getInt("count(id)");
                return result == 1;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Can`t check the existence same email of the employee");
            throw new RepositoryException(e);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Employee update(Employee employee) throws RepositoryException {
        Connection connection = DatabaseConnectionManager.getConnection();
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, age = ?, phone_number = ?, employment_date = ?, department_id = ? WHERE id = ?";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            setEmployeeStatement(employee, preparedStatement);
            preparedStatement.setLong(8, employee.getId());
            preparedStatement.executeUpdate();
            DatabaseConnectionManager.commit(connection);
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Can`t update an employee");
            DatabaseConnectionManager.rollback(connection);
            throw new RepositoryException(e);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return employee;
    }

    @Override
    public void deleteById(long id) throws RepositoryException {
        String sql = "employees";
        jdbcTemplate.deleteById(sql, id);

    }

    private Employee extractEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setEmail(resultSet.getString("email"));
        employee.setAge(resultSet.getInt("age"));
        employee.setPhoneNumber(resultSet.getString("phone_number"));
        employee.setEmploymentDate(resultSet.getDate("employment_date"));
        final Department department = new Department();
        department.setId(resultSet.getLong("department_id"));
        department.setDepartmentName(resultSet.getString("department_name"));
        department.setComments(resultSet.getString("comments"));
        employee.setDepartment(department);
        return employee;
    }

    private void setEmployeeStatement(Employee employee, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, employee.getEmail());
        preparedStatement.setInt(4, employee.getAge());
        preparedStatement.setString(5, employee.getPhoneNumber());
        preparedStatement.setDate(6, new Date(employee.getEmploymentDate().getTime()));
        preparedStatement.setLong(7, employee.getDepartment().getId());
    }
}
