package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.GenericDao;
import com.aimprosoft.kmb.database.JdbcTemplate;
import com.aimprosoft.kmb.domain.Entity;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.rowMapper.RowMapper;

import java.util.List;

public abstract class AbstractDaoJDBC<T extends Entity, R> implements GenericDao<T, R> {


    private JdbcTemplate<T, R> jdbcTemplate = new JdbcTemplate<>();

    protected abstract String CREATE();

    protected abstract String GET_BY_ID();

    protected abstract String UPDATE();

    protected abstract String ALL();

    protected abstract String DELETE();

    protected abstract long getIdForUpdate(T object);

    protected abstract RowMapper<T> rowMapper();

    protected abstract List<Object> getObjects(T object);

    public JdbcTemplate<T, R> getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public void create(T object) throws RepositoryException {
        String sql = CREATE();
        List<Object> params = getObjects(object);
        String log = "Can`t create";
        jdbcTemplate.create(sql, params, log);
    }

    @Override
    public T getById(R id) throws RepositoryException {
        String sql = GET_BY_ID();
        return jdbcTemplate.getById(sql, id, rowMapper());
    }

    @Override
    public List<T> getAll() throws RepositoryException {
        String sql = ALL();
        return jdbcTemplate.getAll(sql, rowMapper());
    }

    @Override
    public T update(T object) throws RepositoryException {
        String sql = UPDATE();
        List<Object> params = getObjects(object);
        params.add(getIdForUpdate(object));
        String log = "Can`t to update";
        return jdbcTemplate.update(sql, params, log);
    }

    @Override
    public void deleteById(R id) throws RepositoryException {
        String sql = DELETE();
        jdbcTemplate.deleteById(sql, id);
    }

}
