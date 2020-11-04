package com.minitwitter;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

public class AdminControl {
    private JPanel panelMain;
    private JButton addNewUser;
    private JTextField enterUserID;
    private JTextField enterUserGroupID;
    private JButton addNewUserGroup;
    private JLabel actionText;
    private JTree treeView;
    private JList treeList;
    private JPanel leftMainPanel;
    private List uniqueIDList;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private UserGroup treeViewList;

    public AdminControl() {
        treeViewList = new UserGroup("root");
        treeViewList.setTreeComponents(new ArrayList<TreeComponent>());
        uniqueIDList = new ArrayList<>();
        addNewUser.setText("Add User");
        addNewUserGroup.setText("Add User Group");
        System.out.println("here1");


        addNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultMutableTreeNode selectedGroupUI = (DefaultMutableTreeNode) treeView.getSelectionPath().getLastPathComponent();
                if (selectedGroupUI.getUserObject() instanceof UserGroup) {   //first, check if we are trying to add to a group
                    String getUserID = enterUserID.getText();
                    if (isUniqueID(getUserID)) { //check if ID is unique
                        uniqueIDList.add(getUserID);
                        User addedUser = new User(getUserID);
                        treeViewList.addToTree(addedUser);


                        actionText.setText("Added " + addedUser.getUserID() + " to the tree.");

                        DefaultMutableTreeNode newUserUI = new DefaultMutableTreeNode(addedUser);
                        selectedGroupUI.add(newUserUI);
                        DefaultTreeModel model = (DefaultTreeModel) treeView.getModel();
                        model.reload();

                        System.out.println(treeViewList.getTreeComponents());
                    } else {
                        actionText.setText("ERROR: " + getUserID + " is already taken.");
                    }
                } else {
                    actionText.setText("ERROR: " + selectedGroupUI + " is not a User Group.");
                }


            }
        });
        addNewUserGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selectedGroupUI = (DefaultMutableTreeNode) treeView.getSelectionPath().getLastPathComponent();
                if (selectedGroupUI.getUserObject() instanceof UserGroup) {
                    String getUserGroupID = enterUserGroupID.getText();
                    if (isUniqueID(getUserGroupID)) {
                        uniqueIDList.add(getUserGroupID);
                        UserGroup addedUserGroup = new UserGroup(getUserGroupID);
                        System.out.println(addedUserGroup.getUserID());
                        treeViewList.addToTree(addedUserGroup);
                        actionText.setText("Added " + addedUserGroup.getUserID() + " to the tree.");
                        DefaultMutableTreeNode newUserGroupUI = new DefaultMutableTreeNode(addedUserGroup);
                        selectedGroupUI.add(newUserGroupUI);
                        DefaultTreeModel model = (DefaultTreeModel) treeView.getModel();
                        model.reload();
                        System.out.println(treeViewList.getTreeComponents());

                    } else {
                        actionText.setText("ERROR: " + getUserGroupID + " is already taken.");
                    }
                } else {
                    actionText.setText("ERROR: " + selectedGroupUI + " is not a User Group.");
                }
            }
        });

        treeView.addMouseListener(new MouseAdapter() {


        });
    }

    public boolean isUniqueID(String uniqueID) {
        return !uniqueIDList.contains(uniqueID);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new AdminControl().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    private void createUIComponents() {
        treeViewList = new UserGroup("root");
        treeViewList.setTreeComponents(new ArrayList<TreeComponent>());
        root = new DefaultMutableTreeNode(treeViewList);


        //treeModel = new DefaultTreeModel(root);
        treeView = new JTree(root);
        treeView.putClientProperty("JTree.lineStyle", "Horizontal");

//        root.add(new DefaultMutableTreeNode("Stephen"));
//        root.add(new DefaultMutableTreeNode(new User("Randall")));
//        root.add(new DefaultMutableTreeNode(new User("Victoria")));
//        DefaultTreeSelectionModel sModel = new DefaultTreeSelectionModel();
//
//        sModel.setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
//        treeView.setSelectionModel(sModel);
    }
}
