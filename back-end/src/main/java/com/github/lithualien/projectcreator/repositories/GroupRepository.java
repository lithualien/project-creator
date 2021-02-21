package com.github.lithualien.projectcreator.repositories;

import com.github.lithualien.projectcreator.models.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {



}
