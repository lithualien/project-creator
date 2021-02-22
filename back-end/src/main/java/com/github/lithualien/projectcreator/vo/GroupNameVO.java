package com.github.lithualien.projectcreator.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupNameVO extends BaseVO {

    private String groupName;

    public GroupNameVO(Long id, String groupName) {
        super(id);
        this.groupName = groupName;
    }

}
