package com.github.lithualien.projectcreator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "projects")
public class Project extends BaseModel {

    @Column(name = "name")
    private String projectName;

    @Column(name = "student_amount_per_group")
    private Integer studentsPerGroup;

    @Column(name = "group_amount")
    private Integer groupAmount;

    @ManyToMany
    @JoinTable(name = "project_group_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "project_group_id"))
    private Set<Group> groups = new HashSet<>();

    public Project(Long id, String projectName, Integer studentsPerGroup, Integer groupAmount) {
        super(id);
        this.projectName = projectName;
        this.studentsPerGroup = studentsPerGroup;
        this.groupAmount = groupAmount;
    }


}
