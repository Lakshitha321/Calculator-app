package com.mycompany.java_calulator_app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorApp extends JFrame {
    private JTextField display;
    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewInput = true;

    // Define theme colors
    private final Color DARK_BACKGROUND = new Color(50, 50, 50);
    private final Color LIGHT_TEXT = new Color(240, 240, 240);
    private final Color NUMBER_BTN_COLOR = new Color(80, 80, 80);
    private final Color OPERATOR_BTN_COLOR = new Color(255, 153, 0); // Orange
    private final Color EQUAL_BTN_COLOR = new Color(0, 153, 204); // Blue
    private final Color CLEAR_BTN_COLOR = new Color(255, 80, 80); // Red

    public CalculatorApp() {
        setTitle("Calculator");
        setSize(350, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Set app theme
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(DARK_BACKGROUND);
        
        // Display
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 36));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(LIGHT_TEXT);
        display.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        mainPanel.add(display, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 8, 8));
        buttonPanel.setBackground(DARK_BACKGROUND);
        
        // Button layout
        String[] buttons = {
            "C", "CE", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "±", "0", ".", "="
        };

        for (String text : buttons) {
            JButton btn = createStyledButton(text);
            btn.addActionListener(new ButtonClickListener());
            buttonPanel.add(btn);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }
    
    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 24));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setForeground(LIGHT_TEXT);
        
        // Set different colors based on button type
        if (text.matches("[0-9]") || text.equals(".") || text.equals("±")) {
            btn.setBackground(NUMBER_BTN_COLOR);
        } else if (text.matches("[+\\-*/%]")) {
            btn.setBackground(OPERATOR_BTN_COLOR);
        } else if (text.equals("=")) {
            btn.setBackground(EQUAL_BTN_COLOR);
        } else {
            btn.setBackground(CLEAR_BTN_COLOR);
        }
        
        return btn;
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.matches("[0-9]")) {
                if (startNewInput) {
                    display.setText(command);
                    startNewInput = false;
                } else {
                    display.setText(display.getText() + command);
                }
            } else if (command.equals(".")) {
                if (startNewInput) {
                    display.setText("0.");
                    startNewInput = false;
                } else if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
            } else if (command.matches("[+\\-*/%]")) {
                try {
                    // If there's already an operation in progress, calculate it first
                    if (!operator.isEmpty()) {
                        calculateResult();
                    }
                    firstNumber = Double.parseDouble(display.getText());
                    operator = command;
                    startNewInput = true;
                } catch (NumberFormatException ex) {
                    display.setText("Error");
                }
            } else if (command.equals("=")) {
                calculateResult();
            } else if (command.equals("C")) {
                // Clear current input but keep operation
                display.setText("0");
                startNewInput = true;
            } else if (command.equals("CE")) {
                // Clear everything
                display.setText("0");
                firstNumber = 0;
                operator = "";
                startNewInput = true;
            } else if (command.equals("±")) {
                // Change sign
                try {
                    double currentValue = Double.parseDouble(display.getText());
                    display.setText(String.valueOf(-currentValue));
                } catch (NumberFormatException ex) {
                    display.setText("Error");
                }
            } else if (command.equals("%")) {
                // Calculate percentage
                try {
                    double currentValue = Double.parseDouble(display.getText());
                    display.setText(String.valueOf(currentValue / 100));
                } catch (NumberFormatException ex) {
                    display.setText("Error");
                }
            }
        }
        
        private void calculateResult() {
            if (!operator.isEmpty()) {
                try {
                    double secondNumber = Double.parseDouble(display.getText());
                    double result = 0;
                    
                    switch (operator) {
                        case "+": result = firstNumber + secondNumber; break;
                        case "-": result = firstNumber - secondNumber; break;
                        case "*": result = firstNumber * secondNumber; break;
                        case "/":
                            if (secondNumber != 0) {
                                result = firstNumber / secondNumber;
                            } else {
                                display.setText("Error: Div by 0");
                                operator = "";
                                startNewInput = true;
                                return;
                            }
                            break;
                        case "%": result = firstNumber % secondNumber; break;
                    }
                    
                    // Format result to avoid excessive decimal places
                    String resultStr = formatResult(result);
                    display.setText(resultStr);
                    
                    // Store result for chaining operations
                    firstNumber = result;
                    operator = "";
                    startNewInput = true;
                } catch (NumberFormatException ex) {
                    display.setText("Error");
                }
            }
        }
        
        private String formatResult(double result) {
            // Remove trailing zeros if the result is a whole number
            if (result == (long) result) {
                return String.format("%d", (long) result);
            } else {
                return String.valueOf(result);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculatorApp();
        });
    }
}