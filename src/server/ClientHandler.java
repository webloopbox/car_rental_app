/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {

    private Socket s;
    private Connection con;

    /**
     * Constructs a ClientHandler object.
     *
     * @param s the Socket object representing the client connection
     * @param con the database Connection object
     */
    public ClientHandler(Socket s, Connection con) {
        this.s = s;
        this.con = con;
    }

    /**
     * Executes the client request handling logic.
     */
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

                    int resStatus = Server.addUser(email, password, username, firstname, lastname, address, phone, "user");
                    out.println(resStatus);

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
                        if (!fieldType.isEmpty()) {
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
                } else if (line.equals("GET_ALL_USERS")) {
                    List<Map<String, Object>> data = Server.getAllUsers();
                    out.println(data.size());
                    for (Map<String, Object> map : data) {
                        out.println(map.get("id"));
                        out.println(map.get("username"));
                        out.println(map.get("email"));
                        out.println(map.get("phone"));
                        out.println(map.get("address"));
                        out.println(map.get("firstname"));
                        out.println(map.get("lastname"));
                        out.println(map.get("role"));
                    }
                } else if (line.startsWith("UPDATE_USER")) {

                    String[] parts = line.split("\\s+");

                    int userId = Integer.parseInt(parts[1]);
                    String firstname = parts[2];
                    String lastname = parts[3];
                    String username = parts[4];
                    String email = parts[5];
                    String address = parts[6];
                    String phone = parts[7];

                    Server.updateUser(userId, firstname, lastname, username, email, address, phone);
                    out.println("Car updated successfully");
                } else if (line.startsWith("DELETE_USER")) {
                    int id = Integer.parseInt(line.split("\\s+")[1]);
                    Server.deleteUser(id);
                    out.println("User deleted successfully");
                } else if (line.equals("GET_ALL_CARS")) {
                    List<Map<String, Object>> data = Server.getAllCars();
                    out.println(data.size());
                    for (Map<String, Object> map : data) {
                        out.println(map.get("id"));
                        out.println(map.get("reg_number"));
                        out.println(map.get("brand"));
                        out.println(map.get("model"));
                        out.println(map.get("engine_capacity"));
                        out.println(map.get("year"));
                        out.println(map.get("price"));
                        out.println(map.get("availability"));
                    }
                } else if (line.startsWith("INSERT_CAR")) {
                    String[] parts = line.split("\\s+");
                    String regNumber = parts[1];
                    String brand = parts[2];
                    String model = parts[3];
                    double engineCapacity = Double.parseDouble(parts[4].replace(",", "."));
                    int year = Integer.parseInt(parts[5]);
                    double price = Double.parseDouble(parts[6].replace(",", "."));
                    boolean availability = Boolean.parseBoolean(parts[7]);
                    try {
                        Server.insertCar(regNumber, brand, model, engineCapacity, year, price, availability);
                        out.println("Car inserted successfully");
                    } catch (SQLException ex) {
                        out.println("Error: " + ex.getMessage());
                    }
                } else if (line.startsWith("DELETE_CAR")) {
                    int id = Integer.parseInt(line.split("\\s+")[1]);
                    Server.deleteCar(id);
                    out.println("Car deleted successfully");
                } else if (line.startsWith("UPDATE_CAR")) {
                    String[] parts = line.split("\\s+");
                    int carId = Integer.parseInt(parts[1]);
                    String regNumber = parts[2];
                    String brand = parts[3];
                    String model = parts[4];
                    double engineCapacity = Double.parseDouble(parts[5].replace(",", "."));
                    int year = Integer.parseInt(parts[6]);
                    double price = Double.parseDouble(parts[7].replace(",", "."));
                    boolean availability = Boolean.parseBoolean(parts[8]);
                    Server.updateCar(carId, regNumber, brand, model, engineCapacity, year, price, availability);
                    out.println("Car updated successfully");
                } else if (line.equals("ADD_RESERVATION")) {

                    String firstname = in.readLine();
                    String lastname = in.readLine();
                    String registration = in.readLine();
                    String rentFrom = in.readLine();
                    String rentTo = in.readLine();

                    System.out.println("firstname: " + firstname);
                    System.out.println("lastname: " + lastname);
                    System.out.println("registration: " + registration);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date rentFromDate = dateFormat.parse(rentFrom);
                    java.util.Date rentToDate = dateFormat.parse(rentTo);

                    java.sql.Date sqlRentFromDate = new java.sql.Date(rentFromDate.getYear(), rentFromDate.getMonth(), rentFromDate.getDate());
                    java.sql.Date sqlRentToDate = new java.sql.Date(rentToDate.getYear(), rentToDate.getMonth(), rentToDate.getDate());

                    System.out.println("sqlRentFromDate: " + sqlRentFromDate);
                    System.out.println("sqlRentFromDate: " + sqlRentFromDate);

                    try {
                        Server.addReservation(firstname, lastname, registration, sqlRentFromDate, sqlRentToDate);
                        out.println("Reservation added successfully");
                    } catch (SQLException e) {
                        System.out.println("blad z sqla: "+ e);
                        out.println(e);
                    }

                } else if (line.equals("GET_ALL_RESERVATIONS")) {
                    List<Map<String, Object>> data = Server.getReservations();

                    out.println(data.size());
                    for (Map<String, Object> map : data) {
                        out.println(map.get("id"));
                        out.println(map.get("firstname"));
                        out.println(map.get("lastname"));
                        out.println(map.get("registration"));
                        out.println(map.get("rentFrom"));
                        out.println(map.get("rentTo"));
                        out.println(map.get("price"));
                    }
                } else if (line.startsWith("DELETE_RESERVATION")) {
                    Scanner scanner = new Scanner(line);
                    String command = scanner.next();
                    int reservationId = scanner.nextInt();
                    try {
                        Server.deleteReservation(reservationId);
                        out.println("Reservation deleted successfully");
                    } catch (SQLException e) {
                        out.println("Error");
                    }
                } else if (line.equals("GET_USER_RESERVATIONS")) {
                    int userId = Integer.parseInt(in.readLine());
                    List<Map<String, Object>> data = Server.getReservationsForUser(userId);
                    out.println(data.size());
                    for (Map<String, Object> map : data) {
                        out.println(map.get("id"));
                        out.println(map.get("reg_number"));
                        out.println(map.get("brand"));
                        out.println(map.get("model"));
                        out.println(map.get("year"));
                        out.println(map.get("capacity"));
                        out.println(map.get("rent_from"));
                        out.println(map.get("rent_to"));
                        out.println(map.get("price"));
                    }
                } else if (line.equals("GET_USER_SUMMARY")) {
                    int userId = Integer.parseInt(in.readLine());
                    Map<String, Object> summary = Server.getReservationsSummaryForUser(userId);
                    if (summary == null) {
                        // neccessary to handle inside Controller method as unsuccessfull response (no data found)
                        out.println(0);
                    } else {
                        out.println(summary.size());
                        int numReservations = (int) summary.get("numReservations");
                        double totalCost = (double) summary.get("totalCost");
                        java.sql.Date nearestReturnDate = (java.sql.Date) summary.get("nearestReturnDate");

                        out.println(numReservations);
                        out.println(totalCost);
                        out.println(nearestReturnDate);
                    }

                } else if (line.equals("GET_TOTAL_ORDERS_PRICE")) {
                    try {
                        double totalReservationsPrice = Server.getTotalReservationsPrice();
                        out.println(totalReservationsPrice);
                    } catch (SQLException e) {
                        out.println("Error");
                    }
                } else {
                    out.println("Invalid command");
                }
            }
            s.close();
        } catch (SocketException e) {
            // Connection reset by client
            System.out.println("Client disconnected: " + s);
        } catch (IOException | SQLException | NullPointerException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
