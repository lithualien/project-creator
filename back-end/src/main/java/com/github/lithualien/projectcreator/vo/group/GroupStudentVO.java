package com.github.lithualien.projectcreator.vo.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.lithualien.projectcreator.vo.StudentVO;
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
public class GroupStudentVO extends GroupVO {

    @JsonProperty("students")
    private Set<StudentVO> studentVoSet = new HashSet<>();

    public GroupStudentVO(Long id, String groupName, Set<StudentVO> studentVoSet) {
        super(id, groupName);
        this.studentVoSet = studentVoSet;
    }

}
