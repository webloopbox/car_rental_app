package wypozyczalnia_aut;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import server.Server;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);
    public static Controller controller;
    private static Server server;
    
    public static void main(String[] args) {
        logger.info("Application started");
        
        server = new Server(); 
        
        /**
        * Start nowego wątku, który uruchamia metodę main instancji Server
         */
        new Thread(() -> {
            String[] args_ = {}; // arguments to pass to the main method
        try {
            server.main(args_); // call the main method of the Server instance
        } catch (Exception e) {
            e.printStackTrace();
        }
        }).start();
        
        controller = new Controller();

        /**
         * Uruchomienie aplikacji klienckiej Swing za pomocą metody invokeLater(planowanie zadania do wykonania w wątku wysyłania zdarzeń)
         */
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new LoginForm();
            frame.setVisible(true);
        });
        
    }
    
    /**
     * @return instancja Klasy Controller.
    */
    public static Controller getController() {
        return controller;
    }
}
