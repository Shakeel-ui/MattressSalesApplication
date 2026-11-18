package com.mycompany.mattresssalesapplication;


public class ProductCalculations implements IProductCalculations {

    private double price;

    // Constructor to receive the products price
    public ProductCalculations(double price) {
        this.price = price;
    }

    // Calculates the VAT at 15% of the products price
    @Override
    public double GetVat() {
        return price * 0.15;
    }

    // Calculates commission at 5% of the product price
    @Override
    public double GetCommission() {
        return price * 0.05;
    }

    // Calculates the total sale amount
    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }
}

