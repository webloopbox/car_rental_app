package server;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class Server {
    private static Connection con;
    public static int portNumber = 8000;

    public static void main(String[] args) throws Exception {
        boolean foundPort = false;
        
        while (!foundPort) {
            try {
                ServerSocket ss = new ServerSocket(portNumber);
                foundPort = true;
                
                System.out.println("Server is running...");
                con = DriverManager.getConnection("jdbc:mysql://localhost/car_rental", "root", "");
                
                while (true) {
                    Socket s = ss.accept();
                    System.out.println("New client connected: " + s);
                    ClientHandler handler = new ClientHandler(s, con);
                    new Thread(handler).start();
                }
                
            } catch(Exception e) {
                portNumber++;
            }
        }    
    }

    public static void addUser(String email, String password, String username, String firstname, String lastname, String address, String phone, String role) throws SQLException {
        String sql = "INSERT INTO users (username, password, email, phone, address, firstname, lastname, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepareStatement = con.prepareStatement(sql);
        
        prepareStatement.setString(1, username);
        prepareStatement.setString(2, password);
        prepareStatement.setString(3, email);
        prepareStatement.setString(4, phone);
        prepareStatement.setString(5, address);
        prepareStatement.setString(6, firstname);
        prepareStatement.setString(7, lastname);
        prepareStatement.setString(8, role);

        prepareStatement.executeUpdate();
    }

    public static List<Map<String, Object>> getLoginData(String username, String password) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE username='"+username+"' AND password='"+password+"'";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", rs.getInt("id"));
            map.put("username", rs.getString("username"));
            map.put("password", rs.getString("password"));
            map.put("email", rs.getString("email"));
            map.put("phone", rs.getString("phone"));
            map.put("firstname", rs.getString("firstname"));
            map.put("lastname", rs.getString("lastname"));
            map.put("role", rs.getString("role"));
            list.add(map);
        }
        return list;
    }
    
        public static List<Map<String, Object>> getUserData(int userId, String fieldType) throws SQLException {
            List<Map<String, Object>> list = new ArrayList<>();
            String sql;
            if(!fieldType.isEmpty() ) {
                sql = "SELECT " + fieldType + " FROM users WHERE id=?";
            } else {
                sql = "SELECT * FROM users WHERE id=?";
            }
            
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                if(!fieldType.isEmpty() ) {
                    map.put(fieldType, rs.getString(fieldType));
                } else {
                    map.put("id", rs.getInt("id"));
                    map.put("username", rs.getString("username"));
                    map.put("password", rs.getString("password"));
                    map.put("email", rs.getString("email"));
                    map.put("phone", rs.getString("phone"));
                    map.put("firstname", rs.getString("firstname"));
                    map.put("lastname", rs.getString("lastname"));
                    map.put("role", rs.getString("role"));
                }
                
                list.add(map);
            }
            return list;
    }
}

class ClientHandler implements Runnable {
    private Socket s;
    private Connection con;

    public ClientHandler(Socket s, Connection con) {
        this.s = s;
        this.con = con;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            String line;
            
            while ((line = in.readLine()) != null) {
                if (line.equals("REGISTER_USER")) {
                    String email = in.readLine();
                    String password = in.readLine();
                    String username = in.readLine();
                    String firstname = in.readLine();
                    String lastname = in.readLine();
                    String address = in.readLine();
                    String phone = in.readLine();
                    
                    Server.addUser(email, password, username, firstname, lastname, address, phone, "user");
                    out.println("Data added successfully");
                    
                } else if (line.equals("LOGIN_USER")) {
                    
                    String username = in.readLine();
                    String password = in.readLine();
                    List<Map<String, Object>> data = Server.getLoginData(username, password);
                    out.println(data.size());
                    
                    for (Map<String, Object> map : data) {
                        out.println(map.get("id"));
                        out.println(map.get("username"));
                        out.println(map.get("password"));
                        out.println(map.get("email"));
                        out.println(map.get("phone"));
                        out.println(map.get("firstname"));
                        out.println(map.get("lastname"));
                        out.println(map.get("role"));
                    }
                } else if (line.equals("GET_USER_DATA")) {
                    
                    int userId = Integer.parseInt(in.readLine());
                    String fieldType = in.readLine();
                    List<Map<String, Object>> data = Server.getUserData(userId, fieldType);
                    out.println(data.size());
                    
                    for (Map<String, Object> map : data) {
                        if(!fieldType.isEmpty() ) {
                            out.println(map.get(fieldType));
                        } else {
                            out.println(map.get("id"));
                            out.println(map.get("username"));
                            out.println(map.get("password"));
                            out.println(map.get("email"));
                            out.println(map.get("phone"));
                            out.println(map.get("firstname"));
                            out.println(map.get("lastname"));
                            out.println(map.get("role"));
                        }
                    }
                } else {
                    out.println("Invalid command");
                }
            }
            s.close();
        } catch (SocketException e) {
        // Connection reset by client
        System.out.println("Client disconnected: " + s);
    } catch (IOException | SQLException e) {
        e.printStackTrace();
    }
}
}
