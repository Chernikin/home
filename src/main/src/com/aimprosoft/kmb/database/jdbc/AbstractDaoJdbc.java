package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.GenericDao;
import com.aimprosoft.kmb.domain.Entity;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.database.rowMapper.RowMapper;

import java.util.List;

public abstract class AbstractDaoJdbc<T extends Entity, Id> implements GenericDao<T, Id> {


    private JdbcTemplate<T, Id> jdbcTemplate = new JdbcTemplate<>();

    @Override
    public void create(T object) throws RepositoryException {
        String sql = getQueryForCreate();
        List<Object> params = getObjects(object);
        String log = "Can`t create.";
        jdbcTemplate.create(sql, params, log);
    }

    @Override
    public T getById(Id id) throws RepositoryException {
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
        String log = "Can`t to update";
        return jdbcTemplate.update(sql, params, log);
    }

    @Override
    public void deleteById(Id id) throws RepositoryException {
        String sql = getQueryForDeleteById();
        jdbcTemplate.deleteById(sql, id);
    }

    protected abstract String getQueryForCreate();

    protected abstract String getQueryForGetById();

    protected abstract String getQueryForUpdate();

    protected abstract String getQueryForGetAll();

    protected abstract String getQueryForDeleteById();

    protected abstract Id getIdForUpdate(T object);

    protected abstract RowMapper<T> getRowMapper();

    protected abstract List<Object> getObjects(T object);

    protected JdbcTemplate<T, Id> getJdbcTemplate() {
        return jdbcTemplate;
    }

}
