package com.github.lithualien.projectcreator.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupVO extends BaseVO {

    private String groupName;
    private Integer studentAmount;
    private StudentVO studentVO;

    public GroupVO(Long id, String groupName, Integer studentAmount, StudentVO studentVO) {
        super(id);
        this.groupName = groupName;
        this.studentAmount = studentAmount;
        this.studentVO = studentVO;
    }

}
