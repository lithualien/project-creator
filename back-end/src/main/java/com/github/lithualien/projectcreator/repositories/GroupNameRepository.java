package com.github.lithualien.projectcreator.repositories;

import com.github.lithualien.projectcreator.models.GroupName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupNameRepository extends CrudRepository<GroupName, Long> {

    Optional<GroupName> findByGroupName(String groupName);
    Boolean existsGroupNameByGroupName(String groupName);
    Boolean existsGroupNameById(Long id);

}
