package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.RepositoryException;

import java.util.List;

public interface EmployeeDao extends GenericDao<Employee, Long> {

    List<Employee> getAllFromDepartment(Long id) throws RepositoryException;

    boolean isExists(Employee employee) throws RepositoryException;

    void deleteAllFromDepartment(Long id) throws RepositoryException;

    Employee getByEmail(String email) throws RepositoryException;
}
