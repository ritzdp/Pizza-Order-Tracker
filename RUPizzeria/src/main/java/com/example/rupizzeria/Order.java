package com.example.rupizzeria;
import java.util.ArrayList;

/**
 * Represents an order.
 * Provides functionality for the pizzas in the order.
 * @author Rishabh Patel, Albert Zou
 */
public class Order implements Customizable{
    private ArrayList<Pizza> pizzas;
    private int serial;
    private static final double TAX_RATE = 0.06625;
    private static final double PERCENT = 100.0;

    /**
     * Constructs Order object.
     * Sets the serial number to the given value.
     * @param serial The serial number of the order.
     */
    public Order(int serial) {
        this.serial = serial;
        pizzas = new ArrayList<>();
    }

    /**
     * Adds a pizza to an order.
     * @param p pizza to add.
     * @return true if the pizza is
     * added successfully,false otherwise.
     */
    @Override
    public boolean add(Object p) {
        Pizza pizza;
        try {
            pizza = (Pizza) p;
        } catch (Exception e) {
            return false;
        }
        return pizzas.add(pizza);
    }

    /**
     * Removes a pizza from an order.
     * Checks to make sure the object is a pizza.
     * @param p pizza to remove.
     * @return true if the pizza is
     * removed successfully, false otherwise.
     */
    @Override
    public boolean remove(Object p) {
        Pizza pizza;
        try {
            pizza = (Pizza) p;
        } catch (Exception e) {
            return false;
        }
        return pizzas.remove(pizza);
    }

    /**
     * Empties the order.
     * Resets the pizzas ArrayList.
     */
    public void clearCart() {
        pizzas = new ArrayList<>();
    }

    /**
     * Returns the subtotal.
     * Sums up the price before tax of each pizza.
     * @return subtotal of the order.
     */
    public double subtotal() {
        double subtotal = 0;
        for (Pizza p : pizzas) {
            subtotal += p.price();
        }
        return subtotal;
    }

    /**
     * Returns the tax.
     * Multiplies the subtotal by the tax rate.
     * @return the tax amount.
     */
    public double tax() {
        double subtotal = subtotal();
        double tax = Math.round(subtotal * TAX_RATE * PERCENT) / PERCENT;
        return tax;
    }

    /**
     * Returns the total.
     * Adds the subtotal and tax.
     * @return the total.
     */
    public double total() {
        return subtotal() + tax();
    }

    /**
     * Returns the serial number of the order.
     * @return serial number.
     */
    public int getSerial() {
        return serial;
    }

    /**
     * Returns the pizzas in the order.
     * @return ArrayList of pizzas.
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }
}
