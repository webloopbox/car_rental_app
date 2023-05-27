package server;

import java.net.*;
import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Server {

    /**
     * The connection object for the database.
     */
    private static Connection con;

    /**
     * The port number for the server.
     */
    public static int portNumber = 8000;

    /**
     * The main method that starts the server.
     *
     * @param args command line arguments
     * @throws Exception if an error occurs
     */
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

    /**
     * Adds a new user to the database.
     *
     * @param email the email of the user
     * @param password the password of the user
     * @param username the username of the user
     * @param firstname the first name of the user
     * @param lastname the last name of the user
     * @param address the address of the user
     * @param phone the phone number of the user
     * @param role the role of the user
     * @return 0 if the user already exists, 1 if the user is added successfully
     * @throws SQLException if a database error occurs
     */
    public static int addUser(String email, String password, String username, String firstname, String lastname, String address, String phone, String role) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? OR email = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return 0;
        }

        sql = "INSERT INTO users (username, password, email, phone, address, firstname, lastname, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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

        return 1;
    }

    /**
     * Retrieves user data from the database for the given username and
     * password.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return a list of login data for the matching username and password
     * @throws SQLException if a database error occurs
     */
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

    /**
     * Inserts a new car into the database.
     *
     * @param regNumber the registration number of the car
     * @param brand the brand of the car
     * @param model the model of the car
     * @param engineCapacity the engine capacity of the car
     * @param year the year of the car
     * @param price the price of the car
     * @param availability the availability status of the car
     * @throws SQLException if a database error occurs
     */
    public static void insertCar(String regNumber, String brand, String model, double engineCapacity, int year, double price, boolean availability) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM cars WHERE reg_number = ?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);
        checkStmt.setString(1, regNumber);
        ResultSet checkResult = checkStmt.executeQuery();
        checkResult.next();
        int count = checkResult.getInt(1);
        if (count > 0) {
            throw new SQLException("Car with registration number already exists");
        }

        String insertSql = "INSERT INTO cars (reg_number, brand, model, engine_capacity, year, price, availability) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStmt = con.prepareStatement(insertSql);
        insertStmt.setString(1, regNumber);
        insertStmt.setString(2, brand);
        insertStmt.setString(3, model);
        insertStmt.setDouble(4, engineCapacity);
        insertStmt.setInt(5, year);
        insertStmt.setDouble(6, price);
        insertStmt.setBoolean(7, availability);
        insertStmt.executeUpdate();
    }


    /**
     * Retrieves user data from the database for the given user ID and field
     * type.
     *
     * @param userId the ID of the user
     * @param fieldType the field type to retrieve (empty for all fields)
     * @return a list of user data for the matching user ID and field type
     * @throws SQLException if a database error occurs
     */
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

    /**
     * Retrieves a list of all users from the database.
     *
     * @return A list of maps representing user data, where each map contains
     * user information.
     * @throws SQLException if there is an error executing the SQL query.
     */
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

    /**
     * Retrieves a list of all cars from the database.
     *
     * @return A list of maps representing car data, where each map contains car
     * information.
     * @throws SQLException if there is an error executing the SQL query.
     */
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

    /**
     * Updates a car in the database with the provided information.
     *
     * @param carId The ID of the car to update.
     * @param regNumber The registration number of the car.
     * @param brand The brand of the car.
     * @param model The model of the car.
     * @param engineCapacity The engine capacity of the car.
     * @param year The year of the car.
     * @param price The price of the car.
     * @param availability The availability status of the car.
     * @throws SQLException if there is an error executing the SQL update.
     */
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

    /**
     * Updates a user in the database with the provided information.
     *
     * @param userId The ID of the user to update.
     * @param firstname The first name of the user.
     * @param lastname The last name of the user.
     * @param username The username of the user.
     * @param email The email address of the user.
     * @param address The address of the user.
     * @param phone The phone number of the user.
     * @throws SQLException if there is an error executing the SQL update.
     */
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

    /**
     * Deletes a car from the database.
     *
     * @param id The ID of the car to delete.
     * @throws SQLException if there is an error executing the SQL delete.
     */
    public static void deleteCar(int id) throws SQLException {
        String sql = "DELETE FROM cars WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    /**
     * Deletes a user from the database.
     *
     * @param id The ID of the user to delete.
     * @throws SQLException if there is an error executing the SQL delete.
     */
    public static void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    /**
     * Adds a reservation for a user and a car to the database.
     *
     * @param firstname The first name of the user.
     * @param lastname The last name of the user.
     * @param registration The registration number of the car.
     * @param rentFrom The start date of the rental.
     * @param rentTo The end date of the rental.
     * @throws SQLException if there is an error executing the SQL queries or if
     * the user or car is not found.
     */
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

    /**
     * Retrieves a list of all reservations from the database.
     *
     * @return A list of maps representing reservation data, where each map
     * contains reservation information.
     * @throws SQLException if there is an error executing the SQL query.
     */
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

    /**
     * Deletes a reservation from the database.
     *
     * @param reservationId The ID of the reservation to delete.
     * @throws SQLException if there is an error executing the SQL delete or if
     * the reservation is not found.
     */
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

    /**
     * Retrieves a list of reservations for a specific user from the database.
     *
     * @param userId The ID of the user.
     * @return A list of maps representing reservation data, where each map
     * contains reservation information.
     * @throws SQLException if there is an error executing the SQL query.
     */
    public static List<Map<String, Object>> getReservationsForUser(int userId) throws SQLException {
        List<Map<String, Object>> data = new ArrayList<>();
        String sql = "SELECT uc.id, c.reg_number, c.brand, c.model, c.year, c.engine_capacity, uc.rent_from, uc.rent_to, c.price "
                + "FROM users_cars uc "
                + "JOIN users u ON uc.user_id = u.id "
                + "JOIN cars c ON uc.car_id = c.id "
                + "WHERE uc.user_id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getString("id"));
                row.put("reg_number", rs.getString("reg_number"));
                row.put("brand", rs.getString("brand"));
                row.put("model", rs.getString("model"));
                row.put("year", rs.getInt("year"));
                row.put("capacity", rs.getDouble("engine_capacity"));
                row.put("rent_from", rs.getDate("rent_from"));
                row.put("rent_to", rs.getDate("rent_to"));
                row.put("price", rs.getDouble("price"));
                data.add(row);
            }
        }
        return data;
    }

    /**
     * Retrieves a summary of reservations for a specific user from the
     * database.
     *
     * @param userId The ID of the user.
     * @return A map containing the reservations summary, including the total
     * number of reservations, total cost, and nearest return date.
     * @throws SQLException if there is an error executing the SQL query.
     */
    public static Map<String, Object> getReservationsSummaryForUser(int userId) throws SQLException {
        Map<String, Object> summary = new HashMap<>();

        // Get reservations for user
        List<Map<String, Object>> reservations = getReservationsForUser(userId);

        if (reservations.isEmpty()) {
            System.out.println("Uzytkownik nie posiada zadnych rezerwacji.");
            return null;
        } else {

            // Calculate total number of reservations
            int numReservations = reservations.size();
            summary.put("numReservations", numReservations);

            // Calculate total cost of reservations
            double totalCost = 0;
            java.sql.Date nearestReturnDate = null;
            for (Map<String, Object> reservation : reservations) {
                double price = (double) reservation.get("price");
                java.sql.Date rentFrom = (java.sql.Date) reservation.get("rent_from");
                java.sql.Date rentTo = (java.sql.Date) reservation.get("rent_to");
                long diffInMillies = Math.abs(rentTo.getTime() - rentFrom.getTime());
                long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                double reservationCost = diffInDays * price;
                totalCost += reservationCost;

                // Find nearest return date
                if (nearestReturnDate == null || rentTo.before(nearestReturnDate)) {
                    nearestReturnDate = rentTo;
                }
            }

            summary.put("totalCost", totalCost);
            summary.put("nearestReturnDate", nearestReturnDate);
        }
        return summary;
    }

    /**
     * Calculates the total price of all reservations in the database.
     *
     * @return The total price of all reservations.
     * @throws SQLException if there is an error executing the SQL query.
     */
    public static double getTotalReservationsPrice() throws SQLException {
        String sql = "SELECT SUM(DATEDIFF(rent_to, rent_from) * price) as total_price FROM users_cars INNER JOIN cars ON users_cars.car_id = cars.id";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_price");
            }
        }
        return 0;
    }
}
