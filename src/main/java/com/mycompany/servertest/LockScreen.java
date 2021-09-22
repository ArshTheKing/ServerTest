/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servertest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Azael
 */
public class LockScreen {
    
    
    
    public LockScreen(){
      JFrame frame = MainFrame();
      loadLockFrame(frame);
      frame.setVisible(true);
    }
    
    private JFrame MainFrame(){
      JFrame frame = new JFrame();
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true); //Uncomment at realese
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setAlwaysOnTop(true);
      frame.setResizable(false);
      frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
      return frame;
    }
    
    private Box box = new Box(BoxLayout.Y_AXIS);
    private javax.swing.JTextField user=new JTextField(20);
    private javax.swing.JTextField password=new JPasswordField(20);
    private void loadLockFrame(JFrame frame) {
        
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("User and Password"));
        jPanel.add(user);
        jPanel.add(password);
        JButton verify = new JButton("Submit");
        verify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = user.getText();
                String text1 = password.getText();
                if(text.equals("admin")&&text1.equals("admin")) frame.dispose();
            }
        });
        jPanel.add(verify);
        frame
                .getRootPane().setDefaultButton(verify);
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
              System.exit(0);
          }
        });
        jPanel.add(closeButton);
        //jPanel.add(password);
        
        //jPanel.add()
        //jPanel.setAlignmentX(frame.CENTER_ALIGNMENT);
        //jPanel.setAlignmentY(frame.CENTER_ALIGNMENT);
        box.add(Box.createVerticalGlue());
        box.add(jPanel);     
        box.add(Box.createVerticalGlue());
        Dimension expectedDimension = new Dimension(250, 150);
        jPanel.setPreferredSize(expectedDimension);
        jPanel.setMaximumSize(expectedDimension);
        jPanel.setMinimumSize(expectedDimension);
        frame.add(box);
        box.repaint();
    }
}
