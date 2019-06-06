package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.database.jdbc.EmployeeDaoJDBC;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.exceptions.ValidationException;

import java.util.List;

public class EmployeeService implements GenericService<Employee> {

    private final EmployeeDao employeeDao = new EmployeeDaoJDBC();

    @Override
    public void create(Employee employee) throws ServiceException {
        final boolean emailExists = employeeDao.isExists(employee);
        if (!emailExists) {
            employeeDao.create(employee);
        } else {
            throw new ValidationException("Can`t create employee, because this email already used!");
        }
    }

    @Override
    public Employee getById(long id) throws ServiceException {
        return employeeDao.getById(id);
    }

    @Override
    public List<Employee> getAll() throws ServiceException {
        return employeeDao.getAll();
    }

    public List<Employee> getAllEmployeesFromDepartment(long id) throws ServiceException {
        return employeeDao.getAllFromDepartment(id);
    }

    @Override
    public Employee update(Employee employee) throws ServiceException {
        return employeeDao.update(employee);
    }

    @Override
    public void deleteById(long id) throws ServiceException {
        employeeDao.deleteById(id);
    }

    public void deleteAllFromDepartment(long id) throws ServiceException {
        employeeDao.deleteAllFromDepartment(id);
    }

}
