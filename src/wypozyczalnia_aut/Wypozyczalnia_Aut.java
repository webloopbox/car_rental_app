package wypozyczalnia_aut;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import server.Server;

public class Wypozyczalnia_Aut {
    public static Controller controller;
    private static Server server;
    
    public static void main(String[] args) {
        
        server = new Server(); 
        
        // start a new thread that runs the controller's methods
        // the Server class creates a new thread for each client connection in the main method, which is handled by the ClientHandler class. 
        // similarly, in the Wypozyczalnia_Aut class, a new thread is created to run the Server instance's main method.
        new Thread(() -> {
            String[] args_ = {}; // arguments to pass to the main method
        try {
            server.main(args_); // call the main method of the Server instance
        } catch (Exception e) {
            e.printStackTrace();
        }
        }).start();
        
        controller = new Controller();

        // Start Swing application
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new loginForm();
            frame.setVisible(true);
        });
        
    }
    
    // Method to get the controller instance
    public static Controller getController() {
        return controller;
    }
    
}
