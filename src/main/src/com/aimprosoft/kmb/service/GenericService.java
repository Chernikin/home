package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.exceptions.ServiceException;

import java.util.List;

public interface GenericService<T> {

    void create(T object) throws ServiceException;

    T getById(long id) throws  ServiceException;

    List<T> getAll() throws ServiceException;

    T update(T object) throws ServiceException;

    void deleteById(long id) throws ServiceException;

}
