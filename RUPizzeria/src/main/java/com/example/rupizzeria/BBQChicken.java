package com.example.rupizzeria;
import java.util.Collections;

/**
 * Represents a barbecue chicken pizza.
 * Extends the Pizza abstract Class.
 * @author Rishabh Patel, Albert Zou
 */
public class BBQChicken extends Pizza {

    private static final double [] PRICES = {13.99, 15.99, 17.99};
    Topping[] BBQTOPPINGS = {Topping.BBQ_CHICKEN, Topping.GREEN_PEPPER,
            Topping.PROVOLONE, Topping.CHEDDAR};


    /**
     * Constructs BBQChicken object given the crust type.
     * Initializes the toppings ArrayList.
     * @param crust the crust to make the pizza with.
     */
    public BBQChicken(Crust crust) {
        super();
        this.crust = crust;
        Collections.addAll(toppings, BBQTOPPINGS);
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
        output = "BBQ Chicken (" + crust + "), " +  output + ", $" + price();
        return output;
    }
}
