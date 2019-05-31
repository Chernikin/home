package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.database.jdbc.EmployeeDaoJDBC;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.exceptions.ServiceException;

import java.util.List;

public class EmployeeService  {

    private final EmployeeDao employeeDao = new EmployeeDaoJDBC();


    public void createEmployee(Employee employee) throws ServiceException {
        try {
            final boolean emailExists = employeeDao.isExists(employee);
            if (!emailExists) {
                employeeDao.create(employee);
            } else {
                throw new ServiceException("Can`t create employee, because this email already used!");
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t create a new employee");
        }
    }


    public Employee getEmployeeById(long id) throws ServiceException {
        try {
            return employeeDao.getById(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t getById employee by id: " + id);
        }
    }

    public List<Employee> getAllEmployees() throws ServiceException {
        try {
            return employeeDao.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t getById all employees");
        }
    }

    public List<Employee> getAllEmployeesFromDepartment(long id) throws ServiceException {
        try {
            return employeeDao.getAllFromDepartment(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t getById all employees from department with id: " + id);
        }
    }


    public Employee updateEmployee(Employee employee) throws ServiceException {
        try {
            return employeeDao.update(employee);
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t to update employee");
        }
    }

    public void deleteEmployee(long id) throws ServiceException {
        try {
            employeeDao.deleteById(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t delete employee by id: " + id);
        }
    }
}
