package com.minitwitter;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;

public class Driver {

    public static void main(String[] args){
        User stephen = new User("Stephen");
        User john = new User("John");
        User sam = new User("Sam");
        User penny = new User("Penny");
        stephen.addFollower("John");
        stephen.addFollowing("John");
        stephen.addFollower("Penny");
        System.out.println(stephen.getFollowerList());
        System.out.println(stephen.getFollowingList());
        UserGroup group1 = new UserGroup("group1");

        group1.setTreeComponents(new ArrayList(Arrays.asList(new TreeComponent[] {john, penny})));
        UserGroup root = new UserGroup("root");
        root.setTreeComponents(new ArrayList(Arrays.asList(new TreeComponent[] {stephen, sam, group1})));
        User bingo = new User("bingo");
        //root.setTreeComponents(root.getTreeComponents().add(bingo));
        root.addToTree(bingo);
        root.printTree();
        System.out.println(root.getTreeComponents());


//        boolean keepGoing = true;
//        Scanner userInput = new Scanner(System.in);
//        while (keepGoing){
//            System.out.println("Enter new group");
//            String userID = userInput.nextLine();
//        }



    }

}
