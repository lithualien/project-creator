package com.github.lithualien.projectcreator.converters.groupname;

import com.github.lithualien.projectcreator.models.GroupName;
import com.github.lithualien.projectcreator.vo.GroupNameVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GroupNameModelToVo implements Converter<GroupName, GroupNameVO> {

    @Override
    public GroupNameVO convert(GroupName groupName) {
        GroupNameVO groupNameVO = new GroupNameVO();
        groupNameVO.setId(groupName.getId());
        groupNameVO.setGroupName(groupName.getGroupName());
        return groupNameVO;
    }

}
