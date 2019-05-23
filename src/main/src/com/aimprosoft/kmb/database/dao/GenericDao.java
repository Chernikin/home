package com.aimprosoft.kmb.database.dao;

import java.sql.Connection;
import java.util.List;

public interface GenericDao<T> {

    long create(Connection connection, T object);

    T getById(Connection connection, long id);

    List<T> getAll(Connection connection);

    T update(Connection connection, T object);

    void deleteById(Connection connection, long id);

}
