package com.github.lithualien.projectcreator.services;

import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.GroupName;
import com.github.lithualien.projectcreator.models.Project;
import com.github.lithualien.projectcreator.models.Student;

import java.util.List;

public interface GroupService {

    Group initializeDefaultGroup(Project project, Integer iterator);
    void resetStudents(Project project);
    void removeGroupFromProject(Group group);
    Group getGroupById(Long id);
    List<Group> getGroupByProjectId(Long projectId);
    void addStudentToGroup(Group group, Student student);
    void removeStudent(Group group, Student student);
    void removeStudent(List<Group> groupList, Student student);
    void updateGroupName(Group group, GroupName groupName);
}
