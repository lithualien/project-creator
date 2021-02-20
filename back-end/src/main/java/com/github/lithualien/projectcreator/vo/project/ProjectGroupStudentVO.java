package com.github.lithualien.projectcreator.vo.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.lithualien.projectcreator.vo.group.GroupStudentVO;
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
public class ProjectGroupStudentVO extends ProjectVO {

    @JsonProperty("groups")
    private Set<GroupStudentVO> groupStudentVoSet = new HashSet<>();

    public ProjectGroupStudentVO(Long id, String projectName, Integer groupAmount,
                                 Integer studentsPerGroup, Set<GroupStudentVO> groupStudentVoSet) {
        super(id, projectName, groupAmount, studentsPerGroup);
        this.groupStudentVoSet = groupStudentVoSet;
    }


}
