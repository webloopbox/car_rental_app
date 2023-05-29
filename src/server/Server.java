package server;

import java.io.IOException;
import java.net.*;

public class Server extends DatabaseHandler {
    /**
     * The port number for the server.
     */
    public static int portNumber = 8000;
    private static final int INITIAL_PORT = 8000;
    private static final int MAX_PORT = 9000;

    /**
     * The main method that starts the server.
     *
     * @param args command line arguments
     * @throws Exception if an error occurs
     */
    public static void main(String[] args) throws Exception {
        int portNumber = findAvailablePort();

        if (portNumber != -1) {
            System.out.println("Server is running on port: " + portNumber);

            // Creates a ServerSocket object to listen for incoming connections on the server
            ServerSocket ss = new ServerSocket(portNumber);

            // start an infinite loop that will listen for client connections all the time the server is running
            while (true) {
                /* wait for the client to establish a connection. When the client makes a connection,
                   accept() method returns a new Socket object that represents that connection */
                Socket s = ss.accept();
                System.out.println("New client connected: " + s);
                ClientHandler handler = new ClientHandler(s, con);
                /* Each client connection is handled in a separate thread so that the server can handle multiple clients simultaneously */
                new Thread(handler).start();
            }
        } else {
            System.out.println("No available port found.");
        }
    }

    private static int findAvailablePort() {
        for (int port = INITIAL_PORT; port <= MAX_PORT; port++) {
            try {
                ServerSocket ss = new ServerSocket(port);
                ss.close();
                return port;
            } catch (IOException e) {
                // Port is already in use, try the next one
            }
        }
        return -1; // No available port found
    }
}
