package com.github.lithualien.projectcreator.converters;

import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.GroupName;
import com.github.lithualien.projectcreator.models.Student;
import com.github.lithualien.projectcreator.vo.GroupNameVO;
import com.github.lithualien.projectcreator.vo.GroupVO;
import com.github.lithualien.projectcreator.vo.StudentVO;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupVoToModel implements Converter<GroupVO, Group> {

    @Override
    public Group convert(GroupVO groupVO) {
        Group group = new Group();

        group.setId(groupVO.getId());
        group.setStudentAmount(groupVO.getStudentAmount());
        group.setGroupName(convertGroupNameVoToModel(groupVO.getGroupName()));
        group.setStudents(convertStudentVoSetToModel(groupVO.getStudentVoSet()));

        return group;
    }

    private GroupName convertGroupNameVoToModel(GroupNameVO groupNameVO) {
        return new GroupNameVoToModel().convert(groupNameVO);
    }

    private Set<Student> convertStudentVoSetToModel(Set<StudentVO> studentVOSet) {
        return studentVOSet
                .stream()
                .map(studentVO -> new StudentVoToModel()
                    .convert(studentVO))
                .collect(Collectors.toSet());
    }

}
