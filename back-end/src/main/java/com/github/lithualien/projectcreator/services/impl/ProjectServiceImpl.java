package com.github.lithualien.projectcreator.services.impl;

import com.github.lithualien.projectcreator.exceptions.ResourceAlreadyExistsException;
import com.github.lithualien.projectcreator.exceptions.ResourceIllogicalAmountException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Log4j2
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final GroupService groupService;
    private final Converter<Project, ProjectGroupStudentVO> modelToVo;
    private final Converter<ProjectVO, Project> projectVoToModel;

    public ProjectServiceImpl(ProjectRepository projectRepository, GroupService groupService, Converter<Project, ProjectGroupStudentVO> modelToVo,
                              Converter<ProjectVO, Project> projectVoToModel) {
        this.projectRepository = projectRepository;
        this.groupService = groupService;
        this.modelToVo = modelToVo;
        this.projectVoToModel = projectVoToModel;
    }

    @Override
    public List<? extends ProjectVO> all() {
        return convertModelToVo(getProjectList(projectRepository.findAll()));
    }

    @Override
    public ProjectVO findById(Long id) {
        return modelToVo.convert(getProjectById(id));
    }

    @Override
    public ProjectVO save(ProjectVO projectVO) {
        checkIfProjectExists(projectVO.getProjectName());
        checkGroupAmount(projectVO.getGroupAmount());
        checkStudentsPerGroupAmount(projectVO.getStudentsPerGroup());

        if(isConversionToModelNull(projectVO)) {
            Project project = saveOrUpdate(projectVoToModel.convert(projectVO), null);
            project.setGroups(getGroupList(project));
            return modelToVo.convert(project);
        }

        log.error(getClass() + " in save method the conversion was null");
        return null;
    }

    @Transactional
    @Override
    public ProjectVO update(Long id, ProjectVO projectVO) {
        checkIfProjectExists(id);
        checkGroupAmount(projectVO.getGroupAmount());
        checkStudentsPerGroupAmount(projectVO.getStudentsPerGroup());

        Project oldProject = getProjectById(id);
        checkIfStudentsPerGroupChanged(projectVO.getStudentsPerGroup(), oldProject);
        List<Group> groupList = updateGroupList(projectVO.getGroupAmount(), oldProject);

        if(isConversionToModelNull(projectVO)) {
            Project project = saveOrUpdate(projectVoToModel.convert(projectVO), id);
            project.setGroups(groupList);
            return modelToVo.convert(project);
        }

        log.error(getClass() + " in update method the conversion was null");
        return null;
    }

    @Override
    public void delete(Long id) {
        projectRepository.delete(getProjectById(id));
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

    private List<Group> getGroupList(Project project) {
        List<Group> groupList = new ArrayList<>();

        for(int i = 1; i <= project.getGroupAmount(); i++) {
            groupList.add(groupService.initializeDefaultGroup(project, i));
        }

        return groupList;
    }

    private List<Group> getGroupListToAdd(Integer endIteration, Project oldProject) {
        List<Group> groupList = new ArrayList<>();

        for(int i = oldProject.getGroupAmount(); i < endIteration; i++) {
            groupService.initializeDefaultGroup(oldProject, i + 1);
        }

        return groupList;
    }

    private List<Group> getGroupListToRemove(Integer endIteration, Project oldProject) {
        List<Group> groupList = new ArrayList<>();

        for(int i = oldProject.getGroupAmount(); i > endIteration; i--) {
            groupService.removeGroupFromProject(oldProject.getGroups().get(i - 1));
            groupList.add(oldProject.getGroups().get(i - 1));
        }

        return  groupList;
    }

    private Project saveOrUpdate(Project project, Long id) {
        project.setId(id);
        return projectRepository.save(project);
    }

    private List<Group> updateGroupList(Integer newAmount, Project oldProject) {
        List<Group> groupList = oldProject.getGroups();

        if(isOldAmountGreater(newAmount, oldProject.getGroupAmount()) == null) {
            return groupList;
        }

        if(isOldAmountGreater(newAmount, oldProject.getGroupAmount())) {
            groupList.removeAll(getGroupListToRemove(newAmount, oldProject));
        }

        if(!isOldAmountGreater(newAmount, oldProject.getGroupAmount())) {
            groupList.addAll(getGroupListToAdd(newAmount, oldProject));
        }

        return groupList;
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

    private void checkGroupAmount(Integer groupAmount) {
        if(groupAmount < 1) {
            log.error("Group amount can not be lower than 1.");
            throw new ResourceIllogicalAmountException("Group amount can not be lower than 1.");
        }

        if(groupAmount > 20) {
            log.error("Maximum number of group is 20.");
            throw new ResourceIllogicalAmountException("Maximum number of group is 20.");
        }
    }

    private void checkStudentsPerGroupAmount(Integer studentsPerGroup) {
        if(studentsPerGroup < 2) {
            log.error("Students per group can not be lower than 2.");
            throw new ResourceIllogicalAmountException("Students per group can not be lower than 2.");
        }

        if(studentsPerGroup > 10) {
            log.error("Maximum number of students per group is 10.");
            throw new ResourceIllogicalAmountException("Maximum number of students per group is 10.");
        }
    }

    private void checkIfStudentsPerGroupChanged(Integer newAmount, Project oldProject) {
        if(isStudentPerGroupDifferent(newAmount, oldProject.getStudentsPerGroup())) {
            groupService.resetStudents(oldProject);
        }
    }

    private Boolean isConversionToModelNull(ProjectVO projectVO) {
        return projectVoToModel.convert(projectVO) != null;
    }

    private Boolean isOldAmountGreater(Integer newAmount, Integer oldAmount) {
        if(oldAmount > newAmount) {
            return true;
        }

        if(oldAmount < newAmount) {
            return  false;
        }

        return null;
    }

    private Boolean isStudentPerGroupDifferent(Integer newAmount, Integer oldAmount) {
        return !oldAmount.equals(newAmount);
    }

}
