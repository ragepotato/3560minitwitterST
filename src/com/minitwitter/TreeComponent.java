package com.minitwitter;

public interface TreeComponent {

    public String getUserID();
    public void setUserID(String userID);
    public void accept(TreeComponentVisitor visitor);

}
