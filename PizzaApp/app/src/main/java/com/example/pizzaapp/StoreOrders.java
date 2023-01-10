package com.example.pizzaapp;

import android.util.Log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a database of orders.
 * Provides functionality for checking
 * the orders in the store.
 * @author Rishabh Patel, Albert Zou
 */
public class StoreOrders implements Customizable, Serializable {
    private ArrayList<Order> orders;
    private ArrayList<Integer> orderNums;
    private int nextSerial;
    private Order currOrder;
    private static final int START_SERIAL = 1;
    private static final int EMPTY = 0;
    private static final String FILEPATH = "src/main/resources/storeOrders.txt";

    /**
     * Constructs StoreOrders object.
     * Creates new ArrayLists and serials for the orders.
     */
    public StoreOrders() {
        orders = new ArrayList<>();
        orderNums = new ArrayList<>();
        nextSerial = START_SERIAL;
        currOrder = new Order(nextSerial++);
    }

    /**
     * Adds an order to the database
     * @param o object to add.
     * @return true if the order was
     * added successfully, false otherwise.
     */
    @Override
    public boolean add(Object o) {
        Order order;
        try {
            order = (Order) o;
        } catch ( Exception e ) {
            return false;
        }
        if ( order.getPizzas().size() == EMPTY ) {
            return false;
        }
        currOrder = new Order(nextSerial++);
        return orders.add(order) && orderNums.add(order.getSerial());
    }

    /**
     * Removes an order from the database.
     * Checks to make sure the order exists.
     * @param s object to remove.
     * @return true if the pizza is removed
     * successfully, false otherwise.
     */
    @Override
    public boolean remove(Object s) {
        Integer serial;
        try {
            serial = (Integer) s;
        } catch ( Exception e ) {
            return false;
        }
        for ( int i = 0; i < orderNums.size(); i++ ) {
            if ( orderNums.get(i).equals(serial) ) {
                orders.remove(i);
                orderNums.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the size of the database.
     * @return an integer of the database size.
     */
    public int getSize() {
        return orders.size();
    }

    /**
     * Returns the current order.
     * @return Order object of the current order.
     */
    public Order getCurrOrder() {
        return currOrder;
    }

    /**
     * Gets the Order object from the serial number.
     * @param serial number of the order.
     * @return Order with the serial number inputted.
     */
    public Order getOrder(int serial) {
        for ( Order o : orders ) {
            if ( o.getSerial() == serial ) {
                return o;
            }
        }
        return null;
    }

    /**
     * Returns the list of order numbers.
     * @return an ArrayList of the order numbers.
     */
    public ArrayList<Integer> getOrderNums() {
        return orderNums;
    }

    /**
     * Exports the database orders into a local file.
     * Formats the string as well.
     * @throws IOException
     */
    public void export() throws IOException {
        FileWriter f = new FileWriter(FILEPATH);
        for ( Order o : orders ) {
            f.write("Order " + o.getSerial() + ":\n" );
            for ( Pizza p : o.getPizzas() ) {
                f.write(p + "\n");
            }
            f.write(String.format("Subtotal: $%.2f\nSales Tax: $%.2f\nOrder Total: $%.2f\n\n",
                    o.subtotal(), o.tax(), o.total()));
        }
        f.close();
    }

    public ArrayList<String> getStringNums() {
        ArrayList<String> output = new ArrayList<>();
        for ( int num : orderNums ) {
            output.add(Integer.toString(num));
        }
        return output;
    }
}
