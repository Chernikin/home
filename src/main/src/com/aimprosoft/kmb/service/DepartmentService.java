package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.database.jdbc.DepartmentDaoJDBC;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.exceptions.ServiceException;

import java.util.List;

public class DepartmentService {


    private final DepartmentDao departmentDao = new DepartmentDaoJDBC();

    public void createDepartment(Department department) throws ServiceException {
        try {
            final boolean departmentExists = departmentDao.isDepartmentExists(department);
            if (!departmentExists) {
                departmentDao.create(department);
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t create a new department");
        }
    }

    public Department getDepartmentById(long id) throws ServiceException {
        try {
            return departmentDao.getById(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t get the department by id: " + id);
        }
    }


    public List<Department> getAllDepartments() throws ServiceException {
        try {
            return departmentDao.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t get all the departments");
        }
    }

    public Department updateDepartment(Department department) throws ServiceException {
        try {
            final Department updatedDepartment = departmentDao.update(department);
            return updatedDepartment;
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t to update department");
        }
    }

    public void deleteDepartmentById(long id) throws ServiceException {
        try {
            departmentDao.deleteById(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t delete department by id: " + id);
        }
    }

}

