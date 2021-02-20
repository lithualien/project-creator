package com.github.lithualien.projectcreator.converters.group;

import com.github.lithualien.projectcreator.converters.student.StudentModelToVo;
import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.Student;
import com.github.lithualien.projectcreator.vo.group.GroupStudentVO;
import com.github.lithualien.projectcreator.vo.StudentVO;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupModelToVo implements Converter<Group, GroupStudentVO> {

    @Override
    public GroupStudentVO convert(Group group) {
        GroupStudentVO groupStudentVO = new GroupStudentVO();

        groupStudentVO.setId(group.getId());
        groupStudentVO.setGroupName(group.getGroupName());
        groupStudentVO.setStudentVoSet(convertStudentToVoSet(group.getStudents()));

        return groupStudentVO;
    }

    private Set<StudentVO> convertStudentToVoSet(Set<Student> studentSet) {
        return studentSet
                .stream()
                .map(student -> new StudentModelToVo()
                        .convert(student))
                .collect(Collectors.toSet());
    }

}
