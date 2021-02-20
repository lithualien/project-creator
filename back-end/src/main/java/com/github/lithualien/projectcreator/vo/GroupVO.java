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
public class GroupVO extends BaseVO {

    @JsonProperty("students")
    private Set<StudentVO> studentVoSet = new HashSet<>();

    private String groupName;

    public GroupVO(Long id, String groupName, Set<StudentVO> studentVoSet) {
        super(id);
        this.groupName = groupName;
        this.studentVoSet = studentVoSet;
    }

}
