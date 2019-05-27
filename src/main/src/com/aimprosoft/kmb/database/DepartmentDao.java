package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.RepositoryException;

import java.sql.Connection;

public interface DepartmentDao extends GenericDao<Department> {

    boolean isDepartmentExists(Connection connection, Department department) throws RepositoryException;
}
