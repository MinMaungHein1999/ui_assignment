package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {

    private JTextField resultField;
    private StringBuilder currentInput;
    private double num1, num2;
    private char operator;

    public CalculatorGUI() {
        // Initialize components
        currentInput = new StringBuilder();
        num1 = num2 = 0;
        operator = ' ';

        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setHorizontalAlignment(JTextField.RIGHT);

        // Create the panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        // Add buttons (numbers and operators)
        String[] buttonLabels = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "0", "C", "=", "/"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        // Set up the frame layout
        setLayout(new BorderLayout(10, 10));
        add(resultField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Set JFrame properties
        setTitle("Basic Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            // Number buttons
            currentInput.append(command);
            resultField.setText(currentInput.toString());

        } else if (command.equals("C")) {
            // Clear button
            currentInput.setLength(0);
            resultField.setText("");
            num1 = num2 = 0;
            operator = ' ';

        } else if (command.equals("=")) {
            // Equals button
            if (currentInput.length() > 0 && operator != ' ') {
                num2 = Double.parseDouble(currentInput.toString());
                double result = 0;
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            resultField.setText("Error: Divide by zero");
                            return;
                        }
                        break;
                }
                resultField.setText(String.valueOf(result));
                currentInput.setLength(0);
                num1 = num2 = 0;
                operator = ' ';
            }

        } else {
            // Operator buttons (+, -, *, /)
            if (currentInput.length() > 0) {
                num1 = Double.parseDouble(currentInput.toString());
                operator = command.charAt(0);
                currentInput.setLength(0);
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
