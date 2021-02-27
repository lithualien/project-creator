package com.github.lithualien.projectcreator.services;

import com.github.lithualien.projectcreator.models.Student;
import com.github.lithualien.projectcreator.vo.StudentVO;

public interface StudentService extends CrudService<StudentVO>, SearchService<StudentVO> {

    void addStudentToGroup(Long studentId, Long groupId);
    void removeStudentFromGroup(Long studentId, Long groupId);


}
