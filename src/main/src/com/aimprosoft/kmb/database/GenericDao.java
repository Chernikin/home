package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.exceptions.RepositoryException;

import java.util.List;

public interface GenericDao<T> {

    void create(T object) throws RepositoryException;

    T getById(long id) throws RepositoryException;

    List<T> getAll() throws RepositoryException;

    T update(T object) throws RepositoryException;

    boolean isExists(T object) throws RepositoryException;

    void deleteById(long id) throws RepositoryException;

}
