package com.github.lithualien.projectcreator.repositories;

import com.github.lithualien.projectcreator.models.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

    Iterable<Group> findAllByProjectId(Long projectId);

    @Query(value = "SELECT * FROM project_groups WHERE project_id = :projectId order by id desc limit 1",
            nativeQuery = true)
    Group findLastGroup(Long projectId);

}
