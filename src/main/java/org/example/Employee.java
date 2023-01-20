package org.example;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Employee {
    private JPanel Main;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtSalary;
    private JTextField txtMobile;
    private JButton saveButton;
    private JTable table1;
    private JButton updateButton;
    private JButton searchButton;
    private JButton deleteButton;


    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("Employee");
        frame.setContentPane(new Employee().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement pst;

    public void connect(){

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bd_company", "root", "alejandra11");
            System.out.println("Success");

        }
        catch(ClassNotFoundException ex){

        } catch(SQLException ex){

            ex.printStackTrace();

        }
    }


    void tableLoad() throws SQLException {

        try {
            pst = con.prepareStatement("select * from employee");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

    }





    public Employee() throws SQLException {
        connect();
        tableLoad();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nameEmployee, salaryEmployee, mobileEmployee;

                nameEmployee = txtName.getText();
                salaryEmployee = txtSalary.getText();
                mobileEmployee = txtMobile.getText();

                    try {
                        pst = con.prepareStatement("insert into employee(nameEmployee, salaryEmployee, mobileEmployee) values(?,?,?)");
                        pst.setString(1, nameEmployee);
                        pst.setString(2, salaryEmployee);
                        pst.setString(3, mobileEmployee);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Record added!!");
                        txtName.setText("");
                        txtSalary.setText("");
                        txtMobile.setText("");
                        txtName.requestFocus();


                    }
                    catch (SQLException e1){
                        e1.printStackTrace();
                }

            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String idEmployee = txtId.getText();

                    pst = con.prepareStatement("select nameEmployee, salaryEmployee, mobileEmployee from employee where idEmployee = ?");
                    pst.setString(1, idEmployee);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {

                        String nameEmployee = rs.getString(1);
                        String salaryEmployee = rs.getString(2);
                        String mobileEmployee = rs.getString(3);

                        txtName.setText(nameEmployee);
                        txtSalary.setText(salaryEmployee);
                        txtMobile.setText(mobileEmployee);

                    } else {

                        txtName.setText("");
                        txtSalary.setText("");
                        txtMobile.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Employee id");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }

            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idEmployee, nameEmployee, salaryEmployee, mobileEmployee;

                idEmployee = txtId.getText();
                nameEmployee = txtName.getText();
                salaryEmployee = txtSalary.getText();
                mobileEmployee = txtMobile.getText();

                try {
                    pst = con.prepareStatement("update employee set nameEmployee = ?, salaryEmployee = ?, mobileEmployee = ? where id = ?");
                    pst.setString(1, nameEmployee);
                    pst.setString(2, salaryEmployee);
                    pst.setString(3, mobileEmployee);
                    pst.setString(4, idEmployee);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updated!");

                    tableLoad();
                    txtName.setText("");
                    txtSalary.setText("");
                    txtMobile.setText("");
                    txtName.requestFocus();

                }

                catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });
    }


}
