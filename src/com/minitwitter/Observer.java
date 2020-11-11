package com.minitwitter;

public interface Observer { //aka the user that is getting updated whenever someone that they follow tweets
    public void getUpdate(String tweet);
}
