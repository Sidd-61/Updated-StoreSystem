/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

public class HomePage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HomePage.class.getName());

    public HomePage() {
        initComponents();
         setSize(500,400);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new java.awt.Color(51,51,51)); 
        welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        
        
        btnManageProducts.setBackground(new java.awt.Color(153, 0, 51));
        btnManageProducts.setForeground(new java.awt.Color(255, 255, 255)); 
        
        btnManageBuyers.setBackground(new java.awt.Color(153, 0, 51));
        btnManageBuyers.setForeground(new java.awt.Color(255, 255, 255));
        
        logoutButton.setBackground(new java.awt.Color(153, 0, 51));
        logoutButton.setForeground(new java.awt.Color(255, 255, 255)); 
         }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomeLabel = new javax.swing.JLabel();
        btnManageProducts = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        btnManageBuyers = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));
        setMinimumSize(new java.awt.Dimension(400, 300));
        setPreferredSize(new java.awt.Dimension(400, 300));

        welcomeLabel.setFont(new java.awt.Font("Gabriola", 1, 36)); // NOI18N
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setText("Welcome, User!");

        btnManageProducts.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        btnManageProducts.setText("Products");
        btnManageProducts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnManageProducts.setPreferredSize(new java.awt.Dimension(150, 23));
        btnManageProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageProductsActionPerformed(evt);
            }
        });

        logoutButton.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        logoutButton.setPreferredSize(new java.awt.Dimension(150, 40));
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        btnManageBuyers.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        btnManageBuyers.setText("Buyers");
        btnManageBuyers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageBuyersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(welcomeLabel))
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(btnManageProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(btnManageBuyers, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(welcomeLabel)
                .addGap(18, 18, 18)
                .addComponent(btnManageProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnManageBuyers, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
this.dispose(); // closes the homepage
new LoginForm().setVisible(true); // opens login form        // TODO add your handling code here:
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void btnManageProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageProductsActionPerformed
      new ProductPage().setVisible(true);  // TODO add your handling code here:
    }//GEN-LAST:event_btnManageProductsActionPerformed

    private void btnManageBuyersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageBuyersActionPerformed
      new BuyerPage().setVisible(true);  // TODO add your handling code here:
    }//GEN-LAST:event_btnManageBuyersActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new HomePage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnManageBuyers;
    private javax.swing.JButton btnManageProducts;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
