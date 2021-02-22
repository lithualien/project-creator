package com.github.lithualien.projectcreator.vo.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.lithualien.projectcreator.vo.StudentVO;
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
public class GroupStudentVO extends GroupVO {

    @JsonProperty("students")
    private List<StudentVO> studentVoSet = new ArrayList<>();

    public GroupStudentVO(Long id, String groupName, List<StudentVO> studentVoSet) {
        super(id, groupName);
        this.studentVoSet = studentVoSet;
    }

}
