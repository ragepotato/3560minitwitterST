package com.minitwitter;

import java.util.List;

public class UserGroup implements TreeComponent { //composite class with hierarchy implements interface

    private String userID;
    private List<TreeComponent> treeComponents; //composite (UserGroup) can have a list of TreeComponents

    public UserGroup(String userID) {
        this.userID = userID;
    }

    @Override //for JTree
    public String toString() {
        return userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public void accept(TreeComponentVisitor visitor) {

        visitor.visitUserGroup(this);
    }

    public List<TreeComponent> getTreeComponents() {
        return treeComponents;
    }

    public void setTreeComponents(List<TreeComponent> treeComponents) {
        this.treeComponents = treeComponents;
    }

    public void addToTree(TreeComponent treeComponent) {
        this.treeComponents.add(treeComponent);
    }


}
