package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.database.jdbc.DepartmentDaoJDBC;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;

import java.util.List;

public class DepartmentService {


    private final DepartmentDao departmentDao = new DepartmentDaoJDBC();


    public void create(Department department) throws ServiceException {
        departmentDao.create(department);
    }


    public Department getById(long id) throws ServiceException {
        return departmentDao.getById(id);
    }

    public List<Department> getAll() throws ServiceException {
        return departmentDao.getAll();
    }

    public Department update(Department department) throws ServiceException {
        return departmentDao.update(department);
    }

    public void deleteById(long id) throws ServiceException {
        departmentDao.deleteById(id);
    }

}

