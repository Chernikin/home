package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.database.DatabaseConnectionManager;
import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.database.jdbc.EmployeeDaoJDBC;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.exceptions.ServiceException;

import java.sql.Connection;
import java.util.List;

public class EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDaoJDBC();

    public long createEmployee(Employee employee) throws ServiceException {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            final boolean emailExists = employeeDao.isEmailExists(connection, employee);
            if (!emailExists) {
                employeeDao.create(connection, employee);
                DatabaseConnectionManager.commit(connection);
            }
        } catch (RepositoryException e) {
            DatabaseConnectionManager.rollback(connection);
            throw new ServiceException("Can`t create a new employee");
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return 0;
    }


    public Employee getEmployeeById(long id) throws ServiceException {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            return employeeDao.getById(connection, id);
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t get employee by id: " + id);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    public List<Employee> getAllEmployees() throws ServiceException {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            return employeeDao.getAll(connection);
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t get all employees");
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    public List<Employee> getAllEmployeesFromDepartment(long id) throws ServiceException {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            return employeeDao.getAllFromDepartment(connection, id);
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t get all employees from department with id: " + id);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    public Employee updateEmployee(Employee employee) throws ServiceException {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            final Employee updatedEmployee = employeeDao.update(connection, employee);
            DatabaseConnectionManager.commit(connection);
            return updatedEmployee;
        } catch (RepositoryException e) {
            DatabaseConnectionManager.rollback(connection);
            throw new ServiceException("Can`t to update employee");
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    public void deleteEmployee(long id) throws ServiceException {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            employeeDao.deleteById(connection, id);
            DatabaseConnectionManager.commit(connection);
        } catch (RepositoryException e) {
            DatabaseConnectionManager.rollback(connection);
            throw new ServiceException("Can`t delete employee by id: " + id);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }
}
