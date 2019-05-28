package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.RepositoryException;

import java.util.List;

public interface EmployeeDao extends GenericDao<Employee> {

    List<Employee> getAllFromDepartment(long id) throws RepositoryException;

    boolean isEmailExists(Employee employee) throws RepositoryException;
}
