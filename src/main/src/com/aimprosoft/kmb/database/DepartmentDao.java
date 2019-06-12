package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.exceptions.ValidationException;

public interface DepartmentDao extends GenericDao<Department, Long> {

    boolean isExists(Department department) throws RepositoryException;
}
