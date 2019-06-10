package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.rowMapper.DepartmentRowMapper;
import com.aimprosoft.kmb.rowMapper.RowMapper;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC extends AbstractDaoJDBC<Department> implements DepartmentDao {


    private DepartmentRowMapper departmentRowMapper = new DepartmentRowMapper();

    @Override
    protected final String CREATE() {
        return "INSERT INTO departments(department_name, comments) VALUES (?, ?)";
    }

    @Override
    protected final String GET_BY_ID() {
        return "SELECT * FROM departments WHERE id = ?";
    }

    @Override
    protected String UPDATE() {
        return " SET department_name = ?, comments = ? WHERE id = ?";
    }

    @Override
    protected String ALL() {
        return "SELECT * FROM departments";
    }

    @Override
    protected String DELETE() {
        return "DELETE FROM departments WHERE id = ?";
    }

    private static final String CHECK_ON_EXIST = "SELECT count(id) FROM departments WHERE department_name = ?";


    @Override
    protected long getIdForUpdate(Department department) {
        return department.getId();
    }

    @Override
    protected RowMapper<Department> rowMapper() {
        return departmentRowMapper;
    }

    @Override
    public boolean isExists(Department department) throws RepositoryException {
        String sql = CHECK_ON_EXIST;
        List<Object> params = new ArrayList<>();
        params.add(department.getDepartmentName());
        String log = "Can`t check the existence same name of the department";
        return getJdbcTemplate().isExist(sql, params, log);
    }

    @Override
    protected List<Object> getObjects(Department department) {
        List<Object> params = new ArrayList<>();
        params.add(department.getDepartmentName());
        params.add(department.getComments());
        return params;
    }

}
