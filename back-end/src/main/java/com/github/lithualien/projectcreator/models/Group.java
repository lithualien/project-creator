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
@Entity(name = "groups")
public class Group extends BaseModel {

    @Column(name = "student_amount_per_group")
    private Integer studentAmount;

    @OneToMany
    private GroupName groupName;

    @ManyToMany
    @JoinTable(name = "group_students",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    public Group(Long id, Integer studentAmount) {
        super(id);
        this.studentAmount = studentAmount;
    }
}
