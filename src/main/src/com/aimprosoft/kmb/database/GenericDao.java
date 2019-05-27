package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.exceptions.RepositoryException;

import java.sql.Connection;
import java.util.List;

public interface GenericDao<T> {

    long create(Connection connection, T object) throws RepositoryException;

    T getById(Connection connection, long id) throws RepositoryException;

    List<T> getAll(Connection connection) throws RepositoryException;

    T update(Connection connection, T object) throws RepositoryException;

    void deleteById(Connection connection, long id) throws RepositoryException;

}
