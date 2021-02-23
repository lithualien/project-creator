package com.github.lithualien.projectcreator.services.impl;

import com.github.lithualien.projectcreator.exceptions.ResourceAlreadyExistsException;
import com.github.lithualien.projectcreator.exceptions.ResourceNotFoundException;
import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.Project;
import com.github.lithualien.projectcreator.repositories.ProjectRepository;
import com.github.lithualien.projectcreator.services.GroupService;
import com.github.lithualien.projectcreator.services.ProjectService;
import com.github.lithualien.projectcreator.vo.project.ProjectGroupStudentVO;
import com.github.lithualien.projectcreator.vo.project.ProjectVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final Converter<Project, ProjectGroupStudentVO> modelToVo;
    private final Converter<ProjectVO, Project> projectVoToModel;
    private final GroupService groupService;

    public ProjectServiceImpl(ProjectRepository projectRepository, Converter<Project, ProjectGroupStudentVO> modelToVo,
                              Converter<ProjectVO, Project> projectVoToModel, GroupService groupService) {
        this.projectRepository = projectRepository;
        this.modelToVo = modelToVo;
        this.projectVoToModel = projectVoToModel;
        this.groupService = groupService;
    }

    @Override
    public List<ProjectGroupStudentVO> all() {
        List<Project> projectGroupStudentVO = getProjectList(projectRepository.findAll());
        return convertModelToVo(projectGroupStudentVO);
    }

    @Override
    public ProjectGroupStudentVO findById(Long id) {
        return modelToVo.convert(getProjectById(id));
    }

    @Transactional
    @Override
    public ProjectGroupStudentVO save(ProjectVO projectVO) {
        checkIfProjectExists(projectVO.getProjectName());
        projectVO.setId(null);

        Project project = projectVoToModel.convert(projectVO);
        Project savedProject = saveOrUpdate(project);

        if(savedProject != null) {
            savedProject.setGroups(groupService.initializeGroupList(savedProject));
            return modelToVo.convert(savedProject);
        }
        return null;
    }

    @Transactional
    @Override
    public ProjectVO update(Long id, ProjectVO projectVO) {
        checkIfProjectExists(id);
        projectVO.setId(id);
        Project oldProject = getProjectById(id);
        Project project = projectVoToModel.convert(projectVO);
        List<Group> groups = groupService.createOrDeleteGroups(oldProject, projectVO.getGroupAmount());
        groupService.resetStudentsOnGroup(groups, oldProject.getStudentsPerGroup(),
                projectVO.getStudentsPerGroup());

        if(project != null) {
            Project updatedProject = saveOrUpdate(project);
            updatedProject.setGroups(groups);
            return modelToVo.convert(updatedProject);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Project project = getProjectById(id);
        projectRepository.delete(project);
    }

    private List<Project> getProjectList(Iterable<Project> projectIterator) {
        return StreamSupport
                .stream(projectIterator.spliterator(), false)
                .collect(Collectors.toList());
    }

    private List<ProjectGroupStudentVO> convertModelToVo(List<Project> projectList) {
        return projectList
                .stream()
                .map(modelToVo::convert)
                .collect(Collectors.toList());
    }

    private Project getProjectById(Long id) {
        return projectRepository
                .findById(id)
                .<ResourceNotFoundException>orElseThrow(() -> {
                    log.error("Project with id = " + id + " was not found!");
                    throw new ResourceNotFoundException("Project with id = " + id + " was not found!");
                });
    }

    private void checkIfProjectExists(String projectName) {
        if(projectRepository.existsProjectByProjectName(projectName)) {
            log.error("Project with name " + projectName + " already exists.");
            throw new ResourceAlreadyExistsException("Project with name '" + projectName + "' already exists.");
        }
    }

    private void checkIfProjectExists(Long id) {
        if(!projectRepository.existsProjectById(id)) {
            throw new ResourceNotFoundException("Project with id = " + id + " was not found!");
        }
    }

    private Project saveOrUpdate(Project project) {
        if(project != null) {
            return projectRepository.save(project);
        } else {
            log.error(getClass() + ", saveOrUpdate() method, project after conversion was null.");
            return null;
        }
    }

}
