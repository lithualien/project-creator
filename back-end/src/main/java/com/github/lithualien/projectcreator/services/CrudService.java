package com.github.lithualien.projectcreator.services;

import java.util.Set;

public interface CrudService<S> {

    Set<S> all();
    S findById(Long id);
    S save(S s);
    S update(S s);
    void delete(Long id);

}
