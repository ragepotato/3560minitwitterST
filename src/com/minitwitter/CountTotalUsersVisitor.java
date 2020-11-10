package com.minitwitter;

public class CountTotalUsersVisitor implements TreeComponentVisitor {
    @Override
    public void visitUser(User user) {
        AdminControl.getInstance().getUsersTotal();
    }

    @Override
    public void visitUserGroup(UserGroup userGroup) {
        if (userGroup.getTreeComponents() != null) {
            for (TreeComponent treeComponent : userGroup.getTreeComponents()) {
                treeComponent.accept(this);
            }
        }

    }
}
