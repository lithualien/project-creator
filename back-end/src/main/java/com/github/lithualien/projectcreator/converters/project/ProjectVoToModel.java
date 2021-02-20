package com.github.lithualien.projectcreator.converters.project;

import com.github.lithualien.projectcreator.models.Project;
import com.github.lithualien.projectcreator.vo.project.ProjectVO;
import org.springframework.core.convert.converter.Converter;

public class ProjectVoToModel implements Converter<ProjectVO, Project> {

    @Override
    public Project convert(ProjectVO projectVO) {
        Project project = new Project();

        project.setId(projectVO.getId());
        project.setProjectName(projectVO.getProjectName());
        project.setGroupAmount(projectVO.getGroupAmount());
        project.setStudentsPerGroup(projectVO.getStudentsPerGroup());
        project.setGroups(null);

        return project;
    }

}
