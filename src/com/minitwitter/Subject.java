package com.minitwitter;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject { //aka the User that is being followed
    private List<Observer> followers = new ArrayList<Observer>();

    public void attach(Observer observer) {
        followers.add(observer);
    }

    public void detach(Observer observer) {
        followers.remove(observer);
    }

    public void notifyFollowers(String tweet) {
        for(Observer follower : followers) {
            follower.getUpdate(tweet);
        }
    }
}
