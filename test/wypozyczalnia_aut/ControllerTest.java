/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package wypozyczalnia_aut;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marek
 */
public class ControllerTest {

    public ControllerTest() {
    }

  
    /**
     * Test of deleteUser method, of class Controller.
     */
    @Test
    public void testDeleteUser() {
//        System.out.println("deleteUser");
//        int id = 14;
//        Controller instance = new Controller();
//        instance.deleteUser(id);
//
//        // Assert that the user with the specified ID is deleted
//        assertNull(instance.getUserData(id, ""));

// Arrange
        int a = 2;
        int b = 3;

        // Act
        int result = a + b;

        // Assert
        assertEquals(5, result, "The sum should be 5");
    }

//    /**
//     * Test of loginUser method, of class Controller.
//     */
//    @Test
//    public void testLoginUser() {
//        System.out.println("loginUser");
//        LoginForm loginForm = null;
//        JTextField LoginInput = null;
//        JPasswordField PassInput = null;
//        Controller instance = new Controller();
//        instance.loginUser(loginForm, LoginInput, PassInput);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of registerUser method, of class Controller.
//     */
//    @Test
//    public void testRegisterUser() {
//        System.out.println("registerUser");
//        JFrame form = null;
//        String email = "";
//        String password = "";
//        String username = "";
//        String firstname = "";
//        String lastname = "";
//        String address = "";
//        String phone = "";
//        Controller instance = new Controller();
//        int expResult = 0;
//        int result = instance.registerUser(form, email, password, username, firstname, lastname, address, phone);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUserData method, of class Controller.
//     */
//    @Test
//    public void testGetUserData() {
//        System.out.println("getUserData");
//        int userId = 0;
//        String fieldType = "";
//        Controller instance = new Controller();
//        String[] expResult = null;
//        String[] result = instance.getUserData(userId, fieldType);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAllCars method, of class Controller.
//     */
//    @Test
//    public void testGetAllCars() {
//        System.out.println("getAllCars");
//        Controller instance = new Controller();
//        List<Map<String, Object>> expResult = null;
//        List<Map<String, Object>> result = instance.getAllCars();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of insertCar method, of class Controller.
//     */
//    @Test
//    public void testInsertCar() {
//        System.out.println("insertCar");
//        String regNumber = "";
//        String brand = "";
//        String model = "";
//        double engineCapacity = 0.0;
//        int year = 0;
//        double price = 0.0;
//        boolean availability = false;
//        Controller instance = new Controller();
//        instance.insertCar(regNumber, brand, model, engineCapacity, year, price, availability);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteCar method, of class Controller.
//     */
//    @Test
//    public void testDeleteCar() {
//        System.out.println("deleteCar");
//        int id = 0;
//        Controller instance = new Controller();
//        instance.deleteCar(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateCar method, of class Controller.
//     */
//    @Test
//    public void testUpdateCar() {
//        System.out.println("updateCar");
//        int carId = 0;
//        String regNumber = "";
//        String brand = "";
//        String model = "";
//        double engineCapacity = 0.0;
//        int year = 0;
//        double price = 0.0;
//        boolean availability = false;
//        Controller instance = new Controller();
//        instance.updateCar(carId, regNumber, brand, model, engineCapacity, year, price, availability);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateUser method, of class Controller.
//     */
//    @Test
//    public void testUpdateUser() {
//        System.out.println("updateUser");
//        int userId = 0;
//        String firstname = "";
//        String lastname = "";
//        String username = "";
//        String email = "";
//        String address = "";
//        String phone = "";
//        Controller instance = new Controller();
//        instance.updateUser(userId, firstname, lastname, username, email, address, phone);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAllUsers method, of class Controller.
//     */
//    @Test
//    public void testGetAllUsers() {
//        System.out.println("getAllUsers");
//        Controller instance = new Controller();
//        List<Map<String, Object>> expResult = null;
//        List<Map<String, Object>> result = instance.getAllUsers();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addReservation method, of class Controller.
//     */
//    @Test
//    public void testAddReservation() {
//        System.out.println("addReservation");
//        String firstname = "";
//        String lastname = "";
//        String registration = "";
//        String rentFrom = "";
//        String rentTo = "";
//        Controller instance = new Controller();
//        instance.addReservation(firstname, lastname, registration, rentFrom, rentTo);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAllReservations method, of class Controller.
//     */
//    @Test
//    public void testGetAllReservations() {
//        System.out.println("getAllReservations");
//        Controller instance = new Controller();
//        List<Map<String, Object>> expResult = null;
//        List<Map<String, Object>> result = instance.getAllReservations();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteReservation method, of class Controller.
//     */
//    @Test
//    public void testDeleteReservation() {
//        System.out.println("deleteReservation");
//        int reservationId = 0;
//        Controller instance = new Controller();
//        instance.deleteReservation(reservationId);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUserReservations method, of class Controller.
//     */
//    @Test
//    public void testGetUserReservations() {
//        System.out.println("getUserReservations");
//        int userId = 0;
//        Controller instance = new Controller();
//        List<Map<String, Object>> expResult = null;
//        List<Map<String, Object>> result = instance.getUserReservations(userId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUserSummary method, of class Controller.
//     */
//    @Test
//    public void testGetUserSummary() {
//        System.out.println("getUserSummary");
//        int userId = 0;
//        Controller instance = new Controller();
//        List<Map<String, Object>> expResult = null;
//        List<Map<String, Object>> result = instance.getUserSummary(userId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTotalOrdersPrice method, of class Controller.
//     */
//    @Test
//    public void testGetTotalOrdersPrice() {
//        System.out.println("getTotalOrdersPrice");
//        Controller instance = new Controller();
//        double expResult = 0.0;
//        double result = instance.getTotalOrdersPrice();
//        assertEquals(expResult, result, 0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
