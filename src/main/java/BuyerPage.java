/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BuyerPage extends javax.swing.JFrame {
    class DeleteCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

            JLabel label = new JLabel("<html><u>" + value.toString() + "</u></html>");
            label.setForeground(new Color(255, 0, 0)); // Red to highlight "Delete"
            label.setFont(new Font("SansSerif", Font.BOLD, 12));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            return label;
        }
    }
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BuyerPage.class.getName());
    JTextField tfName, tfEmail, tfPhone;
    JTextArea taAddress;
    JButton btnAdd;
    JTable buyerTable;
    DefaultTableModel tableModel;
    
    public BuyerPage() {
         setTitle("Buyer Management");
        setSize(800, 500);
        setLocationRelativeTo(null);
    setResizable(false);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initForm();    // Top panel with form
        initTable();   // Center panel with JTable

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     
    private void initForm() {
    JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
    formPanel.setBackground(new Color(51, 51, 51));  // Dark background for elegance

    tfName = new JTextField();
    tfEmail = new JTextField();
    tfPhone = new JTextField();
    taAddress = new JTextArea(3, 20);

    btnAdd = new JButton("ADD BUYER");
    btnAdd.setBackground(new Color(153, 0, 51));       // Deep red button
    btnAdd.setForeground(Color.WHITE);                 // White text for contrast
    btnAdd.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 24));

    Color labelColor = (Color.WHITE);          
    Font labelFont = new Font("PMingLiU-ExtB", Font.BOLD, 18);

    JLabel lblName = new JLabel("Name");
    lblName.setForeground(labelColor);
    lblName.setFont(labelFont);

    JLabel lblEmail = new JLabel("Email");
    lblEmail.setForeground(labelColor);
    lblEmail.setFont(labelFont);

    JLabel lblPhone = new JLabel("Phone");
    lblPhone.setForeground(labelColor);
    lblPhone.setFont(labelFont);

    JLabel lblAddress = new JLabel("Address");
    lblAddress.setForeground(labelColor);
    lblAddress.setFont(labelFont);

    // Add components to panel
    formPanel.add(lblName);
    formPanel.add(tfName);

    formPanel.add(lblEmail);
    formPanel.add(tfEmail);

    formPanel.add(lblPhone);
    formPanel.add(tfPhone);

    formPanel.add(lblAddress);
    formPanel.add(new JScrollPane(taAddress));

    formPanel.add(new JLabel("")); // Spacer to align button
    formPanel.add(btnAdd);         // Button in final cell

    add(formPanel, BorderLayout.NORTH);
    btnAdd.addActionListener(e -> addBuyer());
}

      private void initTable() {
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone", "Address", "Delete"}, 0);
        buyerTable = new JTable(tableModel);
        buyerTable.getColumn("Delete").setCellRenderer(new DeleteCellRenderer());

        buyerTable.getTableHeader().setBackground(new Color(51,51,51));
        buyerTable.getTableHeader().setForeground(Color.WHITE);
        buyerTable.getTableHeader().setFont(new Font("Gabriola", Font.BOLD, 20));
        buyerTable.setRowHeight(24);
        buyerTable.setFont(new Font("SansSerif", Font.PLAIN, 13));

        add(new JScrollPane(buyerTable), BorderLayout.CENTER);

        buyerTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int col = buyerTable.getSelectedColumn();
                int row = buyerTable.getSelectedRow();
                if (col == 5) {
                    int id = Integer.parseInt(buyerTable.getValueAt(row, 0).toString());
                    deleteBuyer(id);
                }
            }
        });

        loadBuyers();
    }


    public static void main(String [] args) {

        SwingUtilities.invokeLater(() -> new BuyerPage().setVisible(true));
    }
    
     private void addBuyer() {
       String name = tfName.getText().trim();
    String email = tfEmail.getText().trim();
    String phone = tfPhone.getText().trim();
    String address = taAddress.getText().trim();

    // Basic blank field check first
    if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill in all buyer details.",
            "Missing Information", JOptionPane.WARNING_MESSAGE);
        return; // Stop execution if any field is blank
    }
     // 📨 Email format check
    if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
    JOptionPane.showMessageDialog(null, "Please enter a valid email address.",
        "Invalid Email", JOptionPane.WARNING_MESSAGE);
    return;
}

    // 📱 Phone number format check (10-digit, numbers only)
    if (!phone.matches("\\d{10}")) {
    JOptionPane.showMessageDialog(null, "Please enter a valid 10-digit phone number.",
        "Invalid Phone", JOptionPane.WARNING_MESSAGE);
    return;
} 
    phone = "+91 " + phone; 
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindatabse", "root", "");
            String sql = "INSERT INTO buyers (name, email, phone, address) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tfName.getText().trim());
            ps.setString(2, tfEmail.getText().trim());
            ps.setString(3, tfPhone.getText().trim());
            ps.setString(4, taAddress.getText().trim());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Buyer added!");
            loadBuyers();

            tfName.setText("");
            tfEmail.setText("");
            tfPhone.setText("");
            taAddress.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void loadBuyers() {
        tableModel.setRowCount(0);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindatabse", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM buyers");
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    "Delete"
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Load error: " + ex.getMessage());
        }
    }

    private void deleteBuyer(int id) {
        int confirm = JOptionPane.showConfirmDialog(null, "Delete this buyer?");
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindatabse", "root", "");
                PreparedStatement ps = con.prepareStatement("DELETE FROM buyers WHERE id=?");
                ps.setInt(1, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Buyer deleted!");
                loadBuyers();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Delete error: " + ex.getMessage());
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
