package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.RepositoryException;

import java.sql.Connection;
import java.util.List;

public interface EmployeeDao extends GenericDao<Employee> {

    List<Employee> getAllFromDepartment(Connection connection, long id) throws RepositoryException;

    boolean isEmailExists(Connection connection, Employee employee) throws RepositoryException;
}
