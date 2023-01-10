package com.example.rupizzeria;

/**
 * Interface for customizable objects.
 * Declares methods for adding and removing objects from objects.
 */
public interface Customizable {
    /**
     * Adds an object.
     * @param obj object to add.
     * @return true if the object is added successfully, false otherwise.
     */
    boolean add(Object obj);

    /**
     * Removes an object.
     * @param obj object to remove.
     * @return true if the object is removed successfully, false otherwise.
     */
    boolean remove(Object obj);
}
