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

    public void addFollower(User follower){
        followerList.add(follower);
        attach(follower);  //attach both the follower and the follower's UI (which updates every time the followee tweets)
        attach(follower.getUserViewUI());
    }

    public void addFollowing(User userFollowing){
        followingList.add(userFollowing);
    }

    public void setFollowerList(List followerList) {
        this.followerList = followerList;
    }

    public void setFollowingList(List followingList) {
        this.followingList = followingList;
    }

    public List<User> getFollowerList() {
        return followerList;
    }

    public List<User> getFollowingList() {
        return followingList;
    }

    public List<String> getTwitterMessages() {
        return twitterMessages;
    }

    public void postTweet(String tweet) {
        tweet = "@" + userID + " tweeted: " + tweet;
        twitterMessages.add(tweet);
        notifyFollowers(tweet); //notify the followers when this user tweets
    }

    @Override
    public void getUpdate(String tweet) {
        twitterMessages.add(tweet);
        System.out.println(userID + "'s has just been updated: ");
        System.out.println(twitterMessages);
    }

    public UserViewUI getUserViewUI() { //the UserViewUI is also an observer, so that we can update it whenever someone that we follow tweets
            return userViewUI;
    }

    public void setUserViewUI(UserViewUI userViewUI) {
        this.userViewUI = userViewUI;
    }
}
