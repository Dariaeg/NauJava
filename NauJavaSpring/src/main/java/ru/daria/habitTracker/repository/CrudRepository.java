package ru.daria.habitTracker.repository;

import java.util.ArrayList;

public interface CrudRepository<T, ID> {
    void create(T entity);
    T read(ID id);
    void update(T entity);
    void delete(ID id);
    ArrayList<T> findAll();
}