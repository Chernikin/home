package com.aimprosoft.kmb.database.rowMapper;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.domain.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee extract(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setEmail(resultSet.getString("email"));
        employee.setAge(resultSet.getInt("age"));
        employee.setPhoneNumber(resultSet.getString("phone_number"));
        employee.setEmploymentDate(resultSet.getDate("employment_date"));
        final Department department = new Department();
        department.setId(resultSet.getLong("department_id"));
        department.setDepartmentName(resultSet.getString("department_name"));
        department.setComments(resultSet.getString("comments"));
        employee.setDepartment(department);
        return employee;
    }
}
