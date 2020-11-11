package com.minitwitter;

public class CountTotalPositiveVisitor implements TreeComponentVisitor {
    @Override
    public void visitUser(User user) {
        int count = 0;
        String[] positiveKeywords = {"great", "good", "nice", "awesome", "lovely", "excellent"};
        for (String tweet : user.getTwitterMessages()){ //with each user's list of tweets, looks for positive keywords
            for (String keyword : positiveKeywords) {
                if (tweet.contains(keyword)) {
                    count++; //if found, increments count
                }
            }
        }
        AdminControl.getInstance().getTotalPositiveCount(count); //add count to total so far
    }

    @Override
    public void visitUserGroup(UserGroup userGroup) {
        if (userGroup.getTreeComponents() != null) {
            for (TreeComponent treeComponent : userGroup.getTreeComponents()) {
                treeComponent.accept(this);
            }
        }

    }
}
