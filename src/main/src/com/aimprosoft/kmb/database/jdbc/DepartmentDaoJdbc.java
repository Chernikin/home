package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.database.rowMapper.DepartmentRowMapper;
import com.aimprosoft.kmb.database.rowMapper.RowMapper;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJdbc extends AbstractDaoJdbc<Department, Long> implements DepartmentDao {


    private DepartmentRowMapper departmentRowMapper = new DepartmentRowMapper();

    private static final String CHECK_ON_EXIST = "SELECT count(id) FROM departments WHERE department_name = ?";

    @Override
    public boolean isExists(Department department) throws RepositoryException {
        List<Object> params = new ArrayList<>();
        params.add(department.getDepartmentName());
        String log = "Can`t check the existence same name of the department.";
        return getJdbcTemplate().isExist(CHECK_ON_EXIST, params, log);
    }

    @Override
    protected List<Object> getObjects(Department department) {
        List<Object> params = new ArrayList<>();
        params.add(department.getDepartmentName());
        params.add(department.getComments());
        return params;
    }

    @Override
    protected final String getQueryForCreate() {
        return "INSERT INTO departments(department_name, comments) VALUES (?, ?)";
    }

    @Override
    protected final String getQueryForGetById() {
        return "SELECT * FROM departments WHERE id = ?";
    }

    @Override
    protected String getQueryForUpdate() {
        return "UPDATE departments SET department_name = ?, comments = ? WHERE id = ?";
    }

    @Override
    protected String getQueryForGetAll() {
        return "SELECT * FROM departments";
    }

    @Override
    protected String getQueryForDeleteById() {
        return "DELETE FROM departments WHERE id = ?";
    }


    @Override
    protected Long getIdForUpdate(Department department) {
        return department.getId();
    }

    @Override
    protected RowMapper<Department> getRowMapper() {
        return departmentRowMapper;
    }

}
