package com.github.lithualien.projectcreator.converters.project;

import com.github.lithualien.projectcreator.models.Project;
import com.github.lithualien.projectcreator.vo.project.ProjectVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProjectModelToVo implements Converter<Project, ProjectVO> {

    @Override
    public ProjectVO convert(Project project) {

        ProjectVO projectVO = new ProjectVO();
        projectVO.setId(project.getId());
        projectVO.setProjectName(project.getProjectName());
        projectVO.setGroupAmount(project.getGroupAmount());
        projectVO.setStudentsPerGroup(project.getStudentsPerGroup());
        return projectVO;

    }
}
