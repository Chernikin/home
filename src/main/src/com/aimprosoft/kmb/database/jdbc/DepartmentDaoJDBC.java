package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.database.JdbcTemplate;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.rowMapper.DepartmentRowMapper;
import com.aimprosoft.kmb.rowMapper.RowMapper;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC extends AbstractDaoJDBC<Department> implements DepartmentDao {


    private JdbcTemplate<Department> jdbcTemplate = new JdbcTemplate<>();
    private DepartmentRowMapper departmentRowMapper = new DepartmentRowMapper();

    @Override
    protected String getTableName() {
        return "departments";
    }

    @Override
    protected String getSqlConditionForGetById() {
        return " WHERE id = ?";
    }

    @Override
    protected String getSqlConditionForCreate() {
        return "(department_name, comments) VALUES (?, ?)";
    }

    @Override
    protected String getSqlConditionForUpdate() {
        return " SET department_name = ?, comments = ? WHERE id = ?";
    }


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
        String sql = "SELECT count(id) FROM departments WHERE department_name = ?";
        List<Object> params = new ArrayList<>();
        params.add(department.getDepartmentName());
        String log = "Can`t check the existence same name of the department";
        return jdbcTemplate.isExist(sql, params, log);
    }

    @Override
    protected List<Object> getObjects(Department department) {
        List<Object> params = new ArrayList<>();
        params.add(department.getDepartmentName());
        params.add(department.getComments());
        return params;
    }





    /*@Override
    public void create(Department department) throws RepositoryException {
        queryBuilderSql.setSqlParams(DEPARTMENT_PARAMS);
        super.create(department);
    }*/

/*
    @Override
    public void create(Department department) throws RepositoryException {
        String sql = "INSERT INTO departments(department_name, comments) VALUES (?, ?)";
        List<Object> params = getObjects(department);
        String log = "Can`t create department";
        jdbcTemplate.create(sql, params, log);
    }*/

    /*@Override
    public Department getById(long id) throws RepositoryException {

        String sql = "SELECT * FROM departments WHERE id = ?";
        final Department department = jdbcTemplate.getById(sql, id, departmentRowMapper);
        return department;
    }*/

/*
    @Override
    public List<Department> getAll() throws RepositoryException {
        return super.getAll();
    }*/

   /* @Override
    public Department update(Department department) throws RepositoryException {
        String sql = "UPDATE departments SET department_name = ?, comments = ? WHERE id = ?";
        List<Object> params = getObjects(department);
        params.add(department.getId());
        String log = "Can`t to update department";
        return jdbcTemplate.update(sql, params, log);
    }*/

  /*  @Override
    public void deleteById(long id) throws RepositoryException {
        super.deleteById(id);
    }*/

   /* @Override
    public void deleteById(long id) throws RepositoryException {
        String sql = "DELETE FROM departments WHERE id = ?";
        jdbcTemplate.deleteById(sql, id);
    }*/
}
