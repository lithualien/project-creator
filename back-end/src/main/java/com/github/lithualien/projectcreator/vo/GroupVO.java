package com.github.lithualien.projectcreator.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupVO extends BaseVO {

    private GroupNameVO groupName;
    private Integer studentAmount;
    private Set<StudentVO> studentVoSet = new HashSet<>();

    public GroupVO(Long id, GroupNameVO groupName, Integer studentAmount, Set<StudentVO> studentVoSet) {
        super(id);
        this.groupName = groupName;
        this.studentAmount = studentAmount;
        this.studentVoSet = studentVoSet;
    }

}
