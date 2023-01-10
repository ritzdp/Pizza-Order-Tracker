package com.example.rupizzeria;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a deluxe pizza.
 * Extends the Pizza abstract Class.
 * @author Rishabh Patel, Albert Zou
 */
public class Deluxe extends Pizza {
    private static final double [] PRICES = {14.99, 16.99, 18.99};

    Topping[] DELUXETOPPINGS = {Topping.SAUSAGE, Topping.PEPPERONI,
            Topping.GREEN_PEPPER, Topping.ONION, Topping.MUSHROOM};


    /**
     * Constructs Deluxe object given the crust type.
     * Initializes the toppings ArrayList.
     * @param crust the crust to make the pizza with.
     */
    public Deluxe(Crust crust) {
        super();
        this.crust = crust;
        Collections.addAll(toppings, DELUXETOPPINGS);
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
        output = "Deluxe (" + crust + "), " +  output + ", $" + price();
        return output;
    }
}
