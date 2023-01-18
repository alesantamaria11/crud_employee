package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Employee {
    private JPanel Main;
    private JTextField txtName;
    private JTextField txtSalary;
    private JTextField txtMobile;
    private JButton saveButton;
    private JTable table1;
    private JButton updateButton;
    private JButton searchButton;
    private JButton deleteButton;
    private JTextField txtId;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee");
        frame.setContentPane(new Employee().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Employee() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
