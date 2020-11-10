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
    private JList listOF;
    private static HashMap uniqueIDList;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private UserGroup treeViewList;
    private static int userTotalCount;
    private static int userGroupTotalCount;


    private static AdminControl pointer;
    private JFrame mainFrame;

    public static AdminControl getInstance()  {
        if (pointer == null){
            pointer = new AdminControl();
        }
        return pointer;
    }

    private AdminControl() {
        treeViewList = new UserGroup("root");
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



        addNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                TreeSelectionModel checkSelected = treeView.getSelectionModel();

                if (checkSelected.getSelectionCount() > 0) {
                    DefaultMutableTreeNode selectedGroupUI = (DefaultMutableTreeNode) treeView.getSelectionPath().getLastPathComponent();
                    if (selectedGroupUI.getUserObject() instanceof UserGroup) {   //first, check if we are trying to add to a group
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

                            System.out.println(treeViewList.getTreeComponents());
                            enterUserID.setText("");
                        } else {
                            errorDialog("ERROR: " + getUserID + " is already taken.");
                        }
                    } else {
                        errorDialog("ERROR: " + selectedGroupUI + " is not a User Group.");

                    }

                }
                else{
                    errorDialog("ERROR: Choose where to add on tree.");
                }
            }
        });
        addNewUserGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TreeSelectionModel checkSelected = treeView.getSelectionModel();

                if (checkSelected.getSelectionCount() > 0) {
                    DefaultMutableTreeNode selectedGroupUI = (DefaultMutableTreeNode) treeView.getSelectionPath().getLastPathComponent();
                    if (selectedGroupUI.getUserObject() instanceof UserGroup) {
                        String getUserGroupID = enterUserGroupID.getText();
                        if (isUniqueID(getUserGroupID)) {

                            UserGroup addedUserGroup = new UserGroup(getUserGroupID);
                            uniqueIDList.put(getUserGroupID, addedUserGroup);
                            System.out.println(addedUserGroup.getUserID());
                            treeViewList.addToTree(addedUserGroup);
                            actionText.setText("Added " + addedUserGroup.getUserID() + " to the tree.");
                            DefaultMutableTreeNode newUserGroupNode = new DefaultMutableTreeNode(addedUserGroup);
                            selectedGroupUI.add(newUserGroupNode);
                            DefaultTreeModel model = (DefaultTreeModel) treeView.getModel();
                            model.reload();

                            System.out.println(treeViewList.getTreeComponents());
                            enterUserGroupID.setText("");
                        } else {
                            actionText.setText("ERROR: " + getUserGroupID + " is already taken.");
                        }
                    } else {
                        actionText.setText("ERROR: " + selectedGroupUI + " is not a User Group.");
                    }
                }
                else{
                    actionText.setText("ERROR: Please choose where to add.");
                }
            }
        });

        showUserTotalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userTotalCount = 0;
                CountTotalUsersVisitor userCount = new CountTotalUsersVisitor();
                treeViewList.accept(userCount);
                actionText.setText("Total user count: " + userTotalCount);
            }
        });

        showGroupTotalButton.addActionListener(new ActionListener() {
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
                    System.out.println("Opened " + selectedUser);
                    UserViewUI newUserView = new UserViewUI((User) selectedUser.getUserObject());
                    ((User) selectedUser.getUserObject()).setUserViewUI(newUserView);
                    newUserView.showUserViewUI();
                    System.out.println(newUserView.toString());
                    System.out.println(getUniqueIDList());
                    System.out.println(uniqueIDList);

                }
                else{
                    System.out.println("Not a user");
                }

            }
        });
    }

    public boolean isUniqueID(String uniqueID) {
        return !uniqueIDList.containsKey(uniqueID);
    }

    public HashMap getUniqueIDList() {
        return uniqueIDList;
    }

    public void createAdminControlPanel(){
        mainFrame = new JFrame("App");
        mainFrame.setContentPane(new AdminControl().panelMain);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setSize(800,400);
    }




//    public static void main(String[] args) {
//        JFrame frame = new JFrame("App");
//        frame.setContentPane(new AdminControl().panelMain);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }

    private void errorDialog(String errorMessage){
        JOptionPane.showMessageDialog(mainFrame,
                errorMessage,
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
    }


    private void createUIComponents() {
        treeViewList = new UserGroup("root");
        treeViewList.setTreeComponents(new ArrayList<TreeComponent>());
        root = new DefaultMutableTreeNode(treeViewList);


        //treeModel = new DefaultTreeModel(root);
        treeView = new JTree(root);
        treeView.putClientProperty("JTree.lineStyle", "Angled");
        //treeView.putClientProperty("JTree.lineStyle", "Horizontal");
        treeView.setCellRenderer(new DefaultTreeCellRenderer() {
            JLabel treeIconLabel = new JLabel();

            //private ImageIcon loadIcon = new ImageIcon(getClass().getResource("imageFolder/groupIcon.png"));
            private Icon saveIcon = UIManager.getIcon("OptionPane.informationIcon");
            private Icon newIcon = new ImageIcon(getClass().getResource("imageFolder/groupIcon.png"));
            @Override
            public Component getTreeCellRendererComponent(JTree tree,
                                                          Object value, boolean selected, boolean expanded,
                                                          boolean isLeaf, int row, boolean focused) {
                Object treeComponent = ((DefaultMutableTreeNode) value).getUserObject();

                Component c = super.getTreeCellRendererComponent(tree, value,
                        selected, expanded, isLeaf, row, focused);
                if (treeComponent instanceof UserGroup){
                    setIcon(null);
                    setFont(new Font("Arial", Font.BOLD , 14));
                }
                else{
                    setFont(new Font("Arial", Font.PLAIN, 14));
                    setIcon(null);
                }

                    //setIcon(saveIcon);
                return c;
            }
        });

    }

    public void getUsersTotal(){
        userTotalCount++;
    }


    public void getGroupsTotal(){
        userGroupTotalCount++;
    }




}


