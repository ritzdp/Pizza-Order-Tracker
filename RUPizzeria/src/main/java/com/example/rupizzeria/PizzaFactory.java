package com.example.rupizzeria;

/**
 * Interface for making pizzas.
 */
public interface PizzaFactory {
    /**
     * Makes a Deluxe
     * @return Deluxe
     */
    Pizza createDeluxe();
    /**
     * Makes a Meatzza
     * @return Meatzza
     */
    Pizza createMeatzza();
    /**
     * Makes a BBQChicken
     * @return BBQChicken
     */
    Pizza createBBQChicken();
    /**
     * Makes a BuildYourOwn
     * @return BuildYourOwn
     */
    Pizza createBuildYourOwn();
}
