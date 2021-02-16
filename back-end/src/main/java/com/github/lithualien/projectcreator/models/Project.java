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
@Entity
public class Project extends BaseModel {

    private String name;

    @Column(name = "group_amount")
    private Integer groupAmount;

    @Column(name = "student_amount_per_group")
    private Integer studentAmount;

    @ManyToMany()
    @JoinTable(name = "project_students",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    public Project(Long id, String name, Integer groupAmount, Integer studentAmount) {
        super(id);
        this.name = name;
        this.groupAmount = groupAmount;
        this.studentAmount = studentAmount;
    }


}
