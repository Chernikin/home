package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ServiceException;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class EmployeeServiceTest {


    @Ignore
    @Test
    public void returnEmployeeIdIfEmployeeSavedInData() throws ServiceException {
        final EmployeeService employeeService = new EmployeeService();
        final Employee employee = new Employee();
        final DepartmentService departmentService = new DepartmentService();
        final Department departmentById = departmentService.getById(9L);
        employee.setFirstName("TestNewItems firstname");
        employee.setLastName("TestNewItems lastname");
        employee.setEmail("Testemail@gmail.com");
        employee.setAge(33);
        employee.setPhoneNumber("+380995695656");
        employee.setEmploymentDate(new Date(2000 - 5 - 12));
        employee.setDepartment(departmentById);
        employeeService.create(employee);
            }

    @Ignore
    @Test
    public void returnEmployeeIfEmployeeUpdated() throws ServiceException {

        final EmployeeService employeeService = new EmployeeService();
        final Employee employeeById = employeeService.getById(6L);
        final DepartmentService departmentService = new DepartmentService();
        final Department departmentById = departmentService.getById(1L);
        employeeById.setFirstName("NEW NEW UPDATED");
        employeeById.setLastName("TestNewItems lastname");
        employeeById.setEmail("Testemail@gmail.com");
        employeeById.setAge(33);
        employeeById.setPhoneNumber("+380995695656");
        employeeById.setEmploymentDate(new Date(2010 - 6 - 20));
        employeeById.setDepartment(departmentById);
        final Employee employee = employeeService.update(employeeById);
        assertNotNull(employee);
    }

    @Ignore
    @Test
    public void returnNullIfEmployeeDeleteFromData() throws ServiceException {
        final EmployeeService employeeService = new EmployeeService();
        employeeService.deleteById(7L);
    }

}
