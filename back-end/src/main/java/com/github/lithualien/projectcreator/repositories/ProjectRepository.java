package com.github.lithualien.projectcreator.repositories;

import com.github.lithualien.projectcreator.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
