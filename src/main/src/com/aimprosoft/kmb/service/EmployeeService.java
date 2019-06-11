package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.database.jdbc.EmployeeDaoJDBC;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ServiceException;

import java.util.List;

public class EmployeeService {

    private final EmployeeDao<Long> employeeDao = new EmployeeDaoJDBC();


    public void create(Employee employee) throws ServiceException {
        employeeDao.create(employee);
    }

    public Employee getById(Long id) throws ServiceException {
        return employeeDao.getById(id);
    }

    public List<Employee> getAll() throws ServiceException {
        return employeeDao.getAll();
    }

    public List<Employee> getAllEmployeesFromDepartment(Long id) throws ServiceException {
        return employeeDao.getAllFromDepartment(id);
    }

    public Employee update(Employee employee) throws ServiceException {
        return employeeDao.update(employee);
    }

    public void deleteById(Long id) throws ServiceException {
        employeeDao.deleteById(id);
    }

    public void deleteAllFromDepartment(Long id) throws ServiceException {
        employeeDao.deleteAllFromDepartment(id);
    }

}
