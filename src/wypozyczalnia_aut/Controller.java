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
    public static Integer id;

    public Controller() {
        try {
            socket = new Socket("localhost", Server.portNumber);
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
            char[] password = PassInput.getPassword();

            out.println("LOGIN_USER");
            out.println(username);
            out.println(password);

            int size = Integer.parseInt(in.readLine());

            if (size > 0) {

                for (int i = 0; i < size; i++) {
                    int id = Integer.parseInt(in.readLine());
                    this.id = id;
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

                    if (role.equals("user")) {
                        loginForm.dispose();
                        System.out.println("123123");
                        DashboardUser dashboardUser = new DashboardUser();
                        dashboardUser.show();
                    } else {
                        loginForm.dispose();
                        Dashboard dashboard = new Dashboard();
                        dashboard.show();
                    }

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

    public void registerUser(JFrame form, String email, String password, String username, String firstname, String lastname, String address, String phone) {

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
            if (size != null) {
                System.out.printf(size);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(form,
                    "Nie udało się zarejestronwać",
                    "Spróbuj ponownie",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Nie udało się wykonać rejestracji");
            System.out.println(e);
        }
    }

    /*
    @params receive field param, for example username or email
    If fieldType argument passed then return 1 field
    Otherwise return all user fields
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

    public void insertCar(String regNumber, String brand, String model, double engineCapacity, int year, double price, boolean availability) {
        try {
            out.println("INSERT_CAR " + regNumber + " " + brand + " " + model + " " + engineCapacity + " " + year + " " + price + " " + availability);
            String response = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCar(int id) {
        try {
            out.println("DELETE_CAR " + id);
            String response = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCar(int carId, String regNumber, String brand, String model, double engineCapacity, int year, double price, boolean availability) {
        try {
            out.println("UPDATE_CAR " + carId + " " + regNumber + " " + brand + " " + model + " " + engineCapacity + " " + year + " " + price + " " + availability);
            String response = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUser(int userId, String firstname, String lastname, String username, String email, String address, String phone) {
        System.out.println("----------------------- WORKS?" + phone);
        try {
            out.println("UPDATE_USER " + userId + " " + firstname + " " + lastname + " " + username + " " + email + " " + address + " " + phone);
            String response = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    public void deleteUser(int id) {
        try {
            out.println("DELETE_USER " + id);
            String response = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
