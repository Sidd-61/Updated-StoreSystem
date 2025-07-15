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
    JTextField tfName, tfEmail, tfPhone, tfSearch;
    JTextArea taAddress;
    JButton btnAdd, btnClear, btnSearch;
    JTable buyerTable;
    DefaultTableModel tableModel;
    JLabel lblEditing;

    
    public BuyerPage() {
         setTitle("Buyer Management");
        setSize(800, 500);
        setLocationRelativeTo(null);
        //setResizable(false);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initForm();    // Top panel with form
        initTable();   // Center panel with JTable
        initSearchBar();

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
    JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
    formPanel.setBackground(new Color(51, 51, 51));  // Dark background for elegance

    tfName = new JTextField();
    tfEmail = new JTextField();
    tfPhone = new JTextField();
    taAddress = new JTextArea(3, 20);
    lblEditing = new JLabel(" ");
    lblEditing.setForeground(Color.YELLOW);
    lblEditing.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 14));

    btnAdd = new JButton("ADD BUYER");
    btnAdd.setBackground(new Color(153, 0, 51));       // Deep red button
    btnAdd.setForeground(Color.WHITE);                 // White text for contrast
    btnAdd.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 24));
    btnAdd.setPreferredSize(new Dimension(280, 40));

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
    
    formPanel.add(lblEditing);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setBackground(new Color(51, 51, 51));
        actionPanel.add(btnAdd);
        actionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        btnClear = new JButton("CLEAR");
        btnClear.setBackground(new Color(153, 0, 51));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 18));
        btnClear.setPreferredSize(new Dimension(120, 40));
        actionPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        actionPanel.add(btnClear);

        formPanel.add(actionPanel);
        add(formPanel, BorderLayout.NORTH);

        btnAdd.addActionListener(e -> addBuyer());

        btnClear.addActionListener(e -> {
            resetForm();
            lblEditing.setText(" ");
            btnAdd.setText("ADD BUYER");
            for (ActionListener al : btnAdd.getActionListeners()) btnAdd.removeActionListener(al);
            btnAdd.addActionListener(ev -> addBuyer());
        });
    

    
        /*formPanel.add(new JLabel("")); // Spacer to align button
    formPanel.add(btnAdd);         // Button in final cell

    add(formPanel, BorderLayout.NORTH);
    btnAdd.addActionListener(e -> addBuyer()); */
}
    
    private void initSearchBar() {
        tfSearch = new JTextField(20);
        btnSearch = new JButton("SEARCH");
        btnSearch.setBackground(new Color(153, 0, 51));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 18));

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(new Color(51, 51, 51));

        JLabel searchLabel = new JLabel("Search by Name or Email");
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));

        searchPanel.add(searchLabel);
        searchPanel.add(tfSearch);
        searchPanel.add(btnSearch);

        add(searchPanel, BorderLayout.SOUTH);

        btnSearch.addActionListener(e -> searchBuyers());
    }
    
    
      private void initTable() {
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone", "Address", "Delete", "Edit"}, 0);
        buyerTable = new JTable(tableModel);
        buyerTable.getColumn("Delete").setCellRenderer(new DeleteCellRenderer());
        buyerTable.getColumn("Edit").setCellRenderer(new DeleteCellRenderer());

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
                int id = Integer.parseInt(buyerTable.getValueAt(row, 0).toString());

                if (col == 5) deleteBuyer(id);
                else if (col == 6) populateFieldsForEdit(id);
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
     
     
     private void updateBuyer(int id) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindatabse", "root", "");
            String sql = "UPDATE buyers SET name=?, email=?, phone=?, address=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tfName.getText().trim());
            ps.setString(2, tfEmail.getText().trim());
            ps.setString(3, tfPhone.getText().trim());
            ps.setString(4, taAddress.getText().trim());
            ps.setInt(5, id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Buyer updated!");
            resetForm();
            btnAdd.setText("ADD BUYER");
            lblEditing.setText(" ");

            for (ActionListener al : btnAdd.getActionListeners()) btnAdd.removeActionListener(al);
            btnAdd.addActionListener(e -> addBuyer());
            loadBuyers();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Update error: " + ex.getMessage());
        }
    }

    private void populateFieldsForEdit(int id) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindatabse", "root", "");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM buyers WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tfName.setText(rs.getString("name"));
                tfEmail.setText(rs.getString("email"));
                tfPhone.setText(rs.getString("phone"));
                taAddress.setText(rs.getString("address"));

                lblEditing.setText("Currently Editing: ID " + id);
                btnAdd.setText("UPDATE BUYER");

                for (ActionListener al : btnAdd.getActionListeners()) btnAdd.removeActionListener(al);
                btnAdd.addActionListener(e -> updateBuyer(id));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Edit error: " + ex.getMessage());
        }
    }

    private void searchBuyers() {
        String keyword = tfSearch.getText().trim().toLowerCase();
        boolean found = false;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindatabse", "root", "");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM buyers WHERE LOWER(name) LIKE ? OR LOWER(email) LIKE ?");
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            DefaultTableModel tempModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone", "Address", "Delete", "Edit"}, 0);

            while (rs.next()) {
                found = true;
                tempModel.addRow(new Object[]{
                    rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                    rs.getString("phone"), rs.getString("address"), "Delete", "Edit"
                });
            }

            if (found) {
        tableModel.setRowCount(0);
        for (int i = 0; i < tempModel.getRowCount(); i++) {
            tableModel.addRow(new Object[]{
                tempModel.getValueAt(i, 0),
                tempModel.getValueAt(i, 1),
                tempModel.getValueAt(i, 2),
                tempModel.getValueAt(i, 3),
                tempModel.getValueAt(i, 4),
                "Delete",
                "Edit"
            });
        }
    } else {
        JOptionPane.showMessageDialog(null, "No matching buyer found :(");
    }

} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Search error: " + ex.getMessage());
}
    }
private void resetForm() {
    tfName.setText("");
    tfEmail.setText("");
    tfPhone.setText("");
    taAddress.setText("");
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
                    "Delete",
                    "Edit"
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
