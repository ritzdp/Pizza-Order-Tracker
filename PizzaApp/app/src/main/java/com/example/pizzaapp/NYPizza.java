package com.example.pizzaapp;

/**
 * Represents a NY Pizza factory.
 * Has functionality to make any flavor of pizza in NY style.
 * @author Rishabh Patel, Albert Zou
 */
public class NYPizza implements PizzaFactory{

    /**
     * Makes a NY Deluxe pizza.
     * @return a NY Deluxe pizza.
     */
    public Pizza createDeluxe() {
        Deluxe deluxe = new Deluxe(Crust.BROOKLYN);
        return deluxe;
    }

    /**
     * Makes a NY Meatzza pizza.
     * @return a NY Meatzza pizza.
     */
    public Pizza createMeatzza() {
        Meatzza meatzza = new Meatzza(Crust.HAND_TOSSED);
        return meatzza;
    }

    /**
     * Makes a NY BBQChicken pizza.
     * @return a NY BBQChicken pizza.
     */
    public Pizza createBBQChicken() {
        BBQChicken bbqChicken = new BBQChicken(Crust.THIN);
        return bbqChicken;
    }

    /**
     * Makes a NY BuildYourOwn pizza.
     * @return a NY BuildYourOwn pizza.
     */
    public Pizza createBuildYourOwn() {
        BuildYourOwn byo = new BuildYourOwn(Crust.HAND_TOSSED);
        return byo;
    }
}
