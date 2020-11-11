package com.minitwitter;

public class CountTotalMessagesVisitor implements TreeComponentVisitor{

    @Override
    public void visitUser(User user) {
        AdminControl.getInstance().getTotalMessagesCount(user.getTwitterMessages().size()); //adds all the user's feed sizes together
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
