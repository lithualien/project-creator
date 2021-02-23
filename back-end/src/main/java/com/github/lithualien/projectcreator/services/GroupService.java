package com.github.lithualien.projectcreator.services;

import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.Project;

import java.util.List;

public interface GroupService {

    List<Group> initializeGroupList(Project project);
    List<Group> createOrDeleteGroups(Project project, Integer oldGroupAmount);
    void resetStudentsOnGroup(List<Group> group, Integer oldStudentsPerGroup, Integer newStudentsPerGroup);
}
