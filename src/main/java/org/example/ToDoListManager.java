package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListManager extends JFrame implements ActionListener {

    private JTextField taskField;
    private JButton addTaskButton, removeTaskButton, clearAllButton;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;

    public ToDoListManager() {
        // Initialize components
        taskField = new JTextField(15);
        addTaskButton = new JButton("Add Task");
        removeTaskButton = new JButton("Remove Task");
        clearAllButton = new JButton("Clear All");

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add action listeners
        addTaskButton.addActionListener(this);
        removeTaskButton.addActionListener(this);
        clearAllButton.addActionListener(this);

        // Set up layout
        setLayout(new BorderLayout(10, 10));

        // Create panel for task entry
        JPanel taskEntryPanel = new JPanel();
        taskEntryPanel.add(new JLabel("Task:"));
        taskEntryPanel.add(taskField);
        taskEntryPanel.add(addTaskButton);
        add(taskEntryPanel, BorderLayout.NORTH);

        // Create panel for task list and buttons
        JPanel taskListPanel = new JPanel(new BorderLayout(5, 5));
        taskListPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeTaskButton);
        buttonPanel.add(clearAllButton);
        taskListPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(taskListPanel, BorderLayout.CENTER);

        // Set up the frame
        setTitle("To-Do List Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTaskButton) {
            // Add task
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                listModel.addElement(task);
                taskField.setText("");  // Clear the input field
            }
        } else if (e.getSource() == removeTaskButton) {
            // Remove selected task
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            }
        } else if (e.getSource() == clearAllButton) {
            // Clear all tasks
            listModel.clear();
        }
    }

    public static void main(String[] args) {
        new ToDoListManager();
    }
}
