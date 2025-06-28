/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

public class HomePage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HomePage.class.getName());

    public HomePage() {
        initComponents();
        setLocationRelativeTo(null);
    setResizable(false);
        getContentPane().setBackground(new java.awt.Color(230,230,250)); // soft lavender
        welcomeLabel.setForeground(new java.awt.Color(102, 51, 153));
        
        viewProfileButton.setBackground(new java.awt.Color(153, 102, 204));
        viewProfileButton.setForeground(new java.awt.Color(255, 255, 255)); // white text
        settingsButton.setBackground(new java.awt.Color(153, 102, 204));
        settingsButton.setForeground(new java.awt.Color(255, 255, 255)); // white text
        logoutButton.setBackground(new java.awt.Color(153, 102, 204));
        logoutButton.setForeground(new java.awt.Color(255, 255, 255)); // white text
         }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomeLabel = new javax.swing.JLabel();
        viewProfileButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(400, 300));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        welcomeLabel.setFont(new java.awt.Font("Gabriola", 1, 36)); // NOI18N
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setText("Welcome, User!");
        getContentPane().add(welcomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        viewProfileButton.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        viewProfileButton.setText("View Profile");
        viewProfileButton.setFocusPainted(false);
        viewProfileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        viewProfileButton.setMaximumSize(new java.awt.Dimension(93, 40));
        viewProfileButton.setMinimumSize(new java.awt.Dimension(93, 40));
        viewProfileButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewProfileButtonActionPerformed(evt);
            }
        });
        getContentPane().add(viewProfileButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 180, 50));

        settingsButton.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        settingsButton.setText("Settings");
        settingsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        settingsButton.setPreferredSize(new java.awt.Dimension(150, 23));
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });
        getContentPane().add(settingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 170, 50));

        logoutButton.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        logoutButton.setPreferredSize(new java.awt.Dimension(150, 40));
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        getContentPane().add(logoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
this.dispose(); // closes the homepage
new LoginForm().setVisible(true); // opens login form        // TODO add your handling code here:
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void viewProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewProfileButtonActionPerformed
        new ViewProfile().setVisible(true);
    }//GEN-LAST:event_viewProfileButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
      new Settings().setVisible(true);  // TODO add your handling code here:
    }//GEN-LAST:event_settingsButtonActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new HomePage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton settingsButton;
    private javax.swing.JButton viewProfileButton;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
