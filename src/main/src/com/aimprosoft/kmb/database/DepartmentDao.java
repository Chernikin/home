package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.RepositoryException;

public interface DepartmentDao extends GenericDao<Department> {

    boolean isExists(Department department) throws RepositoryException;
}
