package com.minitwitter;

import java.util.ArrayList;
import java.util.List;

public class User extends Subject implements TreeComponent, Observer {

    private String userID;
    private List<User> followerList;
    private List<User> followingList;
    private List<String> twitterMessages;

    public User(String userID) {
        System.out.println("Created new user.");
        this.userID = userID;
        followingList = new ArrayList();
        followerList = new ArrayList();
        twitterMessages = new ArrayList();
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

    public void addFollower(User userID){
        followerList.add(userID);
        attach(userID);
    }

    public void addFollowing(User userID){
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

    public List getTwitterMessages() {
        return twitterMessages;
    }

    public void postTweet(String tweet) {
        twitterMessages.add(tweet);
        notifyFollowers(tweet);
    }

    @Override
    public void getUpdate(String tweet) {
        twitterMessages.add(tweet);
        System.out.println(userID + "'s has just been updated: ");
        System.out.println(twitterMessages);
    }

}
