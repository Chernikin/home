package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.domain.Entity;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.exceptions.ValidationException;

import java.util.List;

public interface GenericDao<T extends Entity, R>{

    void create(T object) throws RepositoryException;

    T getById(R id) throws RepositoryException;

    List<T> getAll() throws RepositoryException;

    T update(T object) throws RepositoryException;

    void deleteById(R id) throws RepositoryException;


}
