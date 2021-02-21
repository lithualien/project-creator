package com.github.lithualien.projectcreator.services.impl;

import com.github.lithualien.projectcreator.exceptions.ResourceAlreadyExistsException;
import com.github.lithualien.projectcreator.exceptions.ResourceNotFoundException;
import com.github.lithualien.projectcreator.models.Project;
import com.github.lithualien.projectcreator.repositories.ProjectRepository;
import com.github.lithualien.projectcreator.services.ProjectService;
import com.github.lithualien.projectcreator.vo.project.ProjectGroupStudentVO;
import com.github.lithualien.projectcreator.vo.project.ProjectVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final Converter<Project, ProjectGroupStudentVO> modelToVo;
    private final Converter<ProjectVO, Project> projectVoToModel;

    public ProjectServiceImpl(ProjectRepository projectRepository, Converter<Project, ProjectGroupStudentVO> modelToVo,
                              Converter<ProjectVO, Project> projectVoToModel) {
        this.projectRepository = projectRepository;
        this.modelToVo = modelToVo;
        this.projectVoToModel = projectVoToModel;
    }

    @Override
    public Set<ProjectGroupStudentVO> all() {
        Set<Project> projectGroupStudentVO = getProjectSet(projectRepository.findAll());
        return convertModelToVo(projectGroupStudentVO);
    }

    @Override
    public ProjectGroupStudentVO findById(Long id) {
        return modelToVo.convert(getProjectById(id));
    }

    @Override
    public ProjectVO save(ProjectVO projectVO) {
        checkIfProjectExists(projectVO.getProjectName());
        return saveOrUpdate(null, projectVO);
    }

    @Override
    public ProjectVO update(Long id, ProjectVO projectVO) {
        if(!projectRepository.existsProjectById(id)) {
            throw new ResourceNotFoundException("Project with id = " + id + " was not found!");
        }
        return saveOrUpdate(id, projectVO);
    }

    @Override
    public void delete(Long id) {
        Project project = getProjectById(id);
        projectRepository.delete(project);
    }

    private Set<Project> getProjectSet(Iterable<Project> projectIterator) {
        return StreamSupport
                .stream(projectIterator.spliterator(), false)
                .collect(Collectors.toSet());
    }

    private Set<ProjectGroupStudentVO> convertModelToVo(Set<Project> projectSet) {
        return projectSet
                .stream()
                .map(modelToVo::convert)
                .collect(Collectors.toSet());
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

    private ProjectVO saveOrUpdate(Long id, ProjectVO projectVO) {
        projectVO.setId(id);
        Project project = projectVoToModel.convert(projectVO);
        if(project != null) {
            Project updatedProject = projectRepository.save(project);
            return modelToVo.convert(updatedProject);
        } else {
            log.error(getClass() + ", saveOrUpdate() method, project after conversion was null.");
            return null;
        }
    }

}
