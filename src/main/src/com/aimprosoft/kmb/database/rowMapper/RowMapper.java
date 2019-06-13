package com.aimprosoft.kmb.database.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    T extract(ResultSet resultSet) throws SQLException;


}
