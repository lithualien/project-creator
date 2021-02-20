package com.github.lithualien.projectcreator.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Integer studentsPerGroup;

    @JsonProperty("groups")
    private Set<GroupVO> groupVoSet = new HashSet<>();

    public ProjectVO(Long id, String projectName, Integer groupAmount,
                     Integer studentsPerGroup, Set<GroupVO> groupVoSet) {
        super(id);
        this.projectName = projectName;
        this.groupAmount = groupAmount;
        this.studentsPerGroup = studentsPerGroup;
        this.groupVoSet = groupVoSet;
    }


}
