package com.example.rupizzeria;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a Meatzza pizza.
 * Extends the Pizza abstract Class.
 * @author Rishabh Patel, Albert Zou
 */
public class Meatzza extends Pizza {
    private static final double [] PRICES = {15.99, 17.99, 19.99};
    Topping[] MEATZZATOPPINGS = {Topping.SAUSAGE, Topping.PEPPERONI,
            Topping.BEEF, Topping.HAM};

    /**
     * Constructs Meatzza object given the crust type.
     * Initializes the toppings ArrayList.
     * @param crust the crust to make the pizza with.
     */
    public Meatzza(Crust crust) {
        super();
        this.crust = crust;
        Collections.addAll(toppings, MEATZZATOPPINGS);
    }

    /**
     * Sets the size of the pizza.
     * private size variable is set to the correct Size-type enum.
     * @param size the size to set the pizza to.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Returns the price of the pizza.
     * returns the correct value based on the size of the pizza.
     * @return price of the pizza.
     */
    public double price() {
        return PRICES[size.getIndex()];
    }

    /**
     * Returns the crust type in string form.
     * Returns "No Crust" if crust is null.
     * @return string representing the pizza's crust.
     */
    public String crust() {
        return crust == null ? "No Crust" : crust.getName();
    }

    /**
     * Returns the pizza as a string.
     * All the information about the pizza is returned.
     * @return string representing the pizza.
     */
    @Override
    public String toString() {
        String output = super.toString();
        output = "Meatzza (" + crust + "), " +  output + ", $" + price();
        return output;
    }
}
