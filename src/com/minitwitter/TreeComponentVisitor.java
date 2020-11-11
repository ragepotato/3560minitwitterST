package com.minitwitter;

public interface TreeComponentVisitor { //visitor interface
    public void visitUser(User user);
    public void visitUserGroup(UserGroup userGroup);
}
