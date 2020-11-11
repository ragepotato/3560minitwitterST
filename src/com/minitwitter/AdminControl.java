package com.minitwitter;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//main UI class, also a singleton so only one instance

public class AdminControl {
    private JPanel panelMain;
    private JButton addNewUserButton;
    private JTextField enterUserID;
    private JTextField enterUserGroupID;
    private JButton addNewUserGroupButton;
    private JLabel actionText;
    private JTree treeView;
    private JPanel leftMainPanel;
    private JButton openUserViewButton;
    private JButton showUserTotalButton;
    private JButton showGroupTotalButton;
    private JButton showMessagesTotalButton;
    private JButton showPositiveButton;
    private JPanel rightMainPanel;
    private static HashMap uniqueIDList;  //data structure to check if User/UserGroup ID is taken, also returns TreeComponent object for easy access
    private DefaultMutableTreeNode root;
    private UserGroup treeViewList;
    private static int userTotalCount;
    private static int userGroupTotalCount;
    private static int totalMessagesCount;
    private static int totalPositiveCount;
    private JFrame mainFrame;
    private static AdminControl pointer;  //Singleton static instance

    public static AdminControl getInstance() {  //Singleton static getter
        if (pointer == null) {
            pointer = new AdminControl();
        }
        return pointer;
    }

