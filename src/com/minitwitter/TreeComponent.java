package com.minitwitter;

public interface TreeComponent { //hierarchy allows for composite pattern so UserGroup can hold both Users and UserGroups
    public String getUserID();
    public void setUserID(String userID);
    public void accept(TreeComponentVisitor visitor);
}
