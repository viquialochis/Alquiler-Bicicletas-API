package com.tpi.tpi.services;

import java.util.List;

public interface Service <T, C, ID>{
    C add(T entity);
    C update(T entity);
    C delete (ID id);
    C getById(ID id);
    List<C> getAll();

}