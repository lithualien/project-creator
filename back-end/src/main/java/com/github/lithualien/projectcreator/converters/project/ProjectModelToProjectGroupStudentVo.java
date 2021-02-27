package com.github.lithualien.projectcreator.converters.project;

import com.github.lithualien.projectcreator.converters.group.GroupModelToGroupStudentVo;
import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.Project;
import com.github.lithualien.projectcreator.vo.group.GroupStudentVO;
import com.github.lithualien.projectcreator.vo.project.ProjectGroupStudentVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.List;
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

    private List<GroupStudentVO> convertGroupModelToVo(List<Group> groupList) {
        if(groupList != null) {
            return groupList
                    .stream()
                    .map(group -> new GroupModelToGroupStudentVo()
                            .convert(group))
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

}
