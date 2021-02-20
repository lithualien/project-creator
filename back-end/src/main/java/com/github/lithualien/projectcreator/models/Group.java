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
@Entity(name = "project_groups")
public class Group extends BaseModel {

    private String groupName;

    @ManyToMany
    @JoinTable(name = "project_group_students",
            joinColumns = @JoinColumn(name = "project_group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    public Group(Long id, String groupName) {
        super(id);
        this.groupName = groupName;
    }
}
