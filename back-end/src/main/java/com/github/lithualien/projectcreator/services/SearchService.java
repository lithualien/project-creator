package com.github.lithualien.projectcreator.services;

import java.util.List;

public interface SearchService<T> {

    List<? extends T> all();
    T findById(Long id);

}
