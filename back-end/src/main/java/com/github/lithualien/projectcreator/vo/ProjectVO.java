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
public class ProjectVO extends BaseVO {

    private String projectName;
    private Integer groupAmount;
    private Set<GroupVO> groupVoSet = new HashSet<>();

    public ProjectVO(Long id, String projectName, Integer groupAmount, Set<GroupVO> groupVoSet) {
        super(id);
        this.projectName = projectName;
        this.groupAmount = groupAmount;
        this.groupVoSet = groupVoSet;
    }

}
