package com.github.lithualien.projectcreator.vo.group;

import com.github.lithualien.projectcreator.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupVO extends BaseVO {

    private String groupName;

    public GroupVO(Long id, String groupName) {
        super(id);
        this.groupName = groupName;
    }

}
