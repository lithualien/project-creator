package com.github.lithualien.projectcreator.converters;

import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.Project;
import com.github.lithualien.projectcreator.vo.GroupVO;
import com.github.lithualien.projectcreator.vo.ProjectVO;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.stream.Collectors;

public class ProjectModelToVo implements Converter<Project, ProjectVO> {

    @Override
    public ProjectVO convert(Project project) {
        ProjectVO projectVO = new ProjectVO();

        projectVO.setId(project.getId());
        projectVO.setProjectName(project.getName());
        projectVO.setGroupAmount(project.getGroupAmount());
        projectVO.setGroupVoSet(convertGroupModelToVo(project.getGroups()));

        return projectVO;
    }

    private Set<GroupVO> convertGroupModelToVo(Set<Group> groupSet) {
        return groupSet
                .stream()
                .map(group -> new GroupModelToVo()
                        .convert(group))
                .collect(Collectors.toSet());
    }

}
