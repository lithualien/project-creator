package com.github.lithualien.projectcreator.vo.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.lithualien.projectcreator.vo.group.GroupStudentVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectGroupStudentVO extends ProjectVO {

    @JsonProperty("groups")
    private List<GroupStudentVO> groupStudentVoSet = new ArrayList<>();

    public ProjectGroupStudentVO(Long id, String projectName, Integer groupAmount,
                                 Integer studentsPerGroup, List<GroupStudentVO> groupStudentVoSet) {
        super(id, projectName, groupAmount, studentsPerGroup);
        this.groupStudentVoSet = groupStudentVoSet;
    }


}
