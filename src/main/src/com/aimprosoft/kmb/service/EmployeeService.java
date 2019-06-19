package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.database.daoFactory.EmployeeDaoFactory;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.exceptions.ValidationException;
import com.aimprosoft.kmb.validator.EmployeeValidator;
import com.aimprosoft.kmb.validator.ValidationResult;
import com.aimprosoft.kmb.validator.Validator;

import java.util.List;

public class EmployeeService {

    private final EmployeeDao employeeDao = EmployeeDaoFactory.getDao();
    private final Validator<Employee> validator = new EmployeeValidator();

    public void create(Employee employee) throws ServiceException {

        final ValidationResult validationResult = validator.validate(employee);
        if (validationResult.hasError()) {
            throw new ValidationException(validationResult.getError());
        }
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

        final ValidationResult validationResult = validator.validate(employee);
        if (validationResult.hasError()) {
            throw new ValidationException(validationResult.getError());
        }
        return employeeDao.update(employee);
    }

    public void deleteById(Long id) throws ServiceException {
        employeeDao.deleteById(id);
    }

    public void deleteAllFromDepartment(Long id) throws ServiceException {
        employeeDao.deleteAllFromDepartment(id);
    }

}
