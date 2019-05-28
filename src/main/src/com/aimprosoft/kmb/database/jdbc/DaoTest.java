package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.RepositoryException;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DaoTest extends Dao<Department> {

    @Override
    public long create(Department object, String sql) throws RepositoryException {
        return super.create(object, sql);



    }

    private void prepareStatementForCreate(Department department, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, department.getDepartmentName());
    }
}
