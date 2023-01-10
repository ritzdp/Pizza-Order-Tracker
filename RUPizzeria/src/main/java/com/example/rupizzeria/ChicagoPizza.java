package com.example.rupizzeria;

/**
 * Represents a Chicago Pizza factory.
 * Has functionality to make any flavor of pizza in Chicago style.
 * @author Rishabh Patel, Albert Zou
 */
public class ChicagoPizza implements PizzaFactory{

    /**
     * Makes a Chicago Deluxe pizza.
     * @return a Chicago Deluxe pizza.
     */
    public Pizza createDeluxe() {
        Deluxe deluxe = new Deluxe(Crust.DEEP_DISH);
        return deluxe;
    }

    /**
     * Makes a Chicago Meatzza pizza.
     * @return a Chicago Meatzza pizza.
     */
    public Pizza createMeatzza() {
        Meatzza meatzza = new Meatzza(Crust.STUFFED);
        return meatzza;
    }

    /**
     * Makes a Chicago BBQChicken pizza.
     * @return a Chicago BBQChicken pizza.
     */
    public Pizza createBBQChicken() {
        BBQChicken bbqChicken = new BBQChicken(Crust.PAN);
        return bbqChicken;
    }

    /**
     * Makes a Chicago BuildYourOwn pizza.
     * @return a Chicago BuildYourOwn pizza.
     */
    public Pizza createBuildYourOwn() {
        BuildYourOwn byo = new BuildYourOwn(Crust.PAN);
        return byo;
    }
}
