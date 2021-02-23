package com.github.lithualien.projectcreator.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "project_groups")
public class Group extends BaseModel {

    @ManyToOne()
    @JoinColumn(name = "project_group_name_id")
    private GroupName groupName;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToMany
    @JoinTable(name = "project_group_students",
            joinColumns = @JoinColumn(name = "project_group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students = new ArrayList<>();

    public Group(Long id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName=" + groupName +
                '}';
    }
}
