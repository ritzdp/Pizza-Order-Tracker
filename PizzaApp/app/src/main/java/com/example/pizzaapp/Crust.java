package com.example.pizzaapp;

/**
 * Stores crust types with related information
 * Enum class storing crust type with style and its name as a string.
 * @author Rishabh Patel, Albert Zou
 */
public enum Crust {
    /**
     * Deep Dish data
     */
    DEEP_DISH("Chicago Style", "Deep Dish"),
    /**
     * Brooklyn data
     */
    BROOKLYN("New York Style", "Brooklyn"),
    /**
     * Stuffed data
     */
    STUFFED("Chicago Style", "Stuffed"),
    /**
     * Thin data
     */
    THIN("New York Style", "Thin"),
    /**
     * Hand Tossed data
     */
    HAND_TOSSED("New York Style", "Hand Tossed"),
    /**
     * Pan data
     */
    PAN("Chicago Style", "Pan");

    private final String name;
    private final String style;

    /**
     * Constructs a Crust object.
     * Sets style and name instance variables to corresponding arguments.
     * @param style code of the location.
     * @param name of the location.
     */
    Crust(String style, String name) {
        this.style = style;
        this.name = name;
    }

    /**
     * Returns the name of the crust
     * @return String containing the crust
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the style and name of the crust
     * @return String containing the full name of the crust.
     */
    @Override
    public String toString() {
        return style + " - " + name;
    }
}
