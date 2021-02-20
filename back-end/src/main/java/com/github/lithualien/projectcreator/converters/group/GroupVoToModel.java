package com.github.lithualien.projectcreator.converters.group;

import com.github.lithualien.projectcreator.converters.student.StudentVoToModel;
import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.Student;
import com.github.lithualien.projectcreator.vo.group.GroupStudentVO;
import com.github.lithualien.projectcreator.vo.StudentVO;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupVoToModel implements Converter<GroupStudentVO, Group> {

    @Override
    public Group convert(GroupStudentVO groupStudentVO) {
        Group group = new Group();

        group.setId(groupStudentVO.getId());
        group.setGroupName(groupStudentVO.getGroupName());
        group.setStudents(convertStudentVoSetToModel(groupStudentVO.getStudentVoSet()));

        return group;
    }

    private Set<Student> convertStudentVoSetToModel(Set<StudentVO> studentVOSet) {
        return studentVOSet
                .stream()
                .map(studentVO -> new StudentVoToModel()
                    .convert(studentVO))
                .collect(Collectors.toSet());
    }

}
