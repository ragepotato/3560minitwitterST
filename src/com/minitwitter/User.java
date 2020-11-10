package com.minitwitter;

import java.util.ArrayList;
import java.util.List;

public class User extends Subject implements TreeComponent, Observer {

    private String userID;
    private List<User> followerList;
    private List<User> followingList;
    private List<String> twitterMessages;
    private UserViewUI userViewUI;

    public User(String userID) {
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

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public void accept(TreeComponentVisitor visitor) {
        visitor.visitUser(this);
    }




    public void addFollower(User userID){
        followerList.add(userID);
        attach(userID);
        attach(userID.getUserViewUI());
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
        tweet = "@" + userID + " tweeted: " + tweet;
        twitterMessages.add(tweet);
        notifyFollowers(tweet);
    }

    @Override
    public void getUpdate(String tweet) {
        twitterMessages.add(tweet);
        System.out.println(userID + "'s has just been updated: ");
        System.out.println(twitterMessages);
    }


    public UserViewUI getUserViewUI() {
            return userViewUI;
    }

    public void setUserViewUI(UserViewUI userViewUI) {
        this.userViewUI = userViewUI;
    }
}
