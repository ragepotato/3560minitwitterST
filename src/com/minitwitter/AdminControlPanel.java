//package com.minitwitter;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//
//public class AdminControlPanel implements ActionListener {
//
//    int count;
//    JLabel label;
//    JButton button;
//    JButton createUserButton;
//    JButton createUserGroupButton;
//    JTextField enterUserIDText;
//    JPanel panel1;
//    JPanel panel2;
//    JFrame frame;
//    String userID;
//
//    public AdminControlPanel(){
//        count = 0;
//        frame = new JFrame();
//        panel1 = new JPanel();
//        panel2 = new JPanel();
//        label = new JLabel("Number of clicks: 0");
//        createUserButton = new JButton("Add New com.minitwitter.User");
//        createUserGroupButton = new JButton("Add New Group");
//
//        enterUserIDText = new JTextField(10);
//        userID = enterUserIDText.getText();
//        createUserButton.addActionListener(this);
//
//
//
//        panel1.setBorder(BorderFactory.createEmptyBorder(100, 100, 50, 100));
//        panel1.setLayout(new FlowLayout());
//        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
//
//
//
//        //panel1.add(panel2);
//        panel1.add(label);
//        panel1.add(enterUserIDText);
//        panel1.add(createUserButton);
//        panel1.add(createUserGroupButton);
//
//        label.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                System.out.println("Hello");
//            }
//        });
//
//        frame.add(panel1, BorderLayout.CENTER);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args){
//        new AdminControlPanel();
//    }
//
//    public void actionPerformed(ActionEvent e){
//        if (e.getSource() == createUserButton){
//            System.out.println("userID: " + userID);
////            count++;
////            String printList = "<html>";
////            for (int i = 0; i < 5; i++){
////                printList = printList + " added a new line " + i + "<br> ";
////            }
////            label.setText(printList + count);
//        }
//
//    }
//
//
//
//
//}
