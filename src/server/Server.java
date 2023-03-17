package server;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

            } catch (Exception e) {
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
        String sql = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
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

    public static void insertCar(String regNumber, String brand, String model, double engineCapacity, int year, double price, boolean availability) throws SQLException {
        String sql = "INSERT INTO cars (reg_number, brand, model, engine_capacity, year, price, availability) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, regNumber);
        stmt.setString(2, brand);
        stmt.setString(3, model);
        stmt.setDouble(4, engineCapacity);
        stmt.setInt(5, year);
        stmt.setDouble(6, price);
        stmt.setBoolean(7, availability);
        stmt.executeUpdate();
    }

    public static List<Map<String, Object>> getUserData(int userId, String fieldType) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql;
        if (!fieldType.isEmpty()) {
            sql = "SELECT " + fieldType + " FROM users WHERE id=?";
        } else {
            sql = "SELECT * FROM users WHERE id=?";
        }

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            if (!fieldType.isEmpty()) {
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

    public static List<Map<String, Object>> getAllUsers() throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT * FROM users";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", rs.getInt("id"));
            map.put("username", rs.getString("username"));
            map.put("email", rs.getString("email"));
            map.put("phone", rs.getString("phone"));
            map.put("address", rs.getString("address"));
            map.put("firstname", rs.getString("firstname"));
            map.put("lastname", rs.getString("lastname"));
            map.put("role", rs.getString("role"));
            list.add(map);
        }
        return list;
    }

    public static List<Map<String, Object>> getAllCars() throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", rs.getInt("id"));
            map.put("reg_number", rs.getString("reg_number"));
            map.put("brand", rs.getString("brand"));
            map.put("model", rs.getString("model"));
            map.put("engine_capacity", rs.getDouble("engine_capacity"));
            map.put("year", rs.getInt("year"));
            map.put("price", rs.getDouble("price"));
            map.put("availability", rs.getBoolean("availability"));
            list.add(map);
        }
        return list;
    }

    public static void updateCar(int carId, String regNumber, String brand, String model, double engineCapacity, int year, double price, boolean availability) throws SQLException {
        String sql = "UPDATE cars SET reg_number=?, brand=?, model=?, engine_capacity=?, year=?, price=?, availability=? WHERE id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, regNumber);
        stmt.setString(2, brand);
        stmt.setString(3, model);
        stmt.setDouble(4, engineCapacity);
        stmt.setInt(5, year);
        stmt.setDouble(6, price);
        stmt.setBoolean(7, availability);
        stmt.setInt(8, carId);
        stmt.executeUpdate();
    }

    public static void updateUser(int userId, String firstname, String lastname, String username, String email, String address, String phone) throws SQLException {
        String sql = "UPDATE users SET firstname=?, lastname=?, username=?, email=?, address=?, phone=? WHERE id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, firstname);
        stmt.setString(2, lastname);
        stmt.setString(3, username);
        stmt.setString(4, email);
        stmt.setString(5, address);
        stmt.setString(6, phone);
        stmt.setInt(7, userId);
        stmt.executeUpdate();
    }

    public static void deleteCar(int id) throws SQLException {
        String sql = "DELETE FROM cars WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public static void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public static void addReservation(String firstname, String lastname, String registration, java.sql.Date rentFrom, java.sql.Date rentTo) throws SQLException {
        // Get user ID from username
        String getUserSql = "SELECT id FROM users WHERE firstname = ? AND lastname = ?";
        PreparedStatement getUserStatement = con.prepareStatement(getUserSql);
        getUserStatement.setString(1, firstname);
        getUserStatement.setString(2, lastname);
        ResultSet userResult = getUserStatement.executeQuery();
        if (!userResult.next()) {
            throw new SQLException("User not found");
        }
        int userId = userResult.getInt("id");

        // Get car ID from registration number
        String getCarSql = "SELECT id FROM cars WHERE reg_number = ?";
        PreparedStatement getCarStatement = con.prepareStatement(getCarSql);
        getCarStatement.setString(1, registration);
        ResultSet carResult = getCarStatement.executeQuery();
        if (!carResult.next()) {
            throw new SQLException("Car not found");
        }
        int carId = carResult.getInt("id");

        // Check car availability
        String checkAvailabilitySql = "SELECT availability FROM cars WHERE id = ?";
        PreparedStatement checkAvailabilityStatement = con.prepareStatement(checkAvailabilitySql);
        checkAvailabilityStatement.setInt(1, carId);
        ResultSet availabilityResult = checkAvailabilityStatement.executeQuery();
        if (!availabilityResult.next()) {
            throw new SQLException("Car not found");
        }
        boolean availability = availabilityResult.getBoolean("availability");
        if (!availability) {
            throw new SQLException("Car not available");
        }

        // Insert reservation into users_cars table
        String addReservationSql = "INSERT INTO users_cars (user_id, car_id, rent_from, rent_to) VALUES (?, ?, ?, ?)";
        PreparedStatement addReservationStatement = con.prepareStatement(addReservationSql);
        addReservationStatement.setInt(1, userId);
        addReservationStatement.setInt(2, carId);
        addReservationStatement.setDate(3, rentFrom);
        addReservationStatement.setDate(4, rentTo);
        addReservationStatement.executeUpdate();

        // Update car availability
        String updateAvailabilitySql = "UPDATE cars SET availability = ? WHERE id = ?";
        PreparedStatement updateAvailabilityStatement = con.prepareStatement(updateAvailabilitySql);
        updateAvailabilityStatement.setBoolean(1, false);
        updateAvailabilityStatement.setInt(2, carId);
        updateAvailabilityStatement.executeUpdate();
    }

    public static List<Map<String, Object>> getReservations() throws SQLException {
        List<Map<String, Object>> data = new ArrayList<>();
        String sql = "SELECT uc.id, u.firstname, u.lastname, c.reg_number, uc.rent_from, uc.rent_to, c.price "
                + "FROM users_cars uc "
                + "JOIN users u ON uc.user_id = u.id "
                + "JOIN cars c ON uc.car_id = c.id";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getInt("id"));
                row.put("firstname", rs.getString("firstname"));
                row.put("lastname", rs.getString("lastname"));
                row.put("registration", rs.getString("reg_number"));
                row.put("rentFrom", rs.getDate("rent_from"));
                row.put("rentTo", rs.getDate("rent_to"));

                // calculate price based on rental dates and car price
                int carPrice = rs.getInt("price");
                java.sql.Date rentFrom = rs.getDate("rent_from");
                java.sql.Date rentTo = rs.getDate("rent_to");
                long rentalDays = ChronoUnit.DAYS.between(rentFrom.toLocalDate(), rentTo.toLocalDate());
                int totalPrice = carPrice * (int) rentalDays;
                row.put("price", totalPrice);
                data.add(row);
            }
        }
        return data;
    }

    public static void deleteReservation(int reservationId) throws SQLException {
        // Get car ID and rent dates from reservation ID
        String getReservationSql = "SELECT car_id, rent_from, rent_to FROM users_cars WHERE id = ?";
        PreparedStatement getReservationStatement = con.prepareStatement(getReservationSql);
        getReservationStatement.setInt(1, reservationId);
        ResultSet reservationResult = getReservationStatement.executeQuery();
        if (!reservationResult.next()) {
            throw new SQLException("Reservation not found");
        }
        int carId = reservationResult.getInt("car_id");
        java.sql.Date rentFrom = reservationResult.getDate("rent_from");
        java.sql.Date rentTo = reservationResult.getDate("rent_to");

        // Delete reservation from users_cars table
        String deleteReservationSql = "DELETE FROM users_cars WHERE id = ?";
        PreparedStatement deleteReservationStatement = con.prepareStatement(deleteReservationSql);
        deleteReservationStatement.setInt(1, reservationId);
        deleteReservationStatement.executeUpdate();

        // Update car availability
        String updateAvailabilitySql = "UPDATE cars SET availability = ? WHERE id = ?";
        PreparedStatement updateAvailabilityStatement = con.prepareStatement(updateAvailabilitySql);
        updateAvailabilityStatement.setBoolean(1, true);
        updateAvailabilityStatement.setInt(2, carId);
        updateAvailabilityStatement.executeUpdate();

        // Check if there are any reservations for the same car and date range
        String checkReservationsSql = "SELECT COUNT(*) AS count FROM users_cars WHERE car_id = ? AND ((rent_from <= ? AND rent_to >= ?) OR (rent_from >= ? AND rent_from <= ?))";
        PreparedStatement checkReservationsStatement = con.prepareStatement(checkReservationsSql);
        checkReservationsStatement.setInt(1, carId);
        checkReservationsStatement.setDate(2, rentFrom);
        checkReservationsStatement.setDate(3, rentTo);
        checkReservationsStatement.setDate(4, rentFrom);
        checkReservationsStatement.setDate(5, rentTo);
        ResultSet countResult = checkReservationsStatement.executeQuery();
        if (countResult.next()) {
            int count = countResult.getInt("count");
            if (count == 0) {
                // No reservations for the same car and date range, update car availability to true
                updateAvailabilityStatement.setBoolean(1, true);
                updateAvailabilityStatement.setInt(2, carId);
                updateAvailabilityStatement.executeUpdate();
            }
        }
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
                    Server.insertCar(regNumber, brand, model, engineCapacity, year, price, availability);
                    out.println("Car inserted successfully");
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
                } else if (line.startsWith("ADD_RESERVATION")) {
                    Scanner scanner = new Scanner(line);

                    String command = scanner.next();
                    String firstname = scanner.next();
                    String lastname = scanner.next();
                    String registration = scanner.next();
                    String rentFrom = scanner.next();
                    String rentTo = scanner.next();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date rentFromDate = dateFormat.parse(rentFrom);
                    java.util.Date rentToDate = dateFormat.parse(rentTo);

                    System.out.println("rentFromDate.getYear" + rentFromDate);

                    java.sql.Date sqlRentFromDate = new java.sql.Date(rentFromDate.getYear(), rentFromDate.getMonth(), rentFromDate.getDate());
                    java.sql.Date sqlRentToDate = new java.sql.Date(rentToDate.getYear(), rentToDate.getMonth(), rentToDate.getDate());

                    try {
                        Server.addReservation(firstname, lastname, registration, sqlRentFromDate, sqlRentToDate);
                        out.println("Reservation added successfully");
                    } catch (SQLException e) {
                        out.println("Error");
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
                } else {
                    out.println("Invalid command");
                }
            }
            s.close();
        } catch (SocketException e) {
            // Connection reset by client
            System.out.println("Client disconnected: " + s);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
