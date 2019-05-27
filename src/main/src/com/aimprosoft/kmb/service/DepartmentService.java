package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.database.DatabaseConnectionManager;
import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.database.jdbc.DepartmentDaoJDBC;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;

import java.sql.Connection;
import java.util.List;

public class DepartmentService {


    private final DepartmentDao departmentDao = new DepartmentDaoJDBC();

    public void createDepartment(Department department) throws ServiceException {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            final boolean departmentExists = departmentDao.isDepartmentExists(connection, department);
            if (!departmentExists) {
                departmentDao.create(connection, department);
                DatabaseConnectionManager.commit(connection);
            }
        } catch (RepositoryException e) {
            DatabaseConnectionManager.rollback(connection);
            throw new ServiceException("Can`t create a new department");
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    public Department getDepartmentById(long id) throws ServiceException {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            return departmentDao.getById(connection, id);
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t get the department by id: " + id);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }


    public List<Department> getAllDepartments() throws ServiceException {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            return departmentDao.getAll(connection);
        } catch (RepositoryException e) {
            throw new ServiceException("Can`t get all the departments");
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    public Department updateDepartment(Department department) throws ServiceException {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            final Department updatedDepartment = departmentDao.update(connection, department);
            DatabaseConnectionManager.commit(connection);
            return updatedDepartment;
        } catch (RepositoryException e) {
            DatabaseConnectionManager.rollback(connection);
            throw new ServiceException("Can`t to update department");
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    public void deleteDepartmentById(long id) throws ServiceException {
        Connection connection = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            departmentDao.deleteById(connection, id);
            DatabaseConnectionManager.commit(connection);
        } catch (RepositoryException e) {
            DatabaseConnectionManager.rollback(connection);
            throw new ServiceException("Can`t delete department by id: " + id);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

}

