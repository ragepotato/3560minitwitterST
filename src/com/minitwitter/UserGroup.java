package com.minitwitter;

import java.util.List;

public class UserGroup implements TreeComponent{

    private String userID;
    private List<TreeComponent> treeComponents;

    public UserGroup(String userID) {
        this.userID = userID;
    }

    @Override //for JTree
    public String toString(){
        return userID;
    }



    public String getUserID() {
        return userID;

    }

    public String printTree(){


        if (getTreeComponents() != null) {
            System.out.println(" -" + userID);
            for(TreeComponent treeComponent : getTreeComponents()) {
                System.out.println(treeComponent.printTree());

            }
        }
        return "";
    }


    public void setUserID(String userID) {
        this.userID = userID;
    }


    public List<TreeComponent> getTreeComponents() {
        return treeComponents;
    }

    public void setTreeComponents(List<TreeComponent> treeComponents) {
        this.treeComponents = treeComponents;
    }

    public void addToTree(TreeComponent treeComponent){
        this.treeComponents.add(treeComponent);
    }


}
