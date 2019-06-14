package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.RepositoryException;

import java.util.List;

public interface EmployeeDao<Id> extends GenericDao<Employee, Id> {

    List<Employee> getAllFromDepartment(Id id) throws RepositoryException;

    boolean isExists(Employee employee) throws RepositoryException;

    void deleteAllFromDepartment(Id id) throws RepositoryException;
}
