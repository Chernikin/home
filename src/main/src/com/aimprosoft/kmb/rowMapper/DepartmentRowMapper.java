package com.aimprosoft.kmb.rowMapper;

import com.aimprosoft.kmb.domain.Department;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRowMapper implements RowMapper<Department> {

    @Override
    public Department extract(ResultSet resultSet) throws SQLException {
        final Department department = new Department();
        department.setId(resultSet.getLong("id"));
        department.setDepartmentName(resultSet.getString("department_name"));
        department.setComments(resultSet.getString("comments"));
        return department;
    }
}