    private AdminControl() {  //Singleton private constructor
        treeViewList = new UserGroup("root"); //the composite tree list
        treeViewList.setTreeComponents(new ArrayList<TreeComponent>());
        uniqueIDList = new HashMap<String, TreeComponent>();
        uniqueIDList.put("root", treeViewList);
        addNewUserButton.setText("Add User");
        addNewUserGroupButton.setText("Add User Group");
        openUserViewButton.setText("Open User View");
        showUserTotalButton.setText("Show User Total");
        showGroupTotalButton.setText("Show Group Total");
        showMessagesTotalButton.setText("Show Messages Total");
        showPositiveButton.setText("Show Positive Percentage");
        userTotalCount = 0;
        userGroupTotalCount = 0;
        totalMessagesCount = 0;

        addNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeSelectionModel checkSelected = treeView.getSelectionModel();
                if (checkSelected.getSelectionCount() > 0) {  //check if we specified where we are adding
                    DefaultMutableTreeNode selectedGroupUI = (DefaultMutableTreeNode) treeView.getSelectionPath().getLastPathComponent();
                    if (selectedGroupUI.getUserObject() instanceof UserGroup) {   //check if we are trying to add to a group
                        String getUserID = enterUserID.getText();
                        if (isUniqueID(getUserID)) { //check if ID is unique
                            User addedUser = new User(getUserID);
                            uniqueIDList.put(getUserID, addedUser);
                            treeViewList.addToTree(addedUser);
                            actionText.setText("Added " + addedUser.getUserID() + " to the tree.");
                            DefaultMutableTreeNode newUserNode = new DefaultMutableTreeNode(addedUser);
                            selectedGroupUI.add(newUserNode);
                            DefaultTreeModel model = (DefaultTreeModel) treeView.getModel();
                            model.reload();
                            enterUserID.setText("");
                        } else {
                            errorDialog("ERROR: " + getUserID + " is already taken.");
                        }
                    } else {
                        errorDialog("ERROR: " + selectedGroupUI + " is not a User Group.");
                    }
                } else {
                    errorDialog("ERROR: Choose where to add on tree.");
                }
            }
        });

        addNewUserGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeSelectionModel checkSelected = treeView.getSelectionModel();
                if (checkSelected.getSelectionCount() > 0) { //check if we specified where we are adding
                    DefaultMutableTreeNode selectedGroupUI = (DefaultMutableTreeNode) treeView.getSelectionPath().getLastPathComponent();
                    if (selectedGroupUI.getUserObject() instanceof UserGroup) { //check if we are trying to add to a group
                        String getUserGroupID = enterUserGroupID.getText();
                        if (isUniqueID(getUserGroupID)) { //check if ID is unique
                            UserGroup addedUserGroup = new UserGroup(getUserGroupID);
                            uniqueIDList.put(getUserGroupID, addedUserGroup);
                            treeViewList.addToTree(addedUserGroup); //add to composite
                            actionText.setText("Added " + addedUserGroup.getUserID() + " to the tree.");
                            DefaultMutableTreeNode newUserGroupNode = new DefaultMutableTreeNode(addedUserGroup);
                            selectedGroupUI.add(newUserGroupNode); //add to UI
                            DefaultTreeModel model = (DefaultTreeModel) treeView.getModel();
                            model.reload();
                            enterUserGroupID.setText("");
                        } else {
                            errorDialog("ERROR: " + getUserGroupID + " is already taken.");
                        }
                    } else {
                        errorDialog("ERROR: " + selectedGroupUI + " is not a User Group.");
                    }
                } else {
                    errorDialog("ERROR: Choose where to add on tree.");
                }
            }
        });

        showUserTotalButton.addActionListener(new ActionListener() {  //uses CountTotalUsersVisitor
            @Override
            public void actionPerformed(ActionEvent e) {
                userTotalCount = 0;
                CountTotalUsersVisitor userCount = new CountTotalUsersVisitor();
                treeViewList.accept(userCount);
                actionText.setText("Total user count: " + userTotalCount);
            }
        });

        showGroupTotalButton.addActionListener(new ActionListener() { //uses CountTotalGroupsVisitor
            @Override
            public void actionPerformed(ActionEvent e) {
                userGroupTotalCount = 0;
                CountTotalGroupsVisitor groupCount = new CountTotalGroupsVisitor();
                treeViewList.accept(groupCount);
                actionText.setText("Total user group count: " + userGroupTotalCount);

            }
        });

        openUserViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selectedUser = (DefaultMutableTreeNode) treeView.getSelectionPath().getLastPathComponent();
                if (selectedUser.getUserObject() instanceof User) {
                    UserViewUI newUserView = new UserViewUI((User) selectedUser.getUserObject());
                    ((User) selectedUser.getUserObject()).setUserViewUI(newUserView);
                    newUserView.showUserViewUI(); //open the user's UI
                } else {
                    errorDialog("ERROR: Not a user.");
                }
            }
        });

        showMessagesTotalButton.addActionListener(new ActionListener() { //uses CountTotalMessagesVisitor
            @Override
            public void actionPerformed(ActionEvent e) {
                totalMessagesCount = 0;
                CountTotalMessagesVisitor messageCount = new CountTotalMessagesVisitor();
                treeViewList.accept(messageCount);
                actionText.setText("Total messages in feeds: " + totalMessagesCount);
            }
        });

        showPositiveButton.addActionListener(new ActionListener() { //uses CountTotalPositiveVisitor
            @Override
            public void actionPerformed(ActionEvent e) {
                totalPositiveCount = 0;
                CountTotalPositiveVisitor messageCount = new CountTotalPositiveVisitor();
                treeViewList.accept(messageCount);
                actionText.setText("Total positive messages in feeds: " + totalPositiveCount);
            }
        });
    }

    public boolean isUniqueID(String uniqueID) {  //check in hashmap if id is unique
        return !uniqueIDList.containsKey(uniqueID);
    }

    public HashMap getUniqueIDList() { //retrieve hashmap in other classes
        return uniqueIDList;
    }

    public void createAdminControlPanel() {  //create the UI/start application
        mainFrame = new JFrame("MiniTwitter");
        mainFrame.setContentPane(new AdminControl().panelMain);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setSize(800, 400);
    }

    private void errorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(mainFrame,
                errorMessage,
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
    }


    private void createUIComponents() {
        treeViewList = new UserGroup("root");
        treeViewList.setTreeComponents(new ArrayList<TreeComponent>());
        root = new DefaultMutableTreeNode(treeViewList);
        treeView = new JTree(root);
        treeView.putClientProperty("JTree.lineStyle", "Angled");
        treeView.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree,
                                                          Object value, boolean selected, boolean expanded,
                                                          boolean isLeaf, int row, boolean focused) {
                Object treeComponent = ((DefaultMutableTreeNode) value).getUserObject();

                Component c = super.getTreeCellRendererComponent(tree, value,
                        selected, expanded, isLeaf, row, focused);
                if (treeComponent instanceof UserGroup) {
                    setIcon(null);
                    setFont(new Font("Arial", Font.BOLD, 14));
                    setForeground(Color.RED);
                } else {
                    setFont(new Font("Arial", Font.PLAIN, 14));
                    setIcon(null);
                }
                return c;
            }
        });
    }

    public void getUsersTotal() {  //increment every user that we visit in root tree
        userTotalCount++;
    }

    public void getGroupsTotal() { //increment every group that we visit in root tree
        userGroupTotalCount++;
    }

    public void getTotalMessagesCount(int addToCount) { //adding all the messages together of users we visit
        totalMessagesCount += addToCount;
    }

    public void getTotalPositiveCount(int addToCount) { //adding all the positive messages together of users we visit
        totalPositiveCount += addToCount;
    }


}


