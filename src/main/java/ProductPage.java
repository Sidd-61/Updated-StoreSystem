/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

 
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProductPage extends javax.swing.JFrame {
    
    class DeleteCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        
        JLabel label = new JLabel("<html><u>" + value.toString() + "</u></html>");
        label.setForeground(new Color(255, 0, 0)); 
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }
}
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProductPage.class.getName());
    JTextField tfName, tfCategory, tfPrice, tfQuantity;
    JTextArea taDescription;
    JButton btnAdd;
    JTable productTable;
    DefaultTableModel tableModel;

    public ProductPage() {

    setTitle("Product Management");
    setSize(800, 500);
    setLocationRelativeTo(null);
    setResizable(false);
    //setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    initForm();      // top panel with input fields
    initTable();     // center panel with product table

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
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.setBackground(new Color(51,51,51)); 

    tfName = new JTextField();
    tfCategory = new JTextField();
    tfPrice = new JTextField();
    tfQuantity = new JTextField();
    taDescription = new JTextArea(3, 20);
    
    
    btnAdd = new JButton("ADD PRODUCT");
    btnAdd.setBackground(new Color(153, 0, 51));
    btnAdd.setForeground(Color.WHITE);
    btnAdd.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 24));

    Color labelColor = (Color.WHITE);
    Font labelFont = new Font("PMingLiU-ExtB", Font.BOLD, 18);


JLabel lblName = new JLabel("Name");
lblName.setForeground(labelColor);
lblName.setFont(labelFont);


JLabel lblCategory = new JLabel("Category");
lblCategory.setForeground(labelColor);
lblName.setFont(labelFont);


JLabel lblPrice = new JLabel("Price");
lblPrice.setForeground(labelColor);
lblName.setFont(labelFont);


JLabel lblQuantity = new JLabel("Quantity");
lblQuantity.setForeground(labelColor);
lblName.setFont(labelFont);


JLabel lblDescription = new JLabel("Description");
lblDescription.setForeground(labelColor);
lblName.setFont(labelFont);


// Now add to formPanel
formPanel.add(lblName);
formPanel.add(tfName);
formPanel.add(lblCategory);
formPanel.add(tfCategory);
formPanel.add(lblPrice);
formPanel.add(tfPrice);
formPanel.add(lblQuantity);
formPanel.add(tfQuantity);
formPanel.add(lblDescription);
formPanel.add(new JScrollPane(taDescription));
formPanel.add(new JLabel(""));  // ← This is just a blank cell to help center the button
formPanel.add(btnAdd);          // ← This makes the button show up in the last cell

    add(formPanel, BorderLayout.NORTH);

    btnAdd.addActionListener(e -> addProduct());
}
    
    private void initTable() {
    tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Price", "Qty", "Delete"}, 0);
    productTable = new JTable(tableModel);
    productTable.getColumn("Delete").setCellRenderer(new DeleteCellRenderer());
    
    productTable.getTableHeader().setBackground(new Color(51,51,51)); 
    productTable.getTableHeader().setForeground(Color.WHITE);   
    productTable.getTableHeader().setFont(new Font("Gabriola", Font.BOLD, 20));
    productTable.setRowHeight(24);
    productTable.setFont(new Font("SansSerif", Font.PLAIN, 13));

    add(new JScrollPane(productTable), BorderLayout.CENTER);

    productTable.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            int col = productTable.getSelectedColumn();
            int row = productTable.getSelectedRow();
            if (col == 5) {
                int id = Integer.parseInt(productTable.getValueAt(row, 0).toString());
                deleteProduct(id);
            }
        }
    });

    loadProducts();
}
    
    public static void main(String args[]) {
 
        SwingUtilities.invokeLater(() -> new ProductPage().setVisible(true));
    }

private void addProduct() {
    
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindatabse", "root", "");
        String sql = "INSERT INTO products (name, category, price, quantity, description) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, tfName.getText());
        ps.setString(2, tfCategory.getText());
        String rawPrice = tfPrice.getText().replace(",", "").trim();
        double price = Double.parseDouble(rawPrice);
        ps.setDouble(3, price);
        ps.setInt(4, Integer.parseInt(tfQuantity.getText()));
        ps.setString(5, taDescription.getText());
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Product added!");
        loadProducts();
        
        tfName.setText("");
        tfCategory.setText("");
        tfPrice.setText("");
        tfQuantity.setText("");
        taDescription.setText("");
        
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
}

private void loadProducts() {
    tableModel.setRowCount(0);
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindatabse", "root", "");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM products");
        while (rs.next()) {
            tableModel.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("category"),
                new java.text.DecimalFormat("₹#,##0.00").format(rs.getDouble("price")),
                rs.getInt("quantity"),
                "Delete"
            });
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Load error: " + ex.getMessage());
    }
}

private void deleteProduct(int id) {
    int confirm = JOptionPane.showConfirmDialog(null, "Delete this product?");
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindatabse", "root", "");
            PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Product deleted!");
            loadProducts();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Delete error: " + ex.getMessage());
        }
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
