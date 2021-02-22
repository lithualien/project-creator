package com.github.lithualien.projectcreator.converters.group;

import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.vo.group.GroupVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GroupModelToGroupVo implements Converter<Group, GroupVO> {

    @Override
    public GroupVO convert(Group group) {
        return new GroupModelToGroupStudentVo().convert(group);
    }

}
