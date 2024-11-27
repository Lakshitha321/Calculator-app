package com.mycompany.java_calulator_app;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Java_Calulator_app {

    public static void main(String[] args) {
        calbody cal = new calbody();
    }
}

class calbody extends JFrame {
    double firstNumber, secondNumber, result;
    String operator;

    calbody() {
        setVisible(true);
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BorderLayout bd = new BorderLayout();
        setLayout(bd);

        JPanel p1 = new JPanel();
        p1.setBackground(Color.DARK_GRAY);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.BLUE);

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);

        TextField t1 = new TextField(15);
        t1.setFont(new Font("", 0, 30));
        p1.add(t1);

        // Create grid layout for buttons
        GridLayout gl = new GridLayout(4, 4); // Adjusted for extra row for operators
        p2.setLayout(gl);

        // Number buttons
        Button[] numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].setFont(new Font("", 0, 30));
            numberButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    t1.setText(t1.getText() + ((Button) e.getSource()).getLabel());
                }
            });
            p2.add(numberButtons[i]);
        }

        // Special buttons
        Button dot = new Button(".");
        dot.setFont(new Font("", 0, 30));
        dot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!t1.getText().contains(".")) {
                    t1.setText(t1.getText() + ".");
                }
            }
        });
        p2.add(dot);

        Button clr = new Button("CE");
        clr.setFont(new Font("", 0, 30));
        clr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                firstNumber = 0.0;
                secondNumber = 0.0;
                operator = "";
            }
        });
        p2.add(clr);

        // Operator buttons
        Button[] operatorButtons = new Button[4];
        String[] operators = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            operatorButtons[i] = new Button(operators[i]);
            operatorButtons[i].setFont(new Font("", 0, 30));
            operatorButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!t1.getText().isEmpty()) {
                        firstNumber = Double.parseDouble(t1.getText());
                        operator = ((Button) e.getSource()).getLabel();
                        t1.setText("");
                    }
                }
            });
            p2.add(operatorButtons[i]);
        }

        // Equals button
        Button equals = new Button("=");
        equals.setFont(new Font("", 0, 30));
        equals.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!t1.getText().isEmpty()) {
                    secondNumber = Double.parseDouble(t1.getText());
                    result = calculate(firstNumber, secondNumber, operator);
                    t1.setText(String.valueOf(result));
                    firstNumber = 0.0;
                    secondNumber = 0.0;
                    operator = "";
                }
            }
        });
        p2.add(equals);

        // Set fonts for all buttons
        for (Button btn : numberButtons) {
            btn.setFont(new Font("", 0, 30));
        }
        for (Button btn : operatorButtons) {
            btn.setFont(new Font("", 0, 30));
        }

        // Menu bar setup
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
        MenuItem m_exit = new MenuItem("Exit");

        m1.add(m_new);
        m2.add(m_save);
        m3.add(m_exit);

        m_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, "Exiting Calculator...");
                System.exit(0);
            }
        });
    }

    public double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b != 0) {
                    return a / b;
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Division by zero!");
                    return 0;
                }
            default:
                return 0;
        }
    }
}
