package wypozyczalnia_aut;

import org.apache.log4j.Logger;

import java.awt.Color;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.Border;

public class LoginForm extends javax.swing.JFrame {
    private static Logger logger = Logger.getLogger(LoginForm.class);
    private Controller controller;

    public LoginForm() {
        initComponents();

        controller = Main.getController();

        //Centrowanie Okienka
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //Ładowanie zdjęć Bannera i ikon koło pól logowania
        URL bannerURL = getClass().getResource("images/bannerLogin.png");
        URL userURL = getClass().getResource("images/user.png");
        URL passwordURL = getClass().getResource("images/password.png");

        if (bannerURL != null) {
            jLabel_Banner.setIcon(new javax.swing.ImageIcon(bannerURL));
        }
        if (userURL != null) {
            jLabel_user.setIcon(new javax.swing.ImageIcon(userURL));
        }
        if (passwordURL != null) {
            jLabel_password.setIcon(new javax.swing.ImageIcon(passwordURL));
        }

        //Border
        System.setProperty("BorderLoginColor", "0xFFDF29");
        Border title_border = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.getColor("BorderLoginColor"));
        titleBorder.setBorder(title_border);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_Banner = new javax.swing.JLabel();
        titleBorder = new javax.swing.JLabel();
        jLabel_user = new javax.swing.JLabel();
        LoginInput = new javax.swing.JTextField();
        jLabel_password = new javax.swing.JLabel();
        ShowPassLogin = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel_RegisterUser = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        PassInput = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Wypożyczalnia Aut");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));

        jLabel_Banner.setBackground(new java.awt.Color(51, 153, 255));
        jLabel_Banner.setOpaque(true);

        titleBorder.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        titleBorder.setForeground(new java.awt.Color(0, 0, 0));
        titleBorder.setText("Zaloguj się");

        jLabel_user.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_user.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_user.setText("X");

        LoginInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginInputActionPerformed(evt);
            }
        });

        jLabel_password.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_password.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_password.setText("X");

        ShowPassLogin.setForeground(new java.awt.Color(0, 0, 0));
        ShowPassLogin.setText("Pokaż hasło");
        ShowPassLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowPassLoginActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 223, 41));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Zaloguj się");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel_RegisterUser.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_RegisterUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_RegisterUser.setText("Nie masz jeszcze konta? Kliknij, aby je założyć.");
        jLabel_RegisterUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_RegisterUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_RegisterUserMouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 224, 41));
        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("<<<");

        PassInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel_Banner, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(titleBorder))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel_user, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LoginInput, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel_password, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(ShowPassLogin)
                                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel_RegisterUser)
                                                .addGap(50, 50, 50))
                                            .addComponent(PassInput))))
                                .addGap(12, 12, 12)))
                        .addGap(0, 45, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(16, 16, 16))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Banner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addComponent(titleBorder)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginInput, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_user, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PassInput, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_password, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ShowPassLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_RegisterUser)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles the action performed when the "Register User" label is clicked.
     * Opens the RegisterForm window.
     *
     * @param evt the MouseEvent object representing the event
     */
    private void jLabel_RegisterUserMouseClicked(java.awt.event.MouseEvent evt) {
        logger.info("entered register form");
        RegisterForm formReg = new RegisterForm();
        formReg.setVisible(true);
        formReg.pack();
        formReg.setLocationRelativeTo(null);
    }

    /**
     * Handles the action performed when the "Login" button is clicked. Calls
     * the controller's loginUser method to validate the login credentials.
     *
     * @param evt the ActionEvent object representing the event
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        logger.info("login attempt");
        String res = controller.loginUser( LoginInput, PassInput);
        if(res.equals("user")) {
            this.dispose();
            DashboardUser dashboardUser = new DashboardUser();
            dashboardUser.show();
        } else if(res.equals("admin")) {
            this.dispose();
            Dashboard dashboard = new Dashboard();
            dashboard.show();
        } else if(res.equals("bad_credentials")) {
            JOptionPane.showMessageDialog(this, "Podano zły login lub haslo");
            LoginInput.setText("");
            PassInput.setText("");
        }
    }

    private void LoginInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginInputActionPerformed
        // TODO add your handling code here:
    }

    private void PassInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassInputActionPerformed
        // TODO add your handling code here:
    }

    /**
     * Handles the action performed when the "Show Password" checkbox
     * (right-side eye) is clicked. Changes the echo character of the password
     * field based on the checkbox state. If the checkbox is selected, the
     * password is shown as plain text. If the checkbox is unselected, the
     * password is masked with asterisks.
     *
     * @param evt the ActionEvent object representing the event
     */
    private void ShowPassLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowPassLoginActionPerformed

        if (ShowPassLogin.isSelected()) {
            PassInput.setEchoChar((char) 0);
        } else {
            PassInput.setEchoChar('*');
        }

    }//GEN-LAST:event_ShowPassLoginActionPerformed
//=============================================================================================================================================================================

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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField LoginInput;
    private javax.swing.JPasswordField PassInput;
    private javax.swing.JCheckBox ShowPassLogin;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Banner;
    private javax.swing.JLabel jLabel_RegisterUser;
    private javax.swing.JLabel jLabel_password;
    private javax.swing.JLabel jLabel_user;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel titleBorder;
    // End of variables declaration//GEN-END:variables
}
