package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.database.DatabaseConnectionManager;
import com.aimprosoft.kmb.database.dao.EmployeeDao;
import com.aimprosoft.kmb.domain.Employee;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {

    private static Logger logger = Logger.getLogger(EmployeeService.class);
    private final EmployeeDao employeeDao = new EmployeeDao();

    public long createEmployee(Employee employee) {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            final long employeeId = employeeDao.create(connection, employee);
            DatabaseConnectionManager.commit(connection);
            return employeeId;
        } catch (SQLException e) {
            logger.error("Can`t create a new employee", e);
            DatabaseConnectionManager.rollback(connection);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return -1;
    }


    public Employee getEmployeeById(long id) {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            return employeeDao.getById(connection, id);
        } catch (SQLException e) {
            logger.error("Can`t get employee by id: " + id, e);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            return employeeDao.getAll(connection);
        } catch (SQLException e) {
            logger.error("Can`t get all employees");
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return null;
    }

    public List<Employee> getAllEmployeesFromDepartment(long id) {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            return employeeDao.getAllFromDepartment(connection, id);
        } catch (SQLException e) {
            logger.error("Can`t get all employees from department");
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return null;
    }

    public Employee updateEmployee(Employee employee) {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            final Employee updatedEmployee = employeeDao.update(connection, employee);
            DatabaseConnectionManager.commit(connection);
            return updatedEmployee;
        } catch (SQLException e) {
            logger.error("Can`t update employee", e);
            DatabaseConnectionManager.rollback(connection);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return employee;
    }

    public void deleteEmployee(long id) {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            employeeDao.deleteById(connection, id);
            DatabaseConnectionManager.commit(connection);
        } catch (SQLException e) {
            logger.error("Can`t delete employee by id: " + id, e);
            DatabaseConnectionManager.rollback(connection);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }
}
