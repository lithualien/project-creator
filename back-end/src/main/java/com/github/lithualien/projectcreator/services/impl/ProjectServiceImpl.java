package com.github.lithualien.projectcreator.services.impl;

import com.github.lithualien.projectcreator.converters.ProjectModelToVo;
import com.github.lithualien.projectcreator.converters.ProjectVoToModel;
import com.github.lithualien.projectcreator.models.Project;
import com.github.lithualien.projectcreator.repositories.ProjectRepository;
import com.github.lithualien.projectcreator.services.ProjectService;
import com.github.lithualien.projectcreator.vo.ProjectVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final Converter<Project, ProjectVO> modelToVo;
    private final Converter<ProjectVO, Project> voToModel;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        this.modelToVo = new ProjectModelToVo();
        this.voToModel = new ProjectVoToModel();
    }

    @Override
    public Set<ProjectVO> all() {
        Set<Project> projectSet = getProjectSet(projectRepository.findAll());
        return convertModelToVo(projectSet);
    }

    @Override
    public ProjectVO findById(Long id) {
        return null;
    }

    @Override
    public ProjectVO save(ProjectVO projectVO) {
        return null;
    }

    @Override
    public ProjectVO update(ProjectVO projectVO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private Set<Project> getProjectSet(Iterable<Project> projectIterator) {
        return StreamSupport
                .stream(projectIterator.spliterator(), false)
                .collect(Collectors.toSet());
    }

    private Set<ProjectVO> convertModelToVo(Set<Project> projectSet) {
        return projectSet
                .stream()
                .map(modelToVo::convert)
                .collect(Collectors.toSet());
    }

}
