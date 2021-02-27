package com.github.lithualien.projectcreator.services.impl;

import com.github.lithualien.projectcreator.exceptions.ResourceAlreadyExistsException;
import com.github.lithualien.projectcreator.exceptions.ResourceNotFoundException;
import com.github.lithualien.projectcreator.models.Group;
import com.github.lithualien.projectcreator.models.GroupName;
import com.github.lithualien.projectcreator.repositories.GroupNameRepository;
import com.github.lithualien.projectcreator.services.GroupNameService;
import com.github.lithualien.projectcreator.services.GroupService;
import com.github.lithualien.projectcreator.vo.GroupNameVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class GroupNameServiceImpl implements GroupNameService {

    private final GroupNameRepository groupNameRepository;
    private final Converter<GroupName, GroupNameVO> modelToVo;
    private final Converter<GroupNameVO, GroupName> voToModel;
    private final GroupService groupService;

    public GroupNameServiceImpl(GroupNameRepository groupNameRepository, Converter<GroupName, GroupNameVO> modelToVo,
                                Converter<GroupNameVO, GroupName> voToModel, GroupService groupService) {
        this.groupNameRepository = groupNameRepository;
        this.modelToVo = modelToVo;
        this.voToModel = voToModel;
        this.groupService = groupService;
    }

    @Override
    public List<GroupNameVO> all() {
        List<GroupName> groupNames = getGroupNameSet(groupNameRepository.findAll());
        return getGroupNameVoSet(groupNames);
    }

    @Override
    public GroupNameVO findById(Long id) {
        return modelToVo.convert(getGroupNameById(id));
    }

    @Override
    public GroupNameVO save(GroupNameVO groupNameVO) {
        checkIfGroupExists(groupNameVO.getGroupName());
        return saveOrUpdate(null, groupNameVO);
    }

    @Override
    public GroupNameVO update(Long id, GroupNameVO groupNameVO) {
        checkIfGroupExists(id);
        return saveOrUpdate(id, groupNameVO);
    }

    @Override
    public void delete(Long id) {
        GroupName groupName = getGroupNameById(id);
        groupNameRepository.delete(groupName);
    }

    @Override
    public GroupName findGroupNameByGroupName(String groupName) {
        return groupNameRepository
                .findByGroupName(groupName)
                .<ResourceNotFoundException> orElseThrow(() -> {
                    log.error("Group name " + groupName + " was not found!");
                    throw new ResourceNotFoundException("Group name " + groupName + " was not found!");
                });
    }

    @Override
    public GroupName saveByGroupName(String groupName) {
         try {
             checkIfGroupExists(groupName);
             GroupName groupNameObj = new GroupName(groupName);
             return groupNameRepository.save(groupNameObj);
         } catch (ResourceAlreadyExistsException ex) {
             return findGroupNameByGroupName(groupName);
         }
    }

    @Override
    public void updateGroupName(Long groupId, GroupNameVO groupNameVO) {
        GroupName groupName = saveByGroupName(groupNameVO.getGroupName());
        Group group = groupService.getGroupById(groupId);
        groupService.updateGroupName(group, groupName);
    }

    private List<GroupName> getGroupNameSet(Iterable<GroupName> groupNameIterable) {
        return StreamSupport
                .stream(groupNameIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    private List<GroupNameVO> getGroupNameVoSet(List<GroupName> groupNameSet) {
        return groupNameSet
                .stream()
                .map(modelToVo::convert)
                .collect(Collectors.toList());
    }

    private GroupName getGroupNameById(Long id) {
        return groupNameRepository
                .findById(id)
                .<ResourceNotFoundException> orElseThrow(() -> {
                    log.error("Group name with id = " + id + " was not found!");
                    throw new ResourceNotFoundException("Group name with id = " + id + " was not found!");
                });
    }

    private GroupNameVO saveOrUpdate(Long id, GroupNameVO groupNameVO) {
        groupNameVO.setId(id);
        GroupName groupName = voToModel.convert(groupNameVO);
        if(groupName != null) {
            GroupName receivedGroupName = groupNameRepository.save(groupName);
            return modelToVo.convert(receivedGroupName);
        } else {
            log.error(getClass() + ", saveOrUpdate() method, project after conversion was null.");
            return null;
        }
    }

    private void checkIfGroupExists(String groupName) {
        if(groupNameRepository.existsGroupNameByGroupName(groupName)) {
            log.error("Group " + groupName + " already exists.");
            throw new ResourceAlreadyExistsException("Group " + groupName + " already exists.");
        }
    }

    private void checkIfGroupExists(Long id) {
        if(!groupNameRepository.existsGroupNameById(id)) {
            log.error("Group with id = " + id + " was not found!");
            throw new ResourceNotFoundException("Group with id = " + id + " was not found!");
        }
    }

}
