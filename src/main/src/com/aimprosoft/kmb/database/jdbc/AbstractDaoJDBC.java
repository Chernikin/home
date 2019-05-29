package com.aimprosoft.kmb.database.jdbc;

import com.aimprosoft.kmb.database.JdbcTemplate;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.rowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDaoJDBC<T> {


    protected abstract String getTableName();

    protected abstract RowMapper<T> getRowMapper(RowMapper<T> rowMapper);


    private JdbcTemplate<T> jdbcTemplate = new JdbcTemplate<>();


    public List<T> getAll() throws RepositoryException {
        String sql = "SELECT * FROM " + getTableName();
        final List<T> allDepartments = jdbcTemplate.getAll(sql, ); {
            @Override
            public T extract (ResultSet resultSet) throws SQLException {
                return null;
            }
        });
        return allDepartments;
    }
}
