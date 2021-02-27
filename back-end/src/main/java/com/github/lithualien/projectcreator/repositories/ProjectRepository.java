package com.github.lithualien.projectcreator.repositories;

import com.github.lithualien.projectcreator.models.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Boolean existsProjectByProjectName(String projectName);
    Boolean existsProjectById(Long id);

    @Query(value = "SELECT group_amount FROM projects WHERE id = :id", nativeQuery = true)
    Integer findProjectGroupAmountById(Long id);

    @Query(value = "SELECT student_amount_per_group FROM projects WHERE id = :id", nativeQuery = true)
    Integer findProjectStudentPerGroupById(Long id);

}
