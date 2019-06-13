package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.GenericDao;
import com.aimprosoft.kmb.domain.Entity;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.database.rowMapper.RowMapper;

import java.util.List;

public abstract class AbstractDaoJDBC<T extends Entity, R> implements GenericDao<T, R> {


    private JdbcTemplate<T, R> jdbcTemplate = new JdbcTemplate<>();

    protected abstract String getQueryForCreate();

    protected abstract String getQueryForGetById();

    protected abstract String getQueryForUpdate();

    protected abstract String getQueryForGetAll();

    protected abstract String getQueryForDeleteById();

    protected abstract long getIdForUpdate(T object);

    protected abstract RowMapper<T> getRowMapper();

    protected abstract List<Object> getObjects(T object);

    public JdbcTemplate<T, R> getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public void create(T object) throws RepositoryException {
        String sql = getQueryForCreate();
        List<Object> params = getObjects(object);
        String log = "Can`t getQueryForCreate";
        jdbcTemplate.create(sql, params, log);
    }

    @Override
    public T getById(R id) throws RepositoryException {
        String sql = getQueryForGetById();
        return jdbcTemplate.getById(sql, id, getRowMapper());
    }

    @Override
    public List<T> getAll() throws RepositoryException {
        String sql = getQueryForGetAll();
        return jdbcTemplate.getAll(sql, getRowMapper());
    }

    @Override
    public T update(T object) throws RepositoryException {
        String sql = getQueryForUpdate();
        List<Object> params = getObjects(object);
        params.add(getIdForUpdate(object));
        String log = "Can`t to getQueryForUpdate";
        return jdbcTemplate.update(sql, params, log);
    }

    @Override
    public void deleteById(R id) throws RepositoryException {
        String sql = getQueryForDeleteById();
        jdbcTemplate.deleteById(sql, id);
    }

}
