package com.github.lithualien.projectcreator.services.impl;

import com.github.lithualien.projectcreator.exceptions.ResourceNotFoundException;
import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.GroupName;
import com.github.lithualien.projectcreator.models.Project;
import com.github.lithualien.projectcreator.models.Student;
import com.github.lithualien.projectcreator.repositories.GroupRepository;
import com.github.lithualien.projectcreator.services.GroupNameService;
import com.github.lithualien.projectcreator.services.GroupService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@Log4j2
public class GroupServiceImpl implements GroupService {

    private final GroupNameService groupNameService;
    private final GroupRepository groupRepository;

    public GroupServiceImpl(@Lazy GroupNameService groupNameService, GroupRepository groupRepository) {
        this.groupNameService = groupNameService;
        this.groupRepository = groupRepository;
    }

    @Override
    public Group initializeDefaultGroup(Project project, Integer iterator) {
        Group group = new Group();
        group.setGroupName(getDefaultGroupName(iterator));
        group.setProject(project);
        return groupRepository.save(group);
    }

    @Override
    public void resetStudents(Project project) {
        project
                .getGroups()
                .forEach(this::removeStudents);
    }

    @Override
    public void removeGroupFromProject(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository
                .findById(id)
                .<ResourceNotFoundException> orElseThrow(() -> {
                    throw new ResourceNotFoundException("Group with id = " + id + " was not found.");
                });
    }

    @Override
    public List<Group> getGroupByProjectId(Long projectId) {
        return getGroupList(groupRepository.findAllByProjectId(projectId));
    }

    @Transactional
    @Override
    public void addStudentToGroup(Group group, Student student) {
        group.getStudentList().add(student);
    }

    @Transactional
    @Override
    public void removeStudent(Group group, Student student) {
        group.getStudentList().remove(student);
    }

    @Override
    public void removeStudent(List<Group> groupList, Student student) {
        groupList.forEach(group -> removeStudent(group, student));
    }

    @Transactional
    @Override
    public void updateGroupName(Group group, GroupName groupName) {
        group.setGroupName(groupName);
    }

    private List<Group> getGroupList(Iterable<Group> groupIterable) {
        return StreamSupport
                .stream(groupIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    private GroupName getDefaultGroupName(Integer iterator) {
        return groupNameService.saveByGroupName("#" + iterator + " Group");
    }

    @Transactional
    public void removeStudents(Group group) {
        group.getStudentList().removeAll(group.getStudentList());
    }

}
