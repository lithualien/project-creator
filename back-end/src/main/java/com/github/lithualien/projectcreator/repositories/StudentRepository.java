package com.github.lithualien.projectcreator.repositories;

import com.github.lithualien.projectcreator.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    Optional<Student> findStudentByFirstNameAndLastName(String firstName, String lastName);
    Boolean existsStudentByFirstNameAndLastName(String firstname, String lastName);
    Boolean existsStudentById(Long id);

    @Query(value = "SELECT IF ( EXISTS( SELECT * FROM project_group_students " +
            "WHERE project_group_id = :groupId AND student_id = :studentId), 'true', 'false');", nativeQuery = true)
    Boolean existsStudentInProject(Long groupId, Long studentId);

}
