package com.example.pizzaapp;

/**
 * Stores sizes
 * Enum class storing size with index and name as a string.
 * @author Rishabh Patel, Albert Zou
 */
public enum Size {
    /**
     * Small data
     */
    SMALL(0, "Small"),

    /**
     * Medium data
     */
    MEDIUM(1, "Medium"),

    /**
     * Large data
     */
    LARGE(2, "Large");
    private final int size;
    private final String name;

    /**
     * Constructs a Size object.
     * Sets index and name variables of the size.
     * @param index index of the size
     * @param name name of the size
     */
    Size(int index, String name) {
        this.size = index;
        this.name = name;
    }

    /**
     * Returns the index of the size
     * @return index of the size
     */
    public int getIndex() {
        return size;
    }

    /**
     * Returns the name of the size
     * @return name of the size.
     */
    @Override
    public String toString() {
        return name;
    }
}
