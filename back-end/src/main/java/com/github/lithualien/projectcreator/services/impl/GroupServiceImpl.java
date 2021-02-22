package com.github.lithualien.projectcreator.services.impl;

import com.github.lithualien.projectcreator.exceptions.ResourceNotFoundException;
import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.GroupName;
import com.github.lithualien.projectcreator.models.Project;
import com.github.lithualien.projectcreator.repositories.GroupRepository;
import com.github.lithualien.projectcreator.services.GroupNameService;
import com.github.lithualien.projectcreator.services.GroupService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupNameService groupNameService;

    public GroupServiceImpl(GroupRepository groupRepository, GroupNameService groupNameService) {
        this.groupRepository = groupRepository;
        this.groupNameService = groupNameService;
    }

    @Override
    public List<Group> initializeGroupList(Project project) {
        List<Group> groupList = getGroupsToInitialize(project);
        groupRepository.saveAll(groupList);
        return groupList;
    }

    @Override
    public void deleteByProjectAndGroupName(Project project, GroupName groupName) {
        groupRepository.deleteByProjectAndGroupName(project, groupName);
    }

    @Transactional
    @Override
    public List<Group> createOrDeleteGroups(Project project, Integer groupAmount) {
        List<Group> groupList = project.getGroups();

        if(project.getGroupAmount() < groupAmount) {
            groupRepository.saveAll(getGroupsToAdd(project, groupAmount));
        }

        if(project.getGroupAmount() > groupAmount) {
            groupList.removeAll(getGroupsToRemove(project, groupAmount));
        }
        return groupList;
    }

    private Group findAndAddGroupName(String groupName, Project project) {
        Group group = new Group();
        GroupName groupNameObj = groupNameService.findGroupNameByGroupName(groupName);
        group.setGroupName(groupNameObj);
        group.setProject(project);
        return group;

    }

    private Group createAndGetGroupName(String groupName, Project project) {
        Group group = new Group();
        GroupName groupNameObj = groupNameService.saveByGroupName(groupName);
        group.setGroupName(groupNameObj);
        group.setProject(project);
        return group;
    }

    private Group createGroup(Project project, String groupName) {
        try {
            return findAndAddGroupName(groupName, project);
        } catch (ResourceNotFoundException ex) {
            return createAndGetGroupName(groupName, project);
        }
    }

    private void deleteGroup(Project project, GroupName groupName) {
        groupRepository.deleteByProjectAndGroupName(project, groupName);
    }

    private List<Group> getGroupsToRemove(Project project, Integer groupAmount) {
        List<Group> groupList = new ArrayList<>();

        for(int i = project.getGroupAmount() - 1; i >= groupAmount; i--) {
            deleteGroup(project, project.getGroups().get(i).getGroupName());
            groupList.add(project.getGroups().get(i));
        }

        return groupList;
    }

    private List<Group> getGroupsToAdd(Project project, Integer groupAmount) {
        List<Group> groupList = new ArrayList<>();

        for(int i = project.getGroupAmount() + 1; i <= groupAmount; i++) {
            groupList.add(createGroup(project, "#" + i + " Group"));
        }

        return groupList;
    }

    private List<Group> getGroupsToInitialize(Project project) {
        List<Group> groupList = new ArrayList<>();

        for(int i = 1; i <= project.getGroupAmount(); i++) {
            groupList.add(createGroup(project, "#" + i + " Group"));
        }

        return groupList;
    }

}
