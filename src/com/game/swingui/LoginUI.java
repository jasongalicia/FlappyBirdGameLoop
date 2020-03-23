package com.game.swingui;

import com.game.cryption.Encrypt;
import com.game.display.Display;
import com.game.engine.Engine;
import com.game.globals.Globals;
import com.game.states.GameState;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jason Anthony Galicia
 */
public class LoginUI extends javax.swing.JFrame {
    
    private Connection con;
    private Display display;
    private Engine engine;
    private GameState state;
    
    /**
     * Creates new form LoginUI (Default Constructor).
     */
    public LoginUI() {}
    
    /**
     * Constructor which creates the LoginUI form.
     * @param display The Display object.
     * @param engine The Engine object.
     * @param state The GameState object.
     */
    public LoginUI(Display display, Engine engine, GameState state) {
        this.display = display;
        this.engine = engine;
        this.state = state;
        
        initComponents();
        init();
    }
    
    /**
     * Initializes the frame components.
     */
    private void init() {
        setTitle("Login");
        setSize(200, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        usernameField.setForeground(Color.GRAY);
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
               if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Username");
                    usernameField.setForeground(Color.GRAY);
                } 
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        signupButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        usernameField.setText("Username");
        usernameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameFieldKeyReleased(evt);
            }
        });
        getContentPane().add(usernameField);
        usernameField.setBounds(40, 100, 130, 20);

        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordFieldKeyReleased(evt);
            }
        });
        getContentPane().add(passwordField);
        passwordField.setBounds(40, 160, 130, 20);

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        getContentPane().add(loginButton);
        loginButton.setBounds(50, 210, 100, 23);

        jLabel1.setText("Password");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 134, 110, 10);

        jLabel2.setText("Username");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 70, 110, 14);

        signupButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        signupButton.setText("Sign Up");
        signupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupButtonActionPerformed(evt);
            }
        });
        getContentPane().add(signupButton);
        signupButton.setBounds(10, 10, 70, 21);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Action event when the login button as been pressed.
     * @param evt The action event.
     */
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        checkCredentials();
    }//GEN-LAST:event_loginButtonActionPerformed
    
    /**
     * If a certain key is released while focused on the username field.
     * @param evt The key event.
     */
    private void usernameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameFieldKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) checkCredentials();
    }//GEN-LAST:event_usernameFieldKeyReleased
    
    /**
     * If a certain key is released while focused on the password field.
     * @param evt The key event.
     */
    private void passwordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) checkCredentials();
    }//GEN-LAST:event_passwordFieldKeyReleased
    
    /**
     * Leads the user to a sign up page, to sign up to save their score.
     * @param evt The Action event.
     */
    private void signupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupButtonActionPerformed
        // TODO add your handling code here:
        try {
            // Opens the link in browser and shows a message box afterwards
            Desktop.getDesktop().browse(new URI(Globals.SIGN_UP_LINK));
            JOptionPane.showMessageDialog(null, "Attempting to open browser.");
        }
        catch (HeadlessException | IOException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_signupButtonActionPerformed
    
    /**
     * Checks the credentials inside of the database.
     */
    private void checkCredentials() {
        String username = "";
        String usernameInput = usernameField.getText();
        String passwordInput = String.valueOf(passwordField.getPassword());
        
        if (usernameInput.equals("") || usernameInput.
                equalsIgnoreCase("Username")) {
            JOptionPane.showMessageDialog(null, "Please enter a username.");
        }
        else if (passwordInput.equals("") || passwordInput.
                equalsIgnoreCase("Password")) {
            JOptionPane.showMessageDialog(null, "Please enter a password.");
        }
        else {
            try {
                Class.forName(Globals.DB_DRIVER);
                
                con = DriverManager.getConnection(Globals.DB_URL, 
                        Globals.DB_USERNAME, Globals.DB_PASSWORD);
                System.out.println("Connected!");
                
                Statement stmt = con.createStatement();
                String q = "SELECT * FROM admin_credentials WHERE "
                        + "username='"+usernameInput+"'";
                ResultSet rs = stmt.executeQuery(q);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username or Password "
                        + "entered is incorrect. Please try again.");
                }
                else {
                    String actualPassword = rs.getString("password");
                    String newPassword = "";
                    Encrypt crypt = new Encrypt(passwordInput);
                
                    for (int i = 0; i < passwordInput.length(); i++) {
                        newPassword = crypt.getEncryptionWithSalt(i);
                        if (newPassword.equals(actualPassword)) {
                            username = rs.getString("username");
                            saveScore(username);
                            this.dispose();
                            resetGame();
                            return;
                        }
                        else if (!newPassword.equals(actualPassword) && 
                                i == passwordInput.length()-1) {
                            JOptionPane.showMessageDialog(null, "Password is "
                                    + "incorrect. Please try again.");
                        }
                    }
                }
            }
            catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getLocalizedMessage());
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
    }
    
    /**
     * Saves the score into the database.
     * @param username The username.
     */
    private void saveScore(String username) {
        try {
            // Get today's date
            LocalDate date = LocalDate.now();
          // Insert the name and scores into the database.....................
            String insertStatement = "INSERT INTO flappy_scores (username, "
                    + "score, date_scored)"
                    + "VALUES "
                    + "('"+username+"','"+state.getScore()+"', '"+date+"')";
            PreparedStatement insert = con.prepareStatement(insertStatement);
            insert.executeUpdate();
            System.out.println("Data has been entered into the system.");
            JOptionPane.showMessageDialog(null, "Your score was saved!");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Your score was not saved. "
                    + "Error on server side.");
            System.out.println("Error: "+ e.getMessage());
        }
    }
    
    /**
     * Resets the game.
     */
    private void resetGame() {
        engine.setIsRunning(false);
        Engine newEngine = new Engine(engine.getWindowTitle(), 
                engine.getWindowWidth(), engine.getWindowHeight());
        newEngine.start();
        display.getCanvas().setEnabled(false);
        display.getFrame().dispose();
    }
    
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
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton signupButton;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}