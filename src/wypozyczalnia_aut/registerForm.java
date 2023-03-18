package wypozyczalnia_aut;

import helpers.Helpers;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class registerForm extends javax.swing.JFrame {
    private Controller controller;
    
    public registerForm() {
        initComponents();

        controller = Main.getController();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon(getClass().getResource("images/eye.png"));

        jLabel_BannerRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/RegisterBanner.jpg")));

        Color backgroundColor = new Color(0, 0, 0, 0);

        TogglePassRegister.setBackground(backgroundColor);
        TogglePassRegister.setBorderPainted(false);
        TogglePassRegister.setContentAreaFilled(false);
        TogglePassRegister.setFocusPainted(false);
        TogglePassRegister.setIcon(icon);
        TogglePassRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ToggleRePassRegister.setBackground(backgroundColor);
        ToggleRePassRegister.setBorderPainted(false);
        ToggleRePassRegister.setContentAreaFilled(false);
        ToggleRePassRegister.setFocusPainted(false);
        ToggleRePassRegister.setIcon(icon);
        ToggleRePassRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));

        System.setProperty("BorderColor", "0xFFDF29");
        Border titleRegister2 = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.getColor("BorderLoginColor"));
        titleRegister.setBorder(titleRegister2);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void registerUser() {
        Helpers helpers = new Helpers();  
        String email = EmailInput.getText();
        String pass = String.valueOf(PasswordInput.getPassword());
        String rePass = String.valueOf(RePasswordInput.getPassword());
        String username = UsernameInput.getText();
        String firstname = FirstnameInput.getText();
        String lastname = LastnameInput.getText();
        String address = AddressInput.getText();
        String phone = PhoneInput.getText();

        if (email.isEmpty() || pass.isEmpty() || rePass.isEmpty() || username.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Uzupełnij wszystkie pola", "Spróbuj ponownie", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!pass.equals(rePass)) {
            JOptionPane.showMessageDialog(this, "Hasła się nie zgadzają", "Spróbuj ponownie", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!helpers.isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email jest niepoprawny", "Spróbuj ponownie", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!helpers.isValidPolishPhoneNumber(phone)) {
            JOptionPane.showMessageDialog(this, "Numer telefonu jest niepoprawny", "Spróbuj ponownie", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int status = controller.registerUser(this, email, pass, username, firstname, lastname, address, phone);
        if(status == 1) this.dispose();
    }

//==================================================================================================
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titleRegister = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        FirstnameInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        LastnameInput = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        UsernameInput = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        EmailInput = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        PhoneInput = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        register_btn = new javax.swing.JButton();
        jLabel_BannerRegister = new javax.swing.JLabel();
        PasswordInput = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        AddressInput = new javax.swing.JTextField();
        RePasswordInput = new javax.swing.JPasswordField();
        TogglePassRegister = new javax.swing.JToggleButton();
        ToggleRePassRegister = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rejestracja nowego użytkownika");

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));

        titleRegister.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        titleRegister.setForeground(new java.awt.Color(0, 0, 0));
        titleRegister.setText("Zarejestruj się");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Imię:");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nazwisko:");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nazwa użytkownika:");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Adres e-mail:");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Hasło:");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Potwierdź hasło:");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Numer telefonu:");

        register_btn.setBackground(new java.awt.Color(255, 223, 41));
        register_btn.setForeground(new java.awt.Color(0, 0, 0));
        register_btn.setText("Załóż konto");
        register_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                register_btnActionPerformed(evt);
            }
        });

        jLabel_BannerRegister.setBackground(new java.awt.Color(255, 223, 41));
        jLabel_BannerRegister.setOpaque(true);

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Adres zamieszkania:");

        RePasswordInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RePasswordInputActionPerformed(evt);
            }
        });

        TogglePassRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TogglePassRegisterActionPerformed(evt);
            }
        });

        ToggleRePassRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToggleRePassRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel_BannerRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(TogglePassRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(FirstnameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(LastnameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(register_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(AddressInput, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(PhoneInput, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(EmailInput, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                    .addComponent(UsernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                                .addComponent(PasswordInput, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(RePasswordInput, javax.swing.GroupLayout.Alignment.LEADING)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ToggleRePassRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(titleRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LastnameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FirstnameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UsernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EmailInput, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(TogglePassRegister))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RePasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(ToggleRePassRegister))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PhoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddressInput, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(register_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addComponent(jLabel_BannerRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel3.getAccessibleContext().setAccessibleName("Hasło:");

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

    private void register_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_register_btnActionPerformed
        this.registerUser();
    }//GEN-LAST:event_register_btnActionPerformed

    private void TogglePassRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TogglePassRegisterActionPerformed

        if (TogglePassRegister.isSelected()) {
            PasswordInput.setEchoChar((char) 0);
        } else {
            PasswordInput.setEchoChar('*');
        }
    }//GEN-LAST:event_TogglePassRegisterActionPerformed

    private void ToggleRePassRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToggleRePassRegisterActionPerformed
        if (ToggleRePassRegister.isSelected()) {
            RePasswordInput.setEchoChar((char) 0);
        } else {
            RePasswordInput.setEchoChar('*');
        }
    }//GEN-LAST:event_ToggleRePassRegisterActionPerformed

    private void RePasswordInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RePasswordInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RePasswordInputActionPerformed

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
            java.util.logging.Logger.getLogger(registerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new registerForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddressInput;
    private javax.swing.JTextField EmailInput;
    private javax.swing.JTextField FirstnameInput;
    private javax.swing.JTextField LastnameInput;
    private javax.swing.JPasswordField PasswordInput;
    private javax.swing.JTextField PhoneInput;
    private javax.swing.JPasswordField RePasswordInput;
    private javax.swing.JToggleButton TogglePassRegister;
    private javax.swing.JToggleButton ToggleRePassRegister;
    private javax.swing.JTextField UsernameInput;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_BannerRegister;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton register_btn;
    private javax.swing.JLabel titleRegister;
    // End of variables declaration//GEN-END:variables

}
