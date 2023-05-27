package wypozyczalnia_aut;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static wypozyczalnia_aut.Main.controller;

public class AddCarForm extends javax.swing.JFrame {
    private static Logger logger = Logger.getLogger(AddCarForm.class);
    public static int edit_car_id;

    public AddCarForm() {
        initComponents();

        fetchCarListIntoTable();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * method that calls the controller.getAllCars() method of the Controller
     * class to get a list of all cars and display it as a table
     */
    private void fetchCarListIntoTable() {
        // Get the car data from the server
        List<Map<String, Object>> carData = null;
        try {
            carData = controller.getAllCars();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        // Convert the list of maps to a two-dimensional array of objects
        Object[][] data = new Object[carData.size()][];
        for (int i = 0; i < carData.size(); i++) {
            Map<String, Object> car = carData.get(i);
            data[i] = new Object[]{
                car.get("id"),
                car.get("reg_number"),
                car.get("brand"),
                car.get("model"),
                car.get("engine_capacity"),
                car.get("year"),
                car.get("price"),
                car.get("availability")
            };
        }

        // Set the data and column names as the model of the JTable
        String[] columnNames = new String[]{"ID", "Number rejestracji", "Marka", "Model", "Pojemność silnika", "Rocznik", "Cena/dzień", "Dostępność"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        CarsTable.setModel(model);
    }

    /**
     * method that takes values from the form and adds a new car using Controller insertCar()
     */
    private void addCar() {
        logger.info("add new car attempt");
        int year;
        double price;
        String brand = (String) SelectBrand.getSelectedItem();
        double capacity = Double.parseDouble(Capacity.getText());
        String model = ModelInput.getText();
        String registration = CarRegInput.getText();
        String availability = (String) AvailabilityInput.getSelectedItem();
        try {
            year = Integer.parseInt(YearInput.getText());
            price = Double.parseDouble(PriceInput.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input for year or price. Please enter numeric values.");
            return;
        }

        boolean availability_bool;
        availability_bool = availability.equals("Tak");

        String res = controller.insertCar(registration, brand, model, capacity, year, price, availability_bool);
        if(!res.equals("Car inserted successfully")) {
            JOptionPane.showMessageDialog(this, res);
        }
        fetchCarListIntoTable();
        Dashboard.fetchCarStats();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CarsTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        SelectBrand = new javax.swing.JComboBox<>();
        YearInput = new javax.swing.JTextField();
        CarRegInput = new javax.swing.JTextField();
        Capacity = new javax.swing.JTextField();
        PriceInput = new javax.swing.JTextField();
        ModelInput = new javax.swing.JTextField();
        AddCarBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        AvailabilityInput = new javax.swing.JComboBox<>();
        EditBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Wprowadzanie nowego auta");

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));

        jPanel2.setBackground(new java.awt.Color(255, 223, 41));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Samochody w firmie");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Dodaj samochód");

        CarsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Numer rej.", "Marka", "Model", "Pojemność silnika", "Rok produkcji", "Cena za dzień", "Dostępność"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(CarsTable);

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Marka:");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Cena za dzień:");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Model:");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Numer rej.:");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Rok produkcji:");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Pojemność silnika:");

        SelectBrand.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Toyota", "Volkswagen", "Ford", "Honda", "Nissan", "Hyundai", "Chevrolet", "Kia", "Mercedes", "BMW " }));

        AddCarBtn.setText("Dodaj auto");
        AddCarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCarBtnActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Dostępność:");

        AvailabilityInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tak", "Nie" }));

        EditBtn.setText("Edytuj");
        EditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditBtnActionPerformed(evt);
            }
        });

        DeleteBtn.setText("Usuń");
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });

        ClearBtn.setText("Wyczyść");
        ClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Capacity))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ModelInput))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CarRegInput)
                                    .addComponent(PriceInput)
                                    .addComponent(AvailabilityInput, javax.swing.GroupLayout.Alignment.TRAILING, 0, 200, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(YearInput)
                                            .addComponent(SelectBrand, 0, 200, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(AddCarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(EditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SelectBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(YearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ModelInput, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CarRegInput, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PriceInput, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(AvailabilityInput, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddCarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(27, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * add car handler
     *
     * @param evt The action event triggered by clicking the "Clear" button.
     */
    private void AddCarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCarBtnActionPerformed
        this.addCar();
    }//GEN-LAST:event_AddCarBtnActionPerformed

    /**
     * Clears the input fields when the "Clear" button is clicked.
     *
     * @param evt The action event triggered by clicking the "Clear" button.
     */
    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        YearInput.setText("");
        Capacity.setText("");
        ModelInput.setText("");
        CarRegInput.setText("");
        PriceInput.setText("");
    }//GEN-LAST:event_ClearBtnActionPerformed

    /**
     * Handles the removal of a car when the "Delete" button is clicked.
     *
     * @param evt The action event triggered by clicking the "Delete" button.
     */
    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        String inputValue = JOptionPane.showInputDialog("Podaj ID samochodu:");
        int id = Integer.parseInt(inputValue);
        controller.deleteCar(id);
        fetchCarListIntoTable();
        Dashboard.fetchCarStats();
    }//GEN-LAST:event_DeleteBtnActionPerformed

    /**
     * Handles the action performed when the "Edit" button is clicked.
     *
     * If the button text is "Zapisz": - Retrieves the values from input fields.
     * - Updates the car information in the database. - Refreshes the car list
     * in the table. - Updates the car statistics on the dashboard. - Enables
     * the "Add" and "Delete" buttons. - Changes the button text back to
     * "Edytuj".
     *
     * If the button text is "Edytuj": - Prompts the user to enter a car ID. -
     * Searches for the corresponding car in the table. - If found, populates
     * the input fields with the car's information. - Disables the "Add" and
     * "Delete" buttons. - Changes the button text to "Zapisz".
     *
     * @param evt The action event triggered by clicking the "Edit" button.
     */
    private void EditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditBtnActionPerformed

        if (EditBtn.getText().equals("Zapisz")) {
            String brand = (String) SelectBrand.getSelectedItem();
            int year = Integer.parseInt(YearInput.getText());
            double capacity = Double.parseDouble(Capacity.getText());
            String model = ModelInput.getText();
            String registration = CarRegInput.getText();
            double price = Double.parseDouble(PriceInput.getText());
            String availability = (String) AvailabilityInput.getSelectedItem();

            boolean availability_bool;
            if (availability.equals("Tak")) {
                availability_bool = true;
            } else {
                availability_bool = false;
            }

            controller.updateCar(edit_car_id, registration, brand, model, capacity, year, price, availability_bool);
            fetchCarListIntoTable();
            Dashboard.fetchCarStats();

            AddCarBtn.setEnabled(true);
            DeleteBtn.setEnabled(true);

            // Zmieniamy tekst na przycisku Edytuj na Zapisz
            EditBtn.setText("Edytuj");

        } else {

            // Wyświetlamy okno dialogowe z polem tekstowym
            String inputValue = JOptionPane.showInputDialog("Podaj ID samochodu:");

            // Sprawdzamy, czy wartość została wprowadzona
            if (inputValue != null && !inputValue.isEmpty()) {
                // Parsujemy wartość do liczby całkowitej
                int id = Integer.parseInt(inputValue);
                edit_car_id = id;
                // Przeszukujemy tabelę w poszukiwaniu wiersza o podanym ID
                DefaultTableModel model = (DefaultTableModel) CarsTable.getModel();
                int rowIndex = -1;

                for (int i = 0; i < model.getRowCount(); i++) {
                    System.out.println("Comparing ID " + id + " with " + model.getValueAt(i, 0));
                    if (String.valueOf(id).equals(model.getValueAt(i, 0))) {
                        rowIndex = i;
                        break;
                    }
                }

                // Jeśli znaleziono wiersz, pobieramy dane z tabeli
                if (rowIndex >= 0) {
                    Object[] rowData = new Object[model.getColumnCount()];
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        rowData[j] = model.getValueAt(rowIndex, j);
                    }

                    // Przykładowe wykorzystanie pobranych danych
                    String car_reqistration = (String) rowData[1];
                    String brand = (String) rowData[2];
                    String model_car = (String) rowData[3];
                    String capacity = (String) rowData[4];
                    String yearStr = (String) rowData[5];
                    int year = Integer.parseInt(yearStr);
                    String price = (String) rowData[6];
                    String availability = (String) rowData[7];

                    String availability_text;
                    if (availability.equals("true")) {
                        availability_text = "Tak";
                    } else {
                        availability_text = "Nie";
                    }

                    // Wyświetlamy dane w polach tekstowych
                    SelectBrand.setSelectedItem(brand);
                    ModelInput.setText(model_car);
                    Capacity.setText(capacity);
                    YearInput.setText(Integer.toString(year));
                    PriceInput.setText(price);
                    CarRegInput.setText(car_reqistration);
                    AvailabilityInput.setSelectedItem(availability_text);

                    // Wyłączamy przyciski Dodaj i Usuń
                    AddCarBtn.setEnabled(false);
                    DeleteBtn.setEnabled(false);

                    // Zmieniamy tekst na przycisku Edytuj na Zapisz
                    EditBtn.setText("Zapisz");

                } else {
                    // Jeśli nie znaleziono wiersza, wyświetlamy odpowiedni komunikat
                    JOptionPane.showMessageDialog(this, "Nie znaleziono samochodu o podanym ID", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_EditBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddCarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCarForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddCarBtn;
    private javax.swing.JComboBox<String> AvailabilityInput;
    private javax.swing.JTextField Capacity;
    private javax.swing.JTextField CarRegInput;
    private javax.swing.JTable CarsTable;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JButton EditBtn;
    private javax.swing.JTextField ModelInput;
    private javax.swing.JTextField PriceInput;
    private javax.swing.JComboBox<String> SelectBrand;
    private javax.swing.JTextField YearInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
