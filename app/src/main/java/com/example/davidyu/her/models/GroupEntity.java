package com.example.davidyu.her.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caochao on 17/11/15.
 */
public class GroupEntity {
    private String groupName;
    private List<ChildEntity> childEntities;

    public GroupEntity(String groupName) {
        this.groupName = groupName;
        childEntities = new ArrayList<ChildEntity>();
    }

    public List<ChildEntity> getChildEntities() {
        return childEntities;
    }

    public void setChildEntities(List<ChildEntity> childEntities) {
        this.childEntities = childEntities;
    }
    public void addChild(String name)
    {
        ChildEntity new_child = new ChildEntity(name);
        childEntities.add(new_child);
    }
    public void removeChild(int childPosition)
    {
        childEntities.remove(childPosition);
    }
    public String getGroupName() {
        return groupName;
    }
}
