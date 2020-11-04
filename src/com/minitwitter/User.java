package com.minitwitter;

import java.util.ArrayList;
import java.util.List;

public class User implements TreeComponent {

    private String userID;
    private List followerList;
    private List followingList;

    public User(String userID) {
        System.out.println("Created new user.");
        this.userID = userID;
        followingList = new ArrayList();
        followerList = new ArrayList();
    }

    @Override //for JTree
    public String toString(){
        return userID;
    }

    public String getUserID() {

        return userID;
    }

    public String printTree(){
        return "  -" + userID;
    }


    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void addFollower(String userID){
        followerList.add(userID);
    }

    public void addFollowing(String userID){
        followingList.add(userID);
    }


    public void setFollowerList(List followerList) {
        this.followerList = followerList;
    }

    public void setFollowingList(List followingList) {
        this.followingList = followingList;
    }

    public List getFollowerList() {
        return followerList;
    }

    public List getFollowingList() {
        return followingList;
    }
}
