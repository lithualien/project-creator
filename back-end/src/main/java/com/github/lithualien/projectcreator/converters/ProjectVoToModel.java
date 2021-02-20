package com.github.lithualien.projectcreator.converters;

import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.Project;
import com.github.lithualien.projectcreator.vo.GroupVO;
import com.github.lithualien.projectcreator.vo.ProjectVO;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.stream.Collectors;

public class ProjectVoToModel implements Converter<ProjectVO, Project> {

    @Override
    public Project convert(ProjectVO projectVO) {
        Project project = new Project();

        project.setId(projectVO.getId());
        project.setName(projectVO.getProjectName());
        project.setGroupAmount(projectVO.getGroupAmount());
        project.setGroups(convertGroupVoToModel(projectVO.getGroupVoSet()));

        return project;
    }

    private Set<Group> convertGroupVoToModel(Set<GroupVO> groupVOSet) {
        return groupVOSet
                .stream()
                .map(groupVO -> new GroupVoToModel()
                        .convert(groupVO))
                .collect(Collectors.toSet());
    }

}
