package com.github.lithualien.projectcreator.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentVO extends BaseVO {

    private String fullName;

    public StudentVO(Long id, String firstName, String lastName) {
        super(id);
        setFullName(firstName, lastName);
    }

    public void setFullName(String firstName, String lastName) {
        this.fullName = firstName + " " + lastName;
    }

}
