/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.java_calulator_app;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextField;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Chamidu
 */
public class Java_Calulator_app {

    public static void main(String[] args) {
        
        calbody cal = new calbody();
        
        
    }
}
class calbody extends JFrame {
    calbody(){
        
        setVisible(true);
        setTitle("Calculator");
        setSize(300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        BorderLayout bd = new BorderLayout();
        
        JPanel p1 = new JPanel();
        p1.setBackground(Color.red);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.blue);
        
        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        
        TextField t1 = new TextField(30);
        p1.add(t1);
        
        //create grid
        GridLayout gl = new GridLayout(3,3);
        p2.setLayout(gl);
        
        Button b1 = new Button();
        b1.setLabel("1");
        p2.add(b1);
        
        Button b2 = new Button("2");
        p2.add(b2);
        
        Button b3 = new Button("3");
        p2.add(b3);
        
        Button b4 = new Button("4");
        p2.add(b4);
        
        Button b5 = new Button("5");
        p2.add(b5);
        
        Button b6 = new Button("6");
        p2.add(b6);
        
        Button b7 = new Button("7");
        p2.add(b7);
        
        Button b8 = new Button("");
        p2.add(b8);
        
        Button b9 = new Button("9");
        p2.add(b9);
        
        MenuBar mb = new MenuBar();
        setMenuBar(mb);
        
        Menu m1 = new Menu("File");
        Menu m2 = new Menu("Edit");
        Menu m3 = new Menu("View");
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
    
    
    MenuItem m_new = new MenuItem("New File");
    MenuItem m_save = new MenuItem("Save");
    MenuItem m_Exit = new MenuItem("Exit");
    
    m1.add(m_new);
    m2.add(m_save);
    m3.add(m_Exit);
}
}