package com.minitwitter;

public class CountTotalGroupsVisitor implements TreeComponentVisitor {

    @Override
    public void visitUser(User user) {
    }

    @Override
    public void visitUserGroup(UserGroup userGroup) {
        AdminControl.getInstance().getGroupsTotal();  //when visiting a usergroup, increment total
        if (userGroup.getTreeComponents() != null) {
            for (TreeComponent treeComponent : userGroup.getTreeComponents()) {
                treeComponent.accept(this);
            }
        }

    }
}
