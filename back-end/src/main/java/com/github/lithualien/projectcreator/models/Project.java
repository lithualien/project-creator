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

    private String name;

    @Column(name = "group_amount")
    private Integer groupAmount;

    @ManyToMany
    @JoinTable(name = "group_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();

    public Project(Long id, String name, Integer groupAmount) {
        super(id);
        this.name = name;
        this.groupAmount = groupAmount;
    }


}
