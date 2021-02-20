package com.github.lithualien.projectcreator.converters.student;

import com.github.lithualien.projectcreator.models.Student;
import com.github.lithualien.projectcreator.vo.StudentVO;
import org.springframework.core.convert.converter.Converter;

public class StudentModelToVo implements Converter<Student, StudentVO> {

    @Override
    public StudentVO convert(Student student) {
        StudentVO studentVO = new StudentVO();
        studentVO.setId(student.getId());
        studentVO.setFirstName(student.getFirstName());
        studentVO.setLastName(student.getLastName());
        return studentVO;
    }

}
