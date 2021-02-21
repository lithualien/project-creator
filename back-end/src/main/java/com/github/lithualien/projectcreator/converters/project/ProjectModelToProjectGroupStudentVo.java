package com.github.lithualien.projectcreator.converters.project;

import com.github.lithualien.projectcreator.converters.group.GroupModelToVo;
import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.Project;
import com.github.lithualien.projectcreator.vo.group.GroupStudentVO;
import com.github.lithualien.projectcreator.vo.project.ProjectGroupStudentVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProjectModelToProjectGroupStudentVo implements Converter<Project, ProjectGroupStudentVO> {

    @Override
    public ProjectGroupStudentVO convert(Project project) {
        ProjectGroupStudentVO projectGroupStudentVO = new ProjectGroupStudentVO();

        projectGroupStudentVO.setId(project.getId());
        projectGroupStudentVO.setProjectName(project.getProjectName());
        projectGroupStudentVO.setGroupStudentVoSet(convertGroupModelToVo(project.getGroups()));
        projectGroupStudentVO.setGroupAmount(project.getGroupAmount());
        projectGroupStudentVO.setStudentsPerGroup(project.getStudentsPerGroup());

        return projectGroupStudentVO;
    }

    private Set<GroupStudentVO> convertGroupModelToVo(Set<Group> groupSet) {
        if(groupSet != null) {
            return groupSet
                    .stream()
                    .map(group -> new GroupModelToVo()
                            .convert(group))
                    .collect(Collectors.toSet());
        } else {
            return null;
        }

    }

}
