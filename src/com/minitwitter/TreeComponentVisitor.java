package com.minitwitter;

public interface TreeComponentVisitor {
    public void visitUser(User user);
    public void visitUserGroup(UserGroup userGroup);
}
