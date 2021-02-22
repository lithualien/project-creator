package com.github.lithualien.projectcreator.services;

import java.util.List;

public interface CrudService<T> {

    List<? extends T> all();
    T findById(Long id);
    T save(T t);
    T update(Long id, T t);
    void delete(Long id);
}
