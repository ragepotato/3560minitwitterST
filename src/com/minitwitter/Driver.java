package com.minitwitter;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Driver {

    public static void main(String[] args){
//        User stephen = new User("Stephen");
//        User john = new User("John");
//        User sam = new User("Sam");
//        User penny = new User("Penny");
//        stephen.addFollower("John");
//        stephen.addFollowing("John");
//        stephen.addFollower("Penny");
//        System.out.println(stephen.getFollowerList());
//        System.out.println(stephen.getFollowingList());
//        UserGroup group1 = new UserGroup("group1");
//        group1.setTreeComponents(new ArrayList(Arrays.asList(new TreeComponent[] {john, penny})));
//        UserGroup root = new UserGroup("root");
//        root.setTreeComponents(new ArrayList(Arrays.asList(new TreeComponent[] {stephen, sam, group1})));
//
//
//        search(root.getTreeComponents(), "John");
//
//        System.out.println("Found: " + search( root.getTreeComponents(), "John"));
//
//
//
//        System.out.println(root.getTreeComponents());

        AdminControl adminControl = AdminControl.getInstance();
        adminControl.createAdminControlPanel();





    }


    public static User search(List<TreeComponent> fullList, String findUser) {
        User userFound = null;
        for (TreeComponent treeComponent : fullList) {
            System.out.println(treeComponent);
            if (treeComponent instanceof UserGroup) {
                System.out.println(treeComponent + " is a group.");
                search((((UserGroup) treeComponent).getTreeComponents()), findUser);
            } else {
                if (findUser == treeComponent.getUserID()) {
                    System.out.println("Found!");
                    userFound =  (User) treeComponent;
                    break;
                    
                }
            }
        }
        return userFound;

    }

}
