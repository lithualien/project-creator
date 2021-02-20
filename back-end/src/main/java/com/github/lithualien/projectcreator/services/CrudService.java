package com.github.lithualien.projectcreator.services;

import java.util.Set;

public interface CrudService<T> {

    Set<? extends T> all();
    T findById(Long id);
    T save(T t);
    T update(Long id, T t);
    void delete(Long id);
}
