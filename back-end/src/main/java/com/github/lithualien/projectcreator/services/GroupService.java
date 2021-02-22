package com.github.lithualien.projectcreator.services;

import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.GroupName;
import com.github.lithualien.projectcreator.models.Project;

import java.util.List;

public interface GroupService {

    List<Group> initializeGroupList(Project project);
    void deleteByProjectAndGroupName(Project project, GroupName groupName);
    List<Group> createOrDeleteGroups(Project project, Integer oldGroupAmount);
}
