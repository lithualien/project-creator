package com.github.lithualien.projectcreator.models;

import lombok.*;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "project_group_names")
public class GroupName extends BaseModel {

    private String groupName;

    public GroupName(Long id, String groupName) {
        super(id);
        this.groupName = groupName;
    }

}
