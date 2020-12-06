package com.minitwitter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

//class to display the specified User's UI

public class UserViewUI implements Observer {
    private String userID;
    private User user;
    private JButton followUserButton;
    private JPanel userViewPanel;
    private JTextField enterUserField;
    private JList currentFollowList;
    private JTextField enterTweetField;
    private JButton postTweetButton;
    private JList newsFeedList;
    private JPanel followingPanel;
    private JLabel creationTime;
    private JLabel lastUpdateTime;
    private DefaultListModel followingListModel;
    private DefaultListModel twitterFeedModel;
    private HashMap uniqueIDList;
    JFrame userFrame;


    public UserViewUI(User user) {
        this.user = user;
        this.userID = user.getUserID();
        followUserButton.setText("Follow User");
        postTweetButton.setText("Post Tweet");
        uniqueIDList = AdminControl.getInstance().getUniqueIDList();
        creationTime.setText("  Creation time: " + user.getCreationTime());
        lastUpdateTime.setText("Last news feed update time: ");

        for (Object aTweet : user.getTwitterMessages()) {
            twitterFeedModel.addElement(aTweet);
        }
        followingListModel.clear();
        for (Object aTweet : user.getFollowingList()) {
            followingListModel.addElement(aTweet);
        }
        followUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String followUser = enterUserField.getText();
                //check if you can follow the user (if they exist, not the same as user, is a user not usergroup)
                if (uniqueIDList.containsKey(followUser) && !userID.equals(followUser) && uniqueIDList.get(followUser) instanceof User) {
                    User nowFollowingUser = (User) uniqueIDList.get(followUser);
                    nowFollowingUser.addFollower(user);
                    user.addFollowing(nowFollowingUser);
                    enterUserField.setText("");
                    followingListModel.addElement(nowFollowingUser.getUserID());
                } else {
                    JOptionPane.showMessageDialog(userFrame,
                            "ERROR: Cannot follow user",
                            "Inane error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        postTweetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tweet = enterTweetField.getText();
                user.postTweet(tweet);
                twitterFeedModel.addElement("@You tweeted: " + tweet);
                enterTweetField.setText("");
                lastUpdateTime.setText("Last news feed update time: " + user.getLastUpdateTime().toString());

            }
        });
    }

    public void showUserViewUI() { //create and show the UI
        userFrame = new JFrame(userID);
        userFrame.setContentPane(userViewPanel);
        userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userFrame.setSize(500, 600);
        userFrame.setVisible(true);
    }

    private void createUIComponents() {
        followingListModel = new DefaultListModel();
        twitterFeedModel = new DefaultListModel();
        currentFollowList = new JList(followingListModel);
        newsFeedList = new JList(twitterFeedModel);
        twitterFeedModel.clear();
        // TODO: place custom component creation code here
    }

    @Override
    public void getUpdate(String tweet, Long lastUpdate) {   //updates the UI whenever someone that the user follows tweets
        twitterFeedModel.clear();
        lastUpdateTime.setText("Last news feed update time: " + user.getLastUpdateTime().toString());
        for (Object aTweet : user.getTwitterMessages()) {
            twitterFeedModel.addElement(aTweet);

        }
    }

}
