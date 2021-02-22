package com.github.lithualien.projectcreator.converters.project;

import com.github.lithualien.projectcreator.converters.group.GroupStudentVoToModel;
import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.Project;
import com.github.lithualien.projectcreator.vo.group.GroupStudentVO;
import com.github.lithualien.projectcreator.vo.project.ProjectGroupStudentVO;
import com.github.lithualien.projectcreator.vo.project.ProjectVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectGroupStudentVoToModel implements Converter<ProjectGroupStudentVO, Project> {

    @Override
    public Project convert(ProjectGroupStudentVO projectGroupStudentVO) {
        Project project = convertProjectVoToProject(projectGroupStudentVO);
        project.setGroups(convertGroupVoToModel(projectGroupStudentVO.getGroupStudentVoSet()));
        return project;
    }

    private List<Group> convertGroupVoToModel(List<GroupStudentVO> groupStudentVOSet) {
        return groupStudentVOSet
                .stream()
                .map(groupVO -> new GroupStudentVoToModel()
                        .convert(groupVO))
                .collect(Collectors.toList());
    }

    private Project convertProjectVoToProject(ProjectVO projectVO) {
        return new ProjectVoToModel().convert(projectVO);
    }

}
