package com.example.rupizzeria;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Tests the BuildYourOwn class.
 * Checks for correct calculation in the price() method
 * @author Albert Zou, Rishabh Patel
 */
public class BuildYourOwnTest {
    private static final double TOPPING_PRICE = 1.59;
    private static final double [] PRICES = {8.99, 10.99, 12.99};
    private static final int SMALL = 0;
    private static final int MEDIUM = 1;
    private static final int LARGE = 2;
    private static final double DELTA = .01;


    /**
     * Randomly adds the given amount of toppings to the pizza
     * @param numToppings the number of toppings to add
     * @param pizza the pizza to add to
     */
    private void addToppings(int numToppings, Pizza pizza) {
        ArrayList<Topping> toppingsLeft = new ArrayList<>();
        toppingsLeft.addAll(Arrays.asList(Topping.values()));
        for (int i = 0; i < numToppings; i++) {
            pizza.add(toppingsLeft.remove((int) Math.random() * toppingsLeft.size()));
        }

    }

    /**
     * Tests a small plain pizza
     */
    @Test
    public void smallPlainPrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.PAN);
        byo.setSize(Size.SMALL);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[SMALL] + 0 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a medium plain pizza
     */
    @Test
    public void mediumPlainPrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.HAND_TOSSED);
        byo.setSize(Size.MEDIUM);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[MEDIUM] + 0 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a large pizza with one topping
     */
    @Test
    public void LargeOnePrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.PAN);
        byo.setSize(Size.LARGE);
        addToppings(1, byo);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[LARGE] + 1 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a small pizza with one topping
     */
    @Test
    public void smallOnePrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.HAND_TOSSED);
        byo.setSize(Size.SMALL);
        addToppings(1, byo);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[SMALL] + 1 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a large pizza with two toppings
     */
    @Test
    public void MediumTwoPrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.PAN);
        byo.setSize(Size.MEDIUM);
        addToppings(2, byo);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[MEDIUM] + 2 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a small pizza with two toppings
     */
    @Test
    public void smallTwoPrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.HAND_TOSSED);
        byo.setSize(Size.SMALL);
        addToppings(2, byo);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[SMALL] + 2 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a medium pizza with three toppings
     */
    @Test
    public void mediumThreePrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.PAN);
        byo.setSize(Size.MEDIUM);
        addToppings(3, byo);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[MEDIUM] + 3 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a large pizza with four toppings
     */
    @Test
    public void largeFourPrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.HAND_TOSSED);
        byo.setSize(Size.LARGE);
        addToppings(4, byo);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[LARGE] + 4 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a small pizza with 4 toppings
     */
    @Test
    public void smallFourPrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.PAN);
        byo.setSize(Size.SMALL);
        addToppings(4, byo);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[SMALL] + 4 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a medium pizza with five toppings
     */
    @Test
    public void mediumFivePrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.HAND_TOSSED);
        byo.setSize(Size.MEDIUM);
        addToppings(5, byo);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[MEDIUM] + 5 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a large pizza with five toppings
     */
    @Test
    public void largeFivePrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.PAN);
        byo.setSize(Size.LARGE);
        addToppings(5, byo);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[LARGE] + 5 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a small pizza with 6 toppings
     */
    @Test
    public void smallSixPrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.HAND_TOSSED);
        byo.setSize(Size.SMALL);
        addToppings(6, byo);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[SMALL] + 6 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a medium pizza with 6 toppings
     */
    @Test
    public void mediumSixPrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.PAN);
        byo.setSize(Size.MEDIUM);
        addToppings(6, byo);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[MEDIUM] + 6 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a large pizza with seven toppings
     */
    @Test
    public void largeSevenPrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.HAND_TOSSED);
        byo.setSize(Size.LARGE);
        addToppings(7, byo);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[LARGE] + 7 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }

    /**
     * Tests a small pizza with seven toppings
     */
    @Test
    public void smallSevenPrice() {
        BuildYourOwn byo = new BuildYourOwn(Crust.PAN);
        byo.setSize(Size.SMALL);
        addToppings(7, byo);
        double actualOutput = byo.price();
        double expectedOutput = PRICES[SMALL] + 7 * TOPPING_PRICE;
        assertEquals(actualOutput, expectedOutput, DELTA);
    }
}