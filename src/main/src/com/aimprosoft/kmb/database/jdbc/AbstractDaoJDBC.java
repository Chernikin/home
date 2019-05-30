package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.JdbcTemplate;
import com.aimprosoft.kmb.database.QueryBuilderSql;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.rowMapper.RowMapper;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDaoJDBC<T> {


    protected abstract String getTableName();

    protected abstract String getFieldName();

    protected abstract Object getField(T object);

    protected String params;

    // protected abstract String getF
    protected abstract RowMapper<T> rowMapper();

    protected abstract List<Object> getObjects(T object);

    private JdbcTemplate<T> jdbcTemplate = new JdbcTemplate<>();

    private QueryBuilderSql queryBuilderSql = new QueryBuilderSql();

    public List<T> getAll() throws RepositoryException {
        String sql = "SELECT * FROM " + getTableName();
        return jdbcTemplate.getAll(sql, rowMapper());
    }

    public void deleteById(long id) throws RepositoryException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id = ?";
        jdbcTemplate.deleteById(sql, id);
    }

    public boolean isExists(T object) throws RepositoryException {
        String sql = "SELECT count(id) FROM " + getTableName() + " WHERE " + getFieldName() + " = ?";
        List<Object> params = new ArrayList<>();
        params.add(getField(object));
        String log = "Can`t check the existence same" + getFieldName();
        return jdbcTemplate.isExist(sql, params, log);
    }


    /*public void create(T object) throws RepositoryException {
        String sql = queryBuilderSql.insert() + getTableName() + queryBuilderSql.getSqlParams();
        List<Object> params = getObjects(object);
        String log = "Can`t create";
        jdbcTemplate.create(sql, params, log);
    }*/

   /* public Object update(T object) throws RepositoryException {
        String sql = queryBuilderSql.update() + getTableName() + queryBuilderSql.params();
        List<Object> params = getObjects(object);
        params.add(object.getId());
        String log = "Can`t to update";
        return jdbcTemplate.update(sql, params, log);
    }*/

}
