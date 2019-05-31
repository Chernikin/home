package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.GenericDao;
import com.aimprosoft.kmb.database.JdbcTemplate;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.rowMapper.RowMapper;

import java.util.List;

public abstract class AbstractDaoJDBC<T> implements GenericDao<T> {


    protected abstract String getTableName();

    protected abstract String getSqlConditionForGetById();

    protected abstract String getSqlConditionForCreate();

    protected abstract String getSqlConditionForUpdate();

    protected abstract long getIdForUpdate(T object);

    protected abstract RowMapper<T> rowMapper();

    protected abstract List<Object> getObjects(T object);

    private JdbcTemplate<T> jdbcTemplate = new JdbcTemplate<>();

    @Override
    public void create(T object) throws RepositoryException {
        String sql = "INSERT INTO " + getTableName() + getSqlConditionForCreate();
        List<Object> params = getObjects(object);
        String log = "Can`t create";
        jdbcTemplate.create(sql, params, log);
    }

    @Override
    public T getById(long id) throws RepositoryException {
        String sql = "SELECT * FROM " + getTableName() + getSqlConditionForGetById();
        return jdbcTemplate.getById(sql, id, rowMapper());
    }

    @Override
    public List<T> getAll() throws RepositoryException {
        String sql = "SELECT * FROM " + getTableName();
        return jdbcTemplate.getAll(sql, rowMapper());
    }

    @Override
    public T update(T object) throws RepositoryException {
        String sql = "UPDATE " + getTableName() + getSqlConditionForUpdate();
        List<Object> params = getObjects(object);
        params.add(getIdForUpdate(object));
        String log = "Can`t to update";
        return jdbcTemplate.update(sql, params, log);
    }

    @Override
    public void deleteById(long id) throws RepositoryException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id = ?";
        jdbcTemplate.deleteById(sql, id);
    }

}
