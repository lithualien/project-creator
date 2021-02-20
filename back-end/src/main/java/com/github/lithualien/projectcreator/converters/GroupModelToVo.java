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

public class GroupModelToVo implements Converter<Group, GroupVO> {

    @Override
    public GroupVO convert(Group group) {
        GroupVO groupVO = new GroupVO();

        groupVO.setId(group.getId());
        groupVO.setGroupName(convertGroupNameToVO(group.getGroupName()));
        groupVO.setStudentAmount(group.getStudentAmount());
        groupVO.setStudentVoSet(convertStudentToVoSet(group.getStudents()));

        return groupVO;
    }

    private GroupNameVO convertGroupNameToVO(GroupName groupName) {
        return new GroupNameModelToVo().convert(groupName);
    }

    private Set<StudentVO> convertStudentToVoSet(Set<Student> studentSet) {
        return studentSet
                .stream()
                .map(student -> new StudentModelToVo()
                        .convert(student))
                .collect(Collectors.toSet());
    }

}
