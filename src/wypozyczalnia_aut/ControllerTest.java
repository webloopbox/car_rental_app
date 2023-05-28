package wypozyczalnia_aut;

import org.junit.jupiter.api.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerTest {
    static Controller controller;
    static String carId;
    static int reservationId;

    @BeforeAll
    public static void setUpBeforeClass() {
        // Code to be executed before all tests
        controller = new Controller();
    }

    @Test
    @Order(1)
    void registerUser() {
        JFrame form = null;
        String email = "test@gmail.com";
        String password = "test_pass";
        String username = "test_user";
        String firstname = "jan";
        String lastname = "kowalski";
        String address = "testowy adres";
        String phone = "111222333";
        int expResult = 1;
        int result = controller.registerUser(form, email, password, username, firstname, lastname, address, phone);
        assertEquals(expResult, result);
    }

    @Test
    @Order(2)
    void loginUser() {
        JTextField LoginInput = new JTextField("test_user");
        JPasswordField PassInput = new JPasswordField("test_pass");
        String expResult1 = "user";
        String expResult2 = "admin";
        String result = controller.loginUser(LoginInput, PassInput);
        assertTrue(result.equals(expResult1) || result.equals(expResult2));
    }

    @Test
    @Order(3)
    void getAllUsers() {
        List<Map<String, Object>> result = controller.getAllUsers();
        assertFalse(result.isEmpty());
    }

    @Test
    @Order(4)
    void updateUser() {
        int userId = Controller.userId;
        String firstname = "new_name";
        String lastname = "new_lastname";
        String username = "new_username";
        String email = "changed_mail@gmail.com";
        String address = "new_address";
        String phone = "999999999";
        int result = controller.updateUser(userId, firstname, lastname, username, email, address, phone);
        assertEquals(1, result);
    }

    @Test
    @Order(5)
    void getUserData() {
        int userId = Controller.userId;
        String fieldType = "firstname";
        String[] expResult = {"new_name"};
        String[] result = controller.getUserData(userId, fieldType);
        assertArrayEquals(expResult, result);
    }

    @Test
    @Order(6)
    void insertCar() {
        String regNumber = "DE34FS34";
        String brand = "Opel";
        String model = "Astra";
        double engineCapacity = 1.5;
        int year = 2014;
        double price = 140.0;
        boolean availability = false;

        String result = controller.insertCar(regNumber, brand, model, engineCapacity, year, price, availability);

        List<Map<String, Object>> allCars = controller.getAllCars();

        boolean containsRegNumber = allCars.stream()
                .anyMatch(car -> car.get("reg_number").equals(regNumber));

        // Get the carId of the car with the matched reg_number
        carId = allCars.stream()
                .filter(car -> car.get("reg_number").equals(regNumber))
                .map(car -> String.valueOf(car.get("id")))
                .findFirst()
                .orElse(""); // find carId by proper reg number

        assertTrue(containsRegNumber);

        if(!result.equals("Car inserted successfully")) {
            fail("error while inserting a car");
        }
    }

    @Test
    @Order(7)
    void updateCar() {
        int carId = Integer.parseInt(ControllerTest.carId);
        String regNumber = "DE34FS34";
        String brand = "Opel";
        String model = "Astra";
        double engineCapacity = 1.5;
        int year = 2022; // changed
        double price = 140.0;
        boolean availability = true; // changed
        int result = controller.updateCar(carId, regNumber, brand, model, engineCapacity, year, price, availability);
        assertEquals(1, result);
    }

    @Test
    @Order(8)
    void getAllCars() {
        List<Map<String, Object>> result = controller.getAllCars();
        assertFalse(result.isEmpty());
    }

    @Test
    @Order(9)
    void addReservation() {
        String firstname = "new_name";
        String lastname = "new_lastname";
        String registration = "DE34FS34";

        int result = controller.addReservation(firstname, lastname, registration, "10/06/2023", "16/06/2023");
        assertEquals(1, result);
    }

    @Test
    @Order(10)
    void getAllReservations() {
        List<Map<String, Object>> result = controller.getAllReservations();
        assertFalse(result.isEmpty());
    }

    @Test
    @Order(11)
    void getUserReservations() {
        int userId = Controller.userId;
        List<Map<String, Object>> result = controller.getUserReservations(userId);
        if (!result.isEmpty()) {
            Map<String, Object> firstReservation = result.get(0);
            String reservationIdStr = (String) firstReservation.get("id");
            reservationId = Integer.parseInt(reservationIdStr);
        }
        assertFalse(result.isEmpty());
    }

    @Test
    @Order(12)
    void getUserSummary() {
        int userId = Controller.userId;
        Map<String, Object> result = controller.getUserSummary(userId);

        Integer numReservations = (Integer) result.get("numReservations");
        Double totalCost = (Double) result.get("totalCost");
        java.sql.Date nearestReturnDate = (java.sql.Date) result.get("nearestReturnDate");

        // Perform assertions
        assertEquals(1, numReservations);
        assertEquals(840.0, totalCost);
        assertEquals("2023-06-16", nearestReturnDate.toString());
    }

    @Test
    @Order(13)
    void getTotalOrdersPrice() {
        double expResult = 840.0;
        double result = controller.getTotalOrdersPrice();
        assertEquals(expResult, result);
    }

    @Test
    @Order(14)
    void deleteReservation() {
        int reservationId = ControllerTest.reservationId;
        int result = controller.deleteReservation(reservationId);
        assertEquals(1, result);
    }

    @Test
    @Order(15)
    void deleteCar() {
        int id = Integer.parseInt(carId);
        int result = controller.deleteCar(id);
        assertEquals(1, result);
    }
    @Test
    @Order(16)
    void deleteUser() {
        int id = Controller.userId;
        int result = controller.deleteUser(id);
        assertEquals(1, result);
    }
}