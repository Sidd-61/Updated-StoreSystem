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
    JTextField tfSearch;
    JButton btnSearch;
    JLabel lblEditing;
    JButton btnClear;

    public ProductPage() {

    setTitle("Product Management");
    setSize(800, 500);
    setLocationRelativeTo(null);
    //setResizable(false);
    //setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    initForm();      // top panel with input fields
    initTable();     // center panel with product table
     initSearchBar();  // bottom search bar 

    }
    
    private void initSearchBar() {
    tfSearch = new JTextField(20);
    btnSearch = new JButton("SEARCH");
    btnSearch.setBackground(new Color(153, 0, 51));
    btnSearch.setForeground(Color.WHITE);
    btnSearch.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 18));

    JPanel searchPanel = new JPanel(new FlowLayout());
    searchPanel.setBackground(new Color(51, 51, 51));

    JLabel searchLabel = new JLabel("Search by Name or Category");
    searchLabel.setForeground(Color.WHITE);
    searchLabel.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));

    searchPanel.add(searchLabel);
    searchPanel.add(tfSearch);
    searchPanel.add(btnSearch);

    add(searchPanel, BorderLayout.SOUTH);

    btnSearch.addActionListener(e -> searchProducts());
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
        
        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        formPanel.setBackground(new Color(51,51,51)); 

    tfName = new JTextField();
    tfCategory = new JTextField();
    tfPrice = new JTextField();
    tfQuantity = new JTextField();
    taDescription = new JTextArea(3, 20);
    taDescription.setLineWrap(true);
    taDescription.setWrapStyleWord(true);
    
    
    btnAdd = new JButton("ADD PRODUCT");
    btnAdd.setBackground(new Color(153, 0, 51));
    btnAdd.setForeground(Color.WHITE);
    btnAdd.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 24));
    btnAdd.setPreferredSize(new Dimension(280, 40));
    
    btnClear = new JButton("CLEAR");
    btnClear.setBackground(new Color(153, 0, 51));
    btnClear.setForeground(Color.WHITE);
    btnClear.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 18));
    btnClear.setPreferredSize(new Dimension(120, 40));  // matches btnAdd nicely
    
    btnClear.addActionListener(e -> {
    resetForm();
    lblEditing.setText(" ");
    btnAdd.setText("ADD PRODUCT");
    for (ActionListener al : btnAdd.getActionListeners()) {
        btnAdd.removeActionListener(al);
    }
    btnAdd.addActionListener(ev -> addProduct());
});

    lblEditing = new JLabel(" ");  // Empty until editing starts
    lblEditing.setForeground(Color.YELLOW);
    lblEditing.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 14));
    
    JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    actionPanel.setBackground(new Color(51, 51, 51));
    actionPanel.add(btnAdd);
    actionPanel.add(Box.createRigidArea(new Dimension(10, 0)));  // adds spacing between buttons
    actionPanel.add(btnClear);

    Color labelColor = (Color.WHITE);
    Font labelFont = new Font("PMingLiU-ExtB", Font.BOLD, 18);


JLabel lblName = new JLabel("Name");
lblName.setForeground(labelColor);
lblName.setFont(labelFont);


JLabel lblCategory = new JLabel("Category");
lblCategory.setForeground(labelColor);
lblCategory.setFont(labelFont);


JLabel lblPrice = new JLabel("Price");
lblPrice.setForeground(labelColor);
lblPrice.setFont(labelFont);


JLabel lblQuantity = new JLabel("Quantity");
lblQuantity.setForeground(labelColor);
lblQuantity.setFont(labelFont);


JLabel lblDescription = new JLabel("Description");
lblDescription.setForeground(labelColor);
lblDescription.setFont(labelFont);


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
formPanel.add(lblEditing);      // left column: status label
formPanel.add(actionPanel);    // right column: aligned buttons
//formPanel = new JPanel(new GridLayout(7, 2));

    add(formPanel, BorderLayout.NORTH);

    btnAdd.addActionListener(e -> addProduct());
}
    
    
    
    
    private void initTable() {
    tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Price", "Qty", "Delete", "Edit"}, 0);
    productTable = new JTable(tableModel);
    productTable.getColumn("Delete").setCellRenderer(new DeleteCellRenderer());
    productTable.getColumn("Edit").setCellRenderer(new DeleteCellRenderer());
    
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
        int id = Integer.parseInt(productTable.getValueAt(row, 0).toString());

        if (col == 5) { // Delete column
            deleteProduct(id);
        } else if (col == 6) { // Edit column
            populateFieldsForEdit(id);
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
                "Delete",
                "Edit"
                
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

private void searchProducts() {
    String keyword = tfSearch.getText().trim().toLowerCase();
    boolean found = false;

    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindatabse", "root", "");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE LOWER(name) LIKE ? OR LOWER(category) LIKE ?");
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();

        // Create a temporary model to hold results
        DefaultTableModel tempModel = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Price", "Qty", "Delete", "Edit"}, 0);

        while (rs.next()) {
            found = true;
            tempModel.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("category"),
                new java.text.DecimalFormat("₹#,##0.00").format(rs.getDouble("price")),
                rs.getInt("quantity"),
                "Delete",
                "Edit"
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
                    tempModel.getValueAt(i, 5),
                    tempModel.getValueAt(i, 6)
                });
            }
        } else {
            JOptionPane.showMessageDialog(null, "Item not available :(");
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Search error: " + ex.getMessage());
    }
}

private void populateFieldsForEdit(int id) {
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindatabse", "root", "");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            tfName.setText(rs.getString("name"));
            tfCategory.setText(rs.getString("category"));
            tfPrice.setText(String.valueOf(rs.getDouble("price")));
            tfQuantity.setText(String.valueOf(rs.getInt("quantity")));
            taDescription.setText(rs.getString("description"));
            
            lblEditing.setText("Currently Editing: ID " + id);

            btnAdd.setText("UPDATE PRODUCT");

            for (ActionListener al : btnAdd.getActionListeners()) {
                btnAdd.removeActionListener(al);
            }

            btnAdd.addActionListener(e -> updateProduct(id));
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Edit error: " + ex.getMessage());
    }
}

private void updateProduct(int id) {
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindatabse", "root", "");
        String sql = "UPDATE products SET name=?, category=?, price=?, quantity=?, description=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, tfName.getText());
        ps.setString(2, tfCategory.getText());
        String rawPrice = tfPrice.getText().replace(",", "").trim();
        double price = Double.parseDouble(rawPrice);
        ps.setDouble(3, price);
        ps.setInt(4, Integer.parseInt(tfQuantity.getText()));
        ps.setString(5, taDescription.getText());
        ps.setInt(6, id);
        ps.executeUpdate();

        JOptionPane.showMessageDialog(null, "Product updated!");

       resetForm();
        btnAdd.setText("ADD PRODUCT");
        
        lblEditing.setText(" ");  // clears the edit status

        for (ActionListener al : btnAdd.getActionListeners()) {
            btnAdd.removeActionListener(al);
        }
        btnAdd.addActionListener(e -> addProduct());

        loadProducts();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Update error: " + ex.getMessage());
    }
}


private void resetForm() {
    tfName.setText("");
    tfCategory.setText("");
    tfPrice.setText("");
    tfQuantity.setText("");
    taDescription.setText("");
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
