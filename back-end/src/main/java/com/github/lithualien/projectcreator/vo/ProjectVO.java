package com.github.lithualien.projectcreator.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectVO extends BaseVO {

    private String projectName;
    private String groupAmount;
    private String studentAmount;
    private GroupVO groupVO;

    public ProjectVO(Long id, String projectName, String groupAmount, String studentAmount, GroupVO groupVO) {
        super(id);
        this.projectName = projectName;
        this.groupAmount = groupAmount;
        this.studentAmount = studentAmount;
        this.groupVO = groupVO;
    }

}
