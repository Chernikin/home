package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.RepositoryException;

import java.util.List;

public interface EmployeeDao<R> extends GenericDao<Employee, R> {

    List<Employee> getAllFromDepartment(R id) throws RepositoryException;

    boolean isExists(Employee employee) throws RepositoryException;

    void deleteAllFromDepartment(R id) throws RepositoryException;
}
