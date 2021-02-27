package com.github.lithualien.projectcreator.converters.group;

import com.github.lithualien.projectcreator.converters.student.StudentModelToVo;
import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.Student;
import com.github.lithualien.projectcreator.vo.group.GroupStudentVO;
import com.github.lithualien.projectcreator.vo.StudentVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupModelToGroupStudentVo implements Converter<Group, GroupStudentVO> {

    @Override
    public GroupStudentVO convert(Group group) {
        GroupStudentVO groupStudentVO = new GroupStudentVO();

        groupStudentVO.setId(group.getId());
        groupStudentVO.setGroupName(group.getGroupName().getGroupName());
        groupStudentVO.setStudentVoSet(convertStudentToVoSet(group.getStudentList()));

        return groupStudentVO;
    }

    private List<StudentVO> convertStudentToVoSet(List<Student> studentSet) {
        if(studentSet != null) {
            return studentSet
                    .stream()
                    .map(student -> new StudentModelToVo()
                            .convert(student))
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }


}
