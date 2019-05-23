package kmb.service;

import kmb.database.DatabaseConnectionManager;
import kmb.database.dao.DepartmentDao;
import kmb.domain.Department;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DepartmentService {

    private static Logger logger = Logger.getLogger(DepartmentService.class);
    private final DepartmentDao departmentDao = new DepartmentDao();

    public long createDepartment(Department department) {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            final long departmentId = departmentDao.create(connection, department);
            DatabaseConnectionManager.commit(connection);
            return departmentId;
        } catch (SQLException e) {
            logger.error("Can`t create a new department", e);
            DatabaseConnectionManager.rollback(connection);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return -1;
    }

    public Department getDepartmentById(long id) {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();
            return departmentDao.getById(connection, id);
        } catch (SQLException e) {
            logger.error("Can`t get department by id: " + id, e);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return null;
    }


    public List<Department> getAllDepartments() {
        Connection connection = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            return departmentDao.getAll(connection);
        } catch (SQLException e) {
            logger.error("Can`t get all departments");
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return null;
    }

    public Department updateDepartment(Department department) {
        Connection connection = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            final Department updatedDepartment = departmentDao.update(connection, department);
            DatabaseConnectionManager.commit(connection);
            return updatedDepartment;
        } catch (SQLException e) {
            logger.error("Can`t update department", e);
            DatabaseConnectionManager.rollback(connection);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
        return department;
    }

    public void deleteDepartmentById(long id) {
        Connection connection = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            departmentDao.deleteById(connection, id);
            DatabaseConnectionManager.commit(connection);
        } catch (SQLException e) {
            logger.error("Can`t delete department by id: " + id, e);
            DatabaseConnectionManager.rollback(connection);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }
}

