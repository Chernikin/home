package com.aimprosoft.kmb.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    T extract(ResultSet resultSet) throws SQLException;


}
