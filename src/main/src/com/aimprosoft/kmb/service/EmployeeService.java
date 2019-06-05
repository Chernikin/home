package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.database.jdbc.EmployeeDaoJDBC;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.exceptions.ValidationException;

import javax.servlet.ServletException;
import java.util.List;

public class EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDaoJDBC();

    public void createEmployee(Employee employee) throws ServiceException {
            final boolean emailExists = employeeDao.isExists(employee);
            if (!emailExists) {
                employeeDao.create(employee);
            } else {
                throw new ValidationException("Can`t create employee, because this email already used!");
            }
        }



    public Employee getEmployeeById(long id) throws ServiceException {
            return employeeDao.getById(id);
    }

    public List<Employee> getAllEmployees() throws ServiceException {
            return employeeDao.getAll();
    }

    public List<Employee> getAllEmployeesFromDepartment(long id) throws ServiceException {
            return employeeDao.getAllFromDepartment(id);
    }


    public Employee updateEmployee(Employee employee) throws ServiceException {
            return employeeDao.update(employee);
    }

    public void deleteEmployee(long id) throws ServiceException {
            employeeDao.deleteById(id);
    }

    public void deleteAllFromDepartment(long id) throws ServletException {
        try {
            employeeDao.deleteAllFromDepartment(id);
        } catch (RepositoryException e) {
            throw new ServletException("Can`t delete all employees from department with id: " + id);
        }
    }

}
