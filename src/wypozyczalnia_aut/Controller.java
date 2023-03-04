
package wypozyczalnia_aut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Controller {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    public Controller() {
        try {
            socket = new Socket("localhost", 9999);
            System.out.println("Connected to server: " + socket);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(loginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loginUser(loginForm loginForm, JTextField LoginInput, JPasswordField PassInput) {
        try {
            
            String username = LoginInput.getText();
            String password = PassInput.getText();
            
            out.println("LOGIN_USER");
            out.println(username);
            out.println(password);
            
            int size = Integer.parseInt(in.readLine());
            
            if(size > 0) {
                loginForm.dispose();
                DashboardUser dashboardUser = new DashboardUser();
                dashboardUser.show();
                for (int i = 0; i < size; i++) {
                    int id = Integer.parseInt(in.readLine());
                    
                    String name = in.readLine();
                    String email = in.readLine();
                    String phone = in.readLine();
                    String firstname = in.readLine();
                    String lastname = in.readLine();
                    
                    System.out.printf(name);
                    System.out.printf(" ------- ");
                    System.out.printf(email);
                    System.out.printf(" ------- ");
                    System.out.printf(phone);
                    System.out.printf(" ------- ");
                    System.out.printf(firstname);
                    System.out.printf(" ------- ");
                    System.out.printf(lastname);
                }
            } else {
                JOptionPane.showMessageDialog(loginForm, "Podano zły login lub haslo");
                LoginInput.setText("");
                PassInput.setText("");
            }
        } catch (IOException ex) {
            Logger.getLogger(loginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registerUser(registerForm registerForm, String email, String password, String username, String firstname, String lastname, String address, String phone) {
        
        try {
            out.println("REGISTER_USER");
            
            out.println(email);
            out.println(password);
            out.println(username);
            
            out.println(firstname);
            out.println(lastname);
            out.println(address);
            out.println(phone);
            
            String size = in.readLine();
            if(size!=null) {
                System.out.printf(size);
                registerForm.dispose();
                Dashboard dashboard = new Dashboard();
                dashboard.show();
                }
           
        } catch(Exception e) {
            JOptionPane.showMessageDialog(registerForm,
                    "Nie udało się zarejestronwać",
                    "Spróbuj ponownie",
                    JOptionPane.ERROR_MESSAGE);
             System.out.println("Nie udało się wykonać rejestracji");
             System.out.println(e);
        }
    }
}
