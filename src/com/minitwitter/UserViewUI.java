package com.minitwitter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

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
    private JList list1;
    private DefaultListModel listModel1;
    private DefaultListModel listModel2;

    private UserGroup treeOfUsers;
    private HashMap uniqueIDList;


    public UserViewUI(User user) {


        this.user = user;
        this.userID = user.getUserID();
        System.out.println("User: " + user.getUserID());
        followUserButton.setText("Follow User");
        postTweetButton.setText("Post Tweet");
        uniqueIDList = AdminControl.getInstance().getUniqueIDList();
        //newsFeedList = new JList(user.getTwitterMessages().toArray());

//        if (user.getTwitterMessages() != null){
//            newsFeedList = new JList((ListModel) user.getTwitterMessages());
//        }



        followUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String followUser = enterUserField.getText();
                if (uniqueIDList.containsKey(followUser) && !userID.equals(followUser) && uniqueIDList.get(followUser) instanceof User){
                    System.out.println("Yes! We can get " + followUser);
                    User nowFollowingUser = (User) uniqueIDList.get(followUser);
                    nowFollowingUser.addFollower(user);
                    user.addFollowing(nowFollowingUser);
                    nowFollowingUser.attach(updateList());

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

                listModel2.clear();

                for (Object aTweet : user.getTwitterMessages()){
                    listModel2.addElement(aTweet);
                }
                userViewPanel.updateUI();

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


    private void createUIComponents() {
        System.out.println(user.getTwitterMessages());
        listModel1 = new DefaultListModel();
        listModel2 = new DefaultListModel();

        currentFollowList = new JList(listModel1);
        newsFeedList = new JList(listModel2);


        // TODO: place custom component creation code here
    }

    @Override
    public void getUpdate(String tweet) {
        System.out.println("Hey there");
        listModel2.addElement(tweet);
    }

    private UserViewUI updateList(){
        return this;
    }
}
