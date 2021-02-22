package com.github.lithualien.projectcreator.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Group> groups = new ArrayList<>();

    public Project(Long id, String projectName, Integer studentsPerGroup, Integer groupAmount) {
        super(id);
        this.projectName = projectName;
        this.studentsPerGroup = studentsPerGroup;
        this.groupAmount = groupAmount;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", studentsPerGroup=" + studentsPerGroup +
                ", groupAmount=" + groupAmount +
                ", groups=" + groups +
                '}';
    }
}
