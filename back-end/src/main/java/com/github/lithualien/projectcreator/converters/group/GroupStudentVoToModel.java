package com.github.lithualien.projectcreator.converters.group;

import com.github.lithualien.projectcreator.converters.student.StudentVoToModel;
import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.Student;
import com.github.lithualien.projectcreator.vo.group.GroupStudentVO;
import com.github.lithualien.projectcreator.vo.StudentVO;
import com.github.lithualien.projectcreator.vo.group.GroupVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupStudentVoToModel implements Converter<GroupStudentVO, Group> {

    @Override
    public Group convert(GroupStudentVO groupStudentVO) {
        Group group = convertGroupVoToModel(groupStudentVO);
        group.setStudentList(convertStudentVoSetToModel(groupStudentVO.getStudentVoSet()));
        return group;
    }

    private List<Student> convertStudentVoSetToModel(List<StudentVO> studentVOSet) {
        return studentVOSet
                .stream()
                .map(studentVO -> new StudentVoToModel()
                    .convert(studentVO))
                .collect(Collectors.toList());
    }

    private Group convertGroupVoToModel(GroupVO groupVO) {
        return new GroupVoToModel().convert(groupVO);
    }

}
