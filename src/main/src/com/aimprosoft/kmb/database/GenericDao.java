package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.domain.Entity;
import com.aimprosoft.kmb.exceptions.RepositoryException;

import java.util.List;

public interface GenericDao<T extends Entity, Id> {

    void create(T object) throws RepositoryException;

    T getById(Id id) throws RepositoryException;

    List<T> getAll() throws RepositoryException;

    T update(T object) throws RepositoryException;

    void deleteById(Id id) throws RepositoryException;
}
