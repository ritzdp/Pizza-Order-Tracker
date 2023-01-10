package com.example.rupizzeria;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for the StoreOrders View.
 * Allows for viewing, cancelling, and exporting
 * orders in the store.
 * @author Rishabh Patel, Albert Zou
 */
public class StoreOrdersController extends SecondaryViewController {
    private StoreOrders storeOrder;
    @FXML
    private ComboBox orderBox;
    @FXML
    private TextField totalWithTax;
    @FXML
    private ListView pizzas;

    /**
     * Collects storeOrder information from the controller that opens it.
     * @param storeOrder that the GUI is working from.
     */
    public void initData(StoreOrders storeOrder) {
        this.storeOrder = storeOrder;
        start();
    }

    /**
     * A function to initialize the stage AFTER initData is called.
     * This makes the class not extend the Intializable class.
     */
    public void start() {
        orderBox.getItems().removeAll(orderBox.getItems());
        orderBox.getItems().addAll(storeOrder.getOrderNums());
        orderBox.getSelectionModel().selectFirst();
        refresh();
    }

    /**
     * Updates values when selections change.
     * Updates the orders.
     * Updates the totalWithTax value.
     */
    private void refresh() {
        pizzas.getItems().removeAll(pizzas.getItems());
        if ( orderBox.getItems().size() > 0 ) {
            Order selected = storeOrder.getOrder((Integer) orderBox.getValue());
            pizzas.getItems().addAll(selected.getPizzas());
            totalWithTax.setText(String.format("%.2f", selected.total()));
        } else {
            totalWithTax.setText("");
        }
    }

    /**
     * Refreshes the page with the appropriate order.
     * Calls the refresh() method to retrieve data from the orderBox.
     */
    @FXML
    public void onOrderNumClick() {
        refresh();
    }

    /**
     * Cancels the currently selected order and refreshes.
     * Handles edge case where there are no orders.
     */
    @FXML
    public void onCancelClick() {
        storeOrder.remove(orderBox.getSelectionModel().getSelectedItem());
        orderBox.getItems().remove(orderBox.getSelectionModel().getSelectedItem());
        if ( orderBox.getItems().size() > 0 ) orderBox.getSelectionModel().select(0);
        refresh();
    }

    /**
     * Calls the .export() method in StoreOrders
     * to export the orders locally
     * @throws IOException
     */
    @FXML
    public void onExportClick() throws IOException {
        storeOrder.export();
    }
}
