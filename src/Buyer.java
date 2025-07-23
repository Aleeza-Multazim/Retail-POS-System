import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Buyer {
    Buyer() {
        Frame F = new Frame();

        Label label1 = new Label(" Name ");
        Label label2 = new Label(" Contact No ");
        Label label3 = new Label(" Email ");
        Label label4 = new Label(" Address ");
        Label label5 = new Label(" Product Name ");
        Label label6 = new Label(" Rate ");
        Label label7 = new Label(" Quantity ");
        Label label8 = new Label(" Date ");

        TextField tf = new TextField();
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        TextField tf3 = new TextField();
        TextField tf4 = new TextField();
        TextField tf5 = new TextField();
        TextField tf6 = new TextField();
        TextField tf7 = new TextField();

        Button b1 = new Button(" Submit");
        Button b2 = new Button("Cancel");

        // Set bounds
        label1.setBounds(20, 80, 80, 30);   tf.setBounds(120, 80, 150, 30);
        label2.setBounds(20, 130, 80, 30);  tf1.setBounds(120, 130, 150, 30);
        label3.setBounds(20, 170, 80, 30);  tf2.setBounds(120, 170, 150, 30);
        label4.setBounds(20, 210, 80, 30);  tf3.setBounds(120, 210, 150, 30);
        label5.setBounds(20, 250, 100, 30); tf4.setBounds(120, 250, 150, 30);
        label6.setBounds(20, 290, 80, 30);  tf5.setBounds(120, 290, 150, 30);
        label7.setBounds(20, 330, 80, 30);  tf6.setBounds(120, 330, 150, 30);
        label8.setBounds(20, 370, 80, 30);  tf7.setBounds(120, 370, 150, 30);

        b1.setBounds(30, 450, 80, 30); b1.setBackground(Color.pink);
        b2.setBounds(120, 450, 80, 30); b2.setBackground(Color.pink);

        // Add components
        F.add(label1); F.add(tf);
        F.add(label2); F.add(tf1);
        F.add(label3); F.add(tf2);
        F.add(label4); F.add(tf3);
        F.add(label5); F.add(tf4);
        F.add(label6); F.add(tf5);
        F.add(label7); F.add(tf6);
        F.add(label8); F.add(tf7);
        F.add(b1); F.add(b2);

        F.setSize(400, 550);
        F.setTitle("Buyer_Info");
        F.setLayout(null);
        F.setVisible(true);

        // Submit button event
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Load MySQL JDBC Driver
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/buyer_db", "root", "aleeza12345");

                    String query = "INSERT INTO buyer_info (name, contact_no, email, address, product_name, rate, quantity, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, tf.getText());
                    pst.setString(2, tf1.getText());
                    pst.setString(3, tf2.getText());
                    pst.setString(4, tf3.getText());
                    pst.setString(5, tf4.getText());
                    pst.setDouble(6, Double.parseDouble(tf5.getText()));
                    pst.setInt(7, Integer.parseInt(tf6.getText()));
                    pst.setString(8, tf7.getText());

                    int result = pst.executeUpdate();
                    if (result > 0) {
                        JOptionPane.showMessageDialog(F, "Data inserted successfully!");
                    }

                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(F, "Error: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        new Buyer();
    }
}
