package wypozyczalnia_aut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import server.Server;

public class Controller {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public static Integer userId;

    public Controller() {
        try {
            socket = new Socket("localhost", Server.portNumber);
            System.out.println("Connected to server: " + socket);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Logs in the user with the provided username and password.
     *
     * @param loginForm  The login form object
     * @param LoginInput The JTextField containing the username input
     * @param PassInput  The JPasswordField containing the password input
     * @return None
     */
    public String loginUser(JTextField LoginInput, JPasswordField PassInput) {
        try {

            String username = LoginInput.getText();
            char[] password = PassInput.getPassword();

            out.println("LOGIN_USER");
            out.println(username);
            out.println(password);

            int size = Integer.parseInt(in.readLine());
            System.out.println("Size: " + size);
            if (size > 0) {

                int id = Integer.parseInt(in.readLine());
                this.userId = id;
                String name = in.readLine();
                String pass = in.readLine();
                String email = in.readLine();
                String phone = in.readLine();
                String firstname = in.readLine();
                String lastname = in.readLine();
                String role = in.readLine();

                System.out.printf(name);
                System.out.printf(" ------- ");
                System.out.printf(pass);
                System.out.printf(" ------- ");
                System.out.printf(email);
                System.out.printf(" ------- ");
                System.out.printf(phone);
                System.out.printf(" ------- ");
                System.out.printf(firstname);
                System.out.printf(" ------- ");
                System.out.printf(lastname);
                System.out.printf(" ------- ");
                System.out.println(role);

                return role.equals("user") ? "user" : "admin";
            } else {
                return "bad_credentials";
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            return "server_error";
        }
    }

    /**
     * Registers a new user with the provided information.
     *
     * @param form      The JFrame form object
     * @param email     The email address of the user
     * @param password  The password of the user
     * @param username  The username of the user
     * @param firstname The first name of the user
     * @param lastname  The last name of the user
     * @param address   The address of the user
     * @param phone     The phone number of the user
     * @return 1 if the registration is successful, 0 otherwise
     */
    public int registerUser(JFrame form, String email, String password, String username, String firstname, String lastname, String address, String phone) {

        try {
            out.println("REGISTER_USER");

            out.println(email);
            out.println(password);
            out.println(username);

            out.println(firstname);
            out.println(lastname);
            out.println(address);
            out.println(phone);

            String res = in.readLine();

            if (Integer.parseInt(res) == 0) {
                JOptionPane.showMessageDialog(form,
                        "Użytkownik o tej samej nazwie użytkownika lub adresie e-mail już istnieje",
                        "Niepowodzenie",
                        JOptionPane.ERROR_MESSAGE);
                return 0;
            }

            return 1;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(form,
                    "Nie udało się zarejestronwać",
                    "Spróbuj ponownie",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Nie udało się wykonać rejestracji");
            System.out.println(e);
        }

        return 0;

    }

    /**
     * Retrieves user data of the specified field type for a given user ID.
     *
     * @param userId    The ID of the user
     * @param fieldType The type of field to retrieve (e.g., "email",
     *                  "username", etc.)
     * @return An array of strings containing the user data, or an empty array
     * if no data is found
     */
    public String[] getUserData(int userId, String fieldType) {
        List<String> dataList = new ArrayList<>();

        try {
            out.println("GET_USER_DATA");

            out.println(userId);
            out.println(fieldType);

            int size = Integer.parseInt(in.readLine());
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    dataList.add(in.readLine());
                }
            }
        } catch (Exception e) {
        }

        String[] dataArray = dataList.toArray(new String[0]);
        return dataArray;
    }

    /**
     * Retrieves information for all cars from the database.
     *
     * @return A list of maps, where each map represents the data of a car The
     * map keys correspond to the field names (e.g., "id", "reg_number", etc.)
     * The map values represent the values of the corresponding fields
     */
    public List<Map<String, Object>> getAllCars() {
        List<Map<String, Object>> data = new ArrayList<>();
        try {
            out.println("GET_ALL_CARS");
            int size = Integer.parseInt(in.readLine());
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("id", in.readLine());
                    row.put("reg_number", in.readLine());
                    row.put("brand", in.readLine());
                    row.put("model", in.readLine());
                    row.put("engine_capacity", in.readLine());
                    row.put("year", in.readLine());
                    row.put("price", in.readLine());
                    row.put("availability", in.readLine());
                    data.add(row);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    /**
     * Inserts a new car into the database.
     *
     * @param regNumber      The registration number of the car.
     * @param brand          The brand of the car.
     * @param model          The model of the car.
     * @param engineCapacity The engine capacity of the car.
     * @param year           The manufacturing year of the car.
     * @param price          The price of the car.
     * @param availability   The availability status of the car.
     */
    public String insertCar(String regNumber, String brand, String model, double engineCapacity, int year, double price, boolean availability) {
        try {
            out.println("INSERT_CAR " + regNumber + " " + brand + " " + model + " " + engineCapacity + " " + year + " " + price + " " + availability);
            String response = in.readLine();
            return response;
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    /**
     * Deletes a car from the database based on the provided car ID.
     *
     * @param id The ID of the car to be deleted.
     */
    public int deleteCar(int id) {
        try {
            out.println("DELETE_CAR " + id);
            String response = in.readLine();
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    /**
     * Updates the information of a car in the database based on the provided
     * car ID.
     *
     * @param carId          The ID of the car to be updated.
     * @param regNumber      The new registration number of the car.
     * @param brand          The new brand of the car.
     * @param model          The new model of the car.
     * @param engineCapacity The new engine capacity of the car.
     * @param year           The new manufacturing year of the car.
     * @param price          The new price of the car.
     * @param availability   The new availability status of the car.
     */
    public int updateCar(int carId, String regNumber, String brand, String model, double engineCapacity, int year, double price, boolean availability) {
        try {
            out.println("UPDATE_CAR " + carId + " " + regNumber + " " + brand + " " + model + " " + engineCapacity + " " + year + " " + price + " " + availability);
            String response = in.readLine();
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    /**
     * Updates the information of a user in the database based on the provided
     * user ID.
     *
     * @param userId    The ID of the user to be updated.
     * @param firstname The new firstname of the user.
     * @param lastname  The new lastname of the user.
     * @param username  The new username of the user.
     * @param email     The new email of the user.
     * @param address   The new address of the user.
     * @param phone     The new phone number of the user.
     */
    public int updateUser(int userId, String firstname, String lastname, String username, String email, String address, String phone) {
        try {
            out.println("UPDATE_USER " + userId + " " + firstname + " " + lastname + " " + username + " " + email + " " + address + " " + phone);
            String response = in.readLine();
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    /**
     * Retrieves information for all users from the database.
     *
     * @return A list of maps, where each map represents the data of a user. The
     * map keys correspond to the field names (e.g., "id", "username", etc.).
     * The map values represent the values of the corresponding fields.
     */
    public List<Map<String, Object>> getAllUsers() {
        List<Map<String, Object>> data = new ArrayList<>();
        try {
            out.println("GET_ALL_USERS");
            int size = Integer.parseInt(in.readLine());
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("id", in.readLine());
                    row.put("username", in.readLine());
                    row.put("email", in.readLine());
                    row.put("phone", in.readLine());
                    row.put("address", in.readLine());
                    row.put("firstname", in.readLine());
                    row.put("lastname", in.readLine());
                    row.put("role", in.readLine());
                    data.add(row);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    /**
     * Deletes a user from the database based on the provided user ID.
     *
     * @param id The ID of the user to be deleted.
     */
    public int deleteUser(int id) {
        try {
            out.println("DELETE_USER " + id);
            String response = in.readLine();
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    /**
     * Adds a new reservation to the database.
     *
     * @param firstname    The firstname of the user making the reservation.
     * @param lastname     The lastname of the user making the reservation.
     * @param registration The registration number of the car being reserved.
     * @param rentFrom     The start date of the reservation.
     * @param rentTo       The end date of the reservation.
     */
    public int addReservation(String firstname, String lastname, String registration, String rentFrom, String rentTo) {

        try {

            out.println("ADD_RESERVATION");
            out.println(firstname);
            out.println(lastname);
            out.println(registration);
            out.println(rentFrom);
            out.println(rentTo);

            String response = in.readLine();

            if (!response.equals("Reservation added successfully")) {
                throw new RuntimeException(response);
            } else {
                return 1;
            }
        } catch (Throwable ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }


    /**
     * Retrieves information for all reservations from the database.
     *
     * @return A list of maps, where each map represents the data of a
     * reservation. The map keys correspond to the field names (e.g., "id",
     * "firstname", etc.). The map values represent the values of the
     * corresponding fields.
     */
    public List<Map<String, Object>> getAllReservations() {
        List<Map<String, Object>> data = new ArrayList<>();

        try {
            out.println("GET_ALL_RESERVATIONS");
            int size = Integer.parseInt(in.readLine());
            if (size > 0) {
                for (int i = 0; i < size; i++) {

                    Map<String, Object> row = new HashMap<>();
                    row.put("id", in.readLine());
                    row.put("firstname", in.readLine());
                    row.put("lastname", in.readLine());
                    row.put("registration", in.readLine());
                    row.put("rentFrom", in.readLine());
                    row.put("rentTo", in.readLine());
                    row.put("price", in.readLine());
                    data.add(row);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    /**
     * Deletes a reservation from the database based on the provided reservation
     * ID.
     *
     * @param reservationId The ID of the reservation to be deleted.
     */
    public int deleteReservation(int reservationId) {
        try {
            out.printf("DELETE_RESERVATION %d\n", reservationId);
            String response = in.readLine();
            if (response.equals("Reservation deleted successfully")) {
                return 1;
            }
            return 0;
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    /**
     * Retrieves reservations for a specific user from the database.
     *
     * @param userId The ID of the user for whom reservations are retrieved.
     * @return A list of maps, where each map represents the data of a
     * reservation. The map keys correspond to the field names (e.g.,
     * "reg_number", "brand", etc.). The map values represent the values of the
     * corresponding fields.
     */
    public List<Map<String, Object>> getUserReservations(int userId) {
        List<Map<String, Object>> data = new ArrayList<>();

        try {
            out.println("GET_USER_RESERVATIONS");
            out.println(userId);
            int size = Integer.parseInt(in.readLine());
            System.out.println("Wynik: "+size);
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("id", in.readLine());
                    row.put("reg_number", in.readLine());
                    row.put("brand", in.readLine());
                    row.put("model", in.readLine());
                    row.put("year", in.readLine());
                    row.put("capacity", in.readLine());
                    row.put("rent_from", in.readLine());
                    row.put("rent_to", in.readLine());
                    row.put("price", in.readLine());
                    data.add(row);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    /**
     * Retrieves a summary of user information from the database based on the
     * provided user ID.
     *
     * @param userId The ID of the user for whom the summary is retrieved.
     * @return A list of maps, where each map represents the user summary data.
     * The map keys correspond to the summary fields (e.g., "numReservations",
     * "totalCost", etc.). The map values represent the values of the
     * corresponding summary fields.
     */
    public Map<String, Object> getUserSummary(int userId) {
        Map<String, Object> summary = new HashMap<>();
        try {
            out.println("GET_USER_SUMMARY");
            out.println(userId);
            int size = Integer.parseInt(in.readLine());
            if (size > 0) {
                int numReservations = Integer.parseInt(in.readLine());
                double totalCost = Double.parseDouble(in.readLine());
                String nearestReturnDateStr = in.readLine();
                java.sql.Date nearestReturnDate = null;

                if (nearestReturnDateStr != null && !nearestReturnDateStr.isEmpty()) {
                    nearestReturnDate = java.sql.Date.valueOf(nearestReturnDateStr);
                }

                summary.put("numReservations", numReservations);
                summary.put("totalCost", totalCost);
                summary.put("nearestReturnDate", nearestReturnDate);
            }

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return summary;
    }

    /**
     * Retrieves the total price of all orders from the database.
     *
     * @return The total price of all car orders.
     */
    public double getTotalOrdersPrice() {
        try {
            out.println("GET_TOTAL_ORDERS_PRICE");
            String price = in.readLine();
            return Double.parseDouble(price);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }
}
