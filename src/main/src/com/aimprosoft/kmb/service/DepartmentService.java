package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.database.jdbc.DepartmentDaoJdbc;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.exceptions.ValidationException;
import com.aimprosoft.kmb.validator.DepartmentValidator;
import com.aimprosoft.kmb.validator.ValidationResult;
import com.aimprosoft.kmb.validator.Validator;

import java.util.List;

public class DepartmentService {

    private final DepartmentDao departmentDao = new DepartmentDaoJdbc();
    private final Validator<Department> validator = new DepartmentValidator();
    private final EmployeeService employeeService = new EmployeeService();

    public void create(Department department) throws ServiceException {

        final ValidationResult validationResult = validator.validate(department, "validateName");
        if (validationResult.hasError()) {
            throw new ValidationException(validationResult.getError());
        }
        departmentDao.create(department);
    }

    public Department getById(Long id) throws ServiceException {
        return departmentDao.getById(id);
    }

    public List<Department> getAll() throws ServiceException {
        return departmentDao.getAll();
    }

    public Department update(Department department) throws ServiceException {
        final Department updatableDepartment = departmentDao.getById(department.getId());
        final String updatableName = updatableDepartment.getDepartmentName();

        final ValidationResult validateResult = validator.validate(department, updatableName);
        if (validateResult.hasError()) {
            throw new ValidationException(validateResult.getError());
        }
        return departmentDao.update(department);
    }

    public void deleteById(Long id) throws ServiceException {
        employeeService.deleteAllFromDepartment(id);
        departmentDao.deleteById(id);
    }

}

