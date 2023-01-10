package com.example.pizzaapp;

/**
 * Stores toppings
 * Enum class storing toppings with their names as a string.
 * @author Rishabh Patel, Albert Zou
 */
public enum Topping {
    /**
     * BBQ Chicken data
     */
    BBQ_CHICKEN("BBQ Chicken"),
    /**
     * Beef data
     */
    BEEF("Beef"),
    /**
     * Black Olives data
     */
    BLACK_OLIVES("Black Olive"),
    /**
     * Cheddar data
     */
    CHEDDAR("Cheddar"),
    /**
     * Green Pepper data
     */
    GREEN_PEPPER("Green Pepper"),
    /**
     * Ham data
     */
    HAM("Ham"),
    /**
     * Mushroom data
     */
    MUSHROOM("Mushroom"),
    /**
     * Onion data
     */
    ONION("Onion"),
    /**
     * Pepperoni data
     */
    PEPPERONI("Pepperoni"),
    /**
     * Pineapple data
     */
    PINEAPPLE("Pineapple"),
    /**
     * Provolone data
     */
    PROVOLONE("Provolone"),
    /**
     * Sausage data
     */
    SAUSAGE("Sausage"),
    /**
     * Spinach data
     */
    SPINACH("Spinach");
    private final String name;

    /**
     * Constructs a Topping object.
     * Sets name instance variable to corresponding argument.
     * @param name of the topping.
     */
    Topping(String name) {
        this.name = name;
    }

    /**
     * Returns the topping (as Topping object).
     * Handles error of the string not existing.
     * @param topping to get Topping object of.
     * @return Topping enum type of the input string
     * if found, else null
     */
    public static Topping getTopping(String topping) {
        for ( Topping t : Topping.values() ) {
            if ( t.toString().equals(topping) ) return t;
        }
        return null;
    }

    /**
     * Returns the topping in String format.
     * @return the name of the topping.
     */
    @Override
    public String toString() {
        return name;
    }
}
