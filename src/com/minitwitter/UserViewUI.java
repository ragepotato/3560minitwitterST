package com.minitwitter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class UserViewUI {
    private String userID;
    private JButton followUserButton;
    private JPanel userViewPanel;
    private JTextField enterUserField;
    private JList currentFollowList;
    private JTextField enterTweetField;
    private JButton postTweetButton;
    private JList newsFeedList;
    private JPanel followingPanel;
    private UserGroup treeOfUsers;
    private HashMap uniqueIDList;


    public UserViewUI(User user) {
        this.userID = user.getUserID();

        followUserButton.setText("Follow User");
        postTweetButton.setText("Post Tweet");
        uniqueIDList = AdminControl.getInstance().getUniqueIDList();


        followUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String followUser = enterUserField.getText();
                if (uniqueIDList.containsKey(followUser) && !userID.equals(followUser) && uniqueIDList.get(followUser) instanceof User){
                    System.out.println("Yes! We can get " + followUser);
                    User nowFollowingUser = (User) uniqueIDList.get(followUser);
                    nowFollowingUser.addFollower(user);
                    user.addFollowing(nowFollowingUser);
                }
                else{
                    System.out.println("Nope can't do that");
                }
            }
        });
        postTweetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tweet = enterTweetField.getText();
                user.postTweet(tweet);

            }
        });
    }

    public void showUserViewUI(){
        JFrame userFrame = new JFrame(userID);
        userFrame.setContentPane(userViewPanel);
        userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userFrame.setSize(500,600);
        userFrame.setVisible(true);
    }


    public JPanel getUserViewPanel(){
        return userViewPanel;
    }



}
