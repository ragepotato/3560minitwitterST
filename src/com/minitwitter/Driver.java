package com.minitwitter;

//Stephen Tayag
//CS3560 Assignment 2: Mini Twitter

public class Driver {

    public static void main(String[] args){
        //Singleton instance of AdminControl, create the main admin control panel
        AdminControl.getInstance().createAdminControlPanel();
    }


}
