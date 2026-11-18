package com.mycompany.mattresssalesapplication;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class MattressSalesApplication extends JFrame {

    private JTextField txtCustomerName;
    private JTextField txtPrice;
    private JComboBox<String> cmbProducts;
    private JButton btnProcess;

    public MattressSalesApplication() {

        // Set up the JFrame
        setTitle("Product App");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create the main panel
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 25, 30, 25)
        );

        // Create labels
        JLabel lblCustomer = new JLabel("Customer Name:");
        JLabel lblProduct = new JLabel("Select Product:");
        JLabel lblPrice = new JLabel("Enter Price:");

        // Create text fields
        txtCustomerName = new JTextField();
        txtPrice = new JTextField();

        // Create the product combo box
        String[] products = {"Cozy Nights Monarch","Restonic Barbados","Sealy Phuket"};

        cmbProducts = new JComboBox<>(products);

        // Create the process button
        btnProcess = new JButton("PROCESS");

        // Add components to the panel
        mainPanel.add(lblCustomer);
        mainPanel.add(txtCustomerName);

        mainPanel.add(lblProduct);
        mainPanel.add(cmbProducts);

        mainPanel.add(lblPrice);
        mainPanel.add(txtPrice);

        mainPanel.add(new JLabel(""));
        mainPanel.add(btnProcess);

        // Add panel to the frame
        add(mainPanel);
        
        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create File and Tools menus
        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");

        // Create menu items
        JMenuItem menuExit = new JMenuItem("Exit");
        JMenuItem menuProcess = new JMenuItem("Process");
        JMenuItem menuClear = new JMenuItem("Clear");

        // Add menu items to their menus
        fileMenu.add(menuExit);

        toolsMenu.add(menuProcess);
        toolsMenu.add(menuClear);

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);

        // PROCESS button event
        btnProcess.addActionListener(e -> processSale());

        // File menu event
        menuExit.addActionListener(e -> System.exit(0));

        // Tools menu events
        menuProcess.addActionListener(e -> processSale());
        menuClear.addActionListener(e -> clearFields());

        setVisible(true);

    }
    
    // Clear the text fields and reset the product selection
    private void clearFields() {

    // Clear customer name
    txtCustomerName.setText("");

    // Clear product price
    txtPrice.setText("");

    // Reset combo box to the first product
    cmbProducts.setSelectedIndex(0);

    // Return focus to customer name
    txtCustomerName.requestFocus();
}

    // Process the mattress sale and display the results
    private void processSale() {

        try {
        // Capture customer and product information
        String customerName = txtCustomerName.getText().trim();
        String productName = cmbProducts.getSelectedItem().toString();
        double price = Double.parseDouble(txtPrice.getText().trim());

        // Validate customer name
        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter the customer name.");
            return;
        }

        // Validate product price
        if (!Double.isFinite(price) || price <= 0) {
            JOptionPane.showMessageDialog(this,"Please enter a valid positive price.");
            return;
        }

        
        // Create an object to perform the sales calculations
        ProductCalculations calculations = new ProductCalculations(price);

        // Calculates VAT, commission and the total
        double vat = calculations.GetVat();
        double commission = calculations.GetCommission();
        double total = calculations.GetTotal();

        // Create the sales report
        String report ="Customer Name: " + customerName+ "\nProduct: " + productName + "\nVAT: R " + String.format("%.1f", vat)
                       + "\nCOMMISSION: R " + String.format("%.1f", commission) + "\nTOTAL: R " + String.format("%.1f", total);

        // Display the results in a message dialog
        JOptionPane.showMessageDialog(this,report,"Message",JOptionPane.INFORMATION_MESSAGE);

        // Save the sales information to data.txt
        try (FileWriter writer = new FileWriter("data.txt")) {

            writer.write("DATA LOG\n");
            writer.write("********************************\n");
            writer.write(report);
            writer.write("\n********************************\n");

        }

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(this,"Please enter a valid product price.","Invalid Price",JOptionPane.ERROR_MESSAGE);

    } catch (IOException ex) {

        JOptionPane.showMessageDialog(this,"Error saving sales data to data.txt","File Error",JOptionPane.ERROR_MESSAGE);
    }
}

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new MattressSalesApplication();
        });
    }
}

