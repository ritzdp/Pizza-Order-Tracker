package com.example.rupizzeria;

import java.util.ArrayList;

/**
 * abstract class representing a Pizza
 * Provides skeleton functionality for pizzas.
 * @author Rishabh Patel, Albert Zou
 */
public abstract class Pizza implements Customizable {
    protected ArrayList<Topping> toppings;
    protected Crust crust;
    protected Size size;

    /**
     * Sets size of pizza.
     * @param size size to set
     */
    public abstract void setSize(Size size);

    /**
     * Returns price of pizza.
     * @return price of pizza
     */
    public abstract double price();

    /**
     * Returns crust.
     * @return crust of the pizza.
     */
    public abstract String crust();

    /**
     * Constructs Pizza object.
     * Initializes toppings arraylist and sets size to small.
     */
    public Pizza() {
        toppings = new ArrayList<>();
        size = Size.SMALL;
    }

    /**
     * Adds topping to pizza
     * Checks to confirm it is a topping.
     * @param topp topping to add.
     * @return true if topping is added successfully, false otherwise.
     */
    @Override
    public boolean add(Object topp) {
        Topping topping;
        try {
            topping = (Topping) topp;
        } catch (Exception e) {
            return false;
        }
        return toppings.add(topping);
    }

    /**
     * Removes topping to pizza
     * Checks to confirm it is a topping.
     * @param topp topping to remove.
     * @return true if topping is removed successfully, false otherwise.
     */
    @Override
    public boolean remove(Object topp) {
        Topping topping;
        try {
            topping = (Topping) topp;
        } catch (Exception e) {
            return false;
        }
        return toppings.remove(topping);
    }

    /**
     * Returns a string containing information about the pizza.
     * Includes toppings and size.
     * @return the base string of the pizza.
     */
    @Override
    public String toString() {
        String toppingString = toppings.toString();
        toppingString = toppingString.substring(1, toppingString.length()-1);
        if ( toppingString.equals("") ) toppingString = "No Toppings";
        String output = toppingString + ", " + size;
        return output;
    }
}
