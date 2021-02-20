package com.github.lithualien.projectcreator.vo.project;

import com.github.lithualien.projectcreator.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectVO extends BaseVO {

    private String projectName;
    private Integer groupAmount;
    private Integer studentsPerGroup;

    public ProjectVO(Long id, String projectName, Integer groupAmount, Integer studentsPerGroup) {
        super(id);
        this.projectName = projectName;
        this.groupAmount = groupAmount;
        this.studentsPerGroup = studentsPerGroup;
    }

}
