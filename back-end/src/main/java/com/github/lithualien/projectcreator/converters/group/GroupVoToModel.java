package com.github.lithualien.projectcreator.converters.group;

import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.GroupName;
import com.github.lithualien.projectcreator.vo.group.GroupVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GroupVoToModel implements Converter<GroupVO, Group> {

    @Override
    public Group convert(GroupVO groupVO) {
        Group group = new Group();
        group.setId(groupVO.getId());
        group.setGroupName(createGroupName(groupVO.getGroupName()));
        group.setStudents(null);
        return group;
    }

    private GroupName createGroupName(String groupName) {
        return new GroupName(groupName);
    }

}
