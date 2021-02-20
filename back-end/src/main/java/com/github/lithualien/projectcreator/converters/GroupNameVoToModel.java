package com.github.lithualien.projectcreator.converters;

import com.github.lithualien.projectcreator.models.GroupName;
import com.github.lithualien.projectcreator.vo.GroupNameVO;
import org.springframework.core.convert.converter.Converter;

public class GroupNameVoToModel implements Converter<GroupNameVO, GroupName> {

    @Override
    public GroupName convert(GroupNameVO groupNameVO) {
        GroupName groupName = new GroupName();
        groupName.setId(groupNameVO.getId());
        groupName.setGroupName(groupNameVO.getGroupName());
        return groupName;
    }

}
