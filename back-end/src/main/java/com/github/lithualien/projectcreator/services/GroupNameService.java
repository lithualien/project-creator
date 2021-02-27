package com.github.lithualien.projectcreator.services;

import com.github.lithualien.projectcreator.models.GroupName;
import com.github.lithualien.projectcreator.vo.GroupNameVO;

public interface GroupNameService  extends CrudService<GroupNameVO>, SearchService<GroupNameVO> {

    GroupName findGroupNameByGroupName(String groupName);
    GroupName saveByGroupName(String groupName);
    void updateGroupName(Long groupId, GroupNameVO groupNameVO);

}
