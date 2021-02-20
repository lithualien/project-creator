package com.github.lithualien.projectcreator.converters;

import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.Student;
import com.github.lithualien.projectcreator.vo.GroupVO;
import com.github.lithualien.projectcreator.vo.StudentVO;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupModelToVo implements Converter<Group, GroupVO> {

    @Override
    public GroupVO convert(Group group) {
        GroupVO groupVO = new GroupVO();

        groupVO.setId(group.getId());
        groupVO.setGroupName(group.getGroupName());
        groupVO.setStudentVoSet(convertStudentToVoSet(group.getStudents()));

        return groupVO;
    }

    private Set<StudentVO> convertStudentToVoSet(Set<Student> studentSet) {
        return studentSet
                .stream()
                .map(student -> new StudentModelToVo()
                        .convert(student))
                .collect(Collectors.toSet());
    }

}
