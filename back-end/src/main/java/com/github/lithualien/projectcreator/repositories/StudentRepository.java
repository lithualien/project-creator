package com.github.lithualien.projectcreator.repositories;

import com.github.lithualien.projectcreator.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    Boolean existsStudentByFirstNameAndLastName(String firstname, String lastName);
    Boolean existsStudentById(Long id);

}
