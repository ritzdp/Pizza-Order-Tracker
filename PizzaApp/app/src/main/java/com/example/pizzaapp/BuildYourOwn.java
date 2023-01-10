package com.example.pizzaapp;

/**
 * Represents a customizable pizza.
 * Extends the Pizza abstract class.
 * @author Rishabh Patel, Albert Zou
 */
public class BuildYourOwn extends Pizza {
    private static final double percent = 100;
    private static final double TOPPING_PRICE = 1.59;
    private static final double [] PRICES = {8.99, 10.99, 12.99};


    /**
     * Constructs BuildYourOwn object given the crust type.
     * sets the crust to the given crust.
     * @param crust the crust to make the pizza with.
     */
    public BuildYourOwn(Crust crust) {
        super();
        this.crust = crust;
    }

    /**
     * Sets the size of the pizza.
     * size variable is set to the correct Size-type enum.
     * @param size the size to set the pizza to.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Returns the price of the pizza.
     * Calculates the price of the pizza based on the size and number of toppings
     * @return the price of the pizza.
     */
    public double price() {
        double output = PRICES[size.getIndex()] + TOPPING_PRICE * toppings.size();
        return (double) Math.round(output * percent) / percent;
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
        output = "Build Your Own (" + crust + "), " +  output + ", $" + price();
        return output;
    }

}
