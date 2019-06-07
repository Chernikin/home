package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.database.jdbc.DepartmentDaoJDBC;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.exceptions.ValidationException;

import java.util.List;

public class DepartmentService implements GenericService<Department> {


    private final DepartmentDao departmentDao = new DepartmentDaoJDBC();


    @Override
    public void create(Department department) throws ServiceException {
      /*  final boolean departmentExists = departmentDao.isExists(department);
        if (!departmentExists) {*/
            departmentDao.create(department);
       /* } else {
            throw new ValidationException("Can`t create department, because this department name already used!");
        }*/
    }


    @Override
    public Department getById(long id) throws ServiceException {
        return departmentDao.getById(id);
    }

    @Override
    public List<Department> getAll() throws ServiceException {
        return departmentDao.getAll();
    }

    @Override
    public Department update(Department department) throws ServiceException {
        return departmentDao.update(department);
    }

    @Override
    public void deleteById(long id) throws ServiceException {
        departmentDao.deleteById(id);
    }

}

