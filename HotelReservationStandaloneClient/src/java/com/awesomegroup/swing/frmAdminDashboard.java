/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.swing;

/**
 *
 * @author lujamanandhar
 */
public class frmAdminDashboard extends javax.swing.JFrame {

    /**
     * Creates new form frmAdminDashboard
     */
    public frmAdminDashboard() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        mnuRoomType = new javax.swing.JMenuItem();
        mnuRoomAmenitiesSetup = new javax.swing.JMenuItem();
        frmRoomSetup = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard");
        setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/awesomegroup/swing/logo copy.png"))); // NOI18N

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 255));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Setups");

        mnuRoomType.setText("Room Type Setup");
        mnuRoomType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRoomTypeActionPerformed(evt);
            }
        });
        jMenu2.add(mnuRoomType);

        mnuRoomAmenitiesSetup.setText("Room Amenities Setup");
        mnuRoomAmenitiesSetup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRoomAmenitiesSetupActionPerformed(evt);
            }
        });
        jMenu2.add(mnuRoomAmenitiesSetup);

        frmRoomSetup.setText("Room Setup");
        frmRoomSetup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frmRoomSetupActionPerformed(evt);
            }
        });
        jMenu2.add(frmRoomSetup);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 948, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(549, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuRoomTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRoomTypeActionPerformed
        // TODO add your handling code here:
        frmRoomType rt = new frmRoomType();
        rt.setLocationRelativeTo(this);
        rt.setVisible(true);
        
    }//GEN-LAST:event_mnuRoomTypeActionPerformed

    private void mnuRoomAmenitiesSetupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRoomAmenitiesSetupActionPerformed
        // TODO add your handling code here:
        frmRoomAmenities rt = new frmRoomAmenities();
        rt.setLocationRelativeTo(this);
        rt.setVisible(true);
        
    }//GEN-LAST:event_mnuRoomAmenitiesSetupActionPerformed

    private void frmRoomSetupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frmRoomSetupActionPerformed
        // TODO add your handling code here:
//        frmRoom rt = new frmRoom();
//        rt.setLocationRelativeTo(this);
//        rt.setVisible(true);
    }//GEN-LAST:event_frmRoomSetupActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmAdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmAdminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem frmRoomSetup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mnuRoomAmenitiesSetup;
    private javax.swing.JMenuItem mnuRoomType;
    // End of variables declaration//GEN-END:variables
}
