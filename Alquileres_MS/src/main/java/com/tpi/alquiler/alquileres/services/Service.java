package com.tpi.alquiler.alquileres.services;
import java.util.List;
public interface Service<T, C, ID> {
    T add(C entity);
    T update(C entity);
    T delete(ID id);
    T getById(ID id);
    List<T> getAll();
}