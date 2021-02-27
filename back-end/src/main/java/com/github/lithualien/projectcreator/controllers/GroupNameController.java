package com.github.lithualien.projectcreator.controllers;

import com.github.lithualien.projectcreator.services.GroupNameService;
import com.github.lithualien.projectcreator.vo.GroupNameVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/group/names/v1")
public class GroupNameController {

    private final GroupNameService groupNameService;

    public GroupNameController(GroupNameService groupNameService) {
        this.groupNameService = groupNameService;
    }

    @PostMapping("/{groupId}")
    public ResponseEntity<?> changeGroupName(@PathVariable Long groupId, @RequestBody GroupNameVO groupNameVO) {
        groupNameService.updateGroupName(groupId, groupNameVO);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

}
