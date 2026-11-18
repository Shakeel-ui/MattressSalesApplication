package com.mycompany.mattresssalesapplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductCalculationsTest {

    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {

        // Create an object with a product price of R3500
        ProductCalculations product = new ProductCalculations(3500);

        // Expected VAT at 15%
        double expected = 525.0;

        // Calculate the actual VAT
        double actual = product.GetVat();

        // Check if VAT is calculated correctly
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {

        // Create an object with a product price of R3500
        ProductCalculations product = new ProductCalculations(3500);

        // Expected total including VAT and commission
        double expected = 4200.0;

        // Calculate the actual total
        double actual = product.GetTotal();

        // Check if the total is calculated correctly
        assertEquals(expected, actual, 0.001);
    }
}

