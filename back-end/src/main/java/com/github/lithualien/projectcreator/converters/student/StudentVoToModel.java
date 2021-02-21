package com.github.lithualien.projectcreator.converters.student;

import com.github.lithualien.projectcreator.models.Student;
import com.github.lithualien.projectcreator.vo.StudentVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentVoToModel implements Converter<StudentVO, Student> {

    @Override
    public Student convert(StudentVO studentVO) {
        Student student = new Student();
        student.setId(studentVO.getId());
        student.setFirstName(studentVO.getFirstName());
        student.setLastName(studentVO.getLastName());
        return student;
    }

}
