package com.example.rupizzeria;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the CurrentOrdering View.
 * Allows for deleting pizzas from and placing the order.
 * @author Rishabh Patel, Albert Zou
 */
public class CurrentOrderingController extends SecondaryViewController {
    @FXML
    private TextField orderNum;
    @FXML
    private ListView pizzas;
    @FXML
    private TextField subtotal;
    @FXML
    private TextField salesTax;
    @FXML
    private TextField total;
    private Order order;
    private StoreOrders storeOrder;

    /**
     * Updates values when selections change.
     * Updates the pizzas in the order
     * Updates the subtotal, salesTax, and total.
     */
    private void refresh() {
        orderNum.setText(order.getSerial() + "");
        pizzas.getItems().removeAll(pizzas.getItems());
        pizzas.getItems().addAll(order.getPizzas());
        subtotal.setText(String.format("%.2f", order.subtotal()));
        salesTax.setText(String.format("%.2f", order.tax()));
        total.setText(String.format("%.2f", order.total()));
    }

    /**
     * Collects storeOrder information from the controller that opens it.
     * @param storeOrder that the GUI is working from.
     */
    public void initData(StoreOrders storeOrder) {
        this.storeOrder = storeOrder;
        order = storeOrder.getCurrOrder();
        refresh();
    }

    /**
     * Removes the selected pizza from the order.
     * Removes the pizza from the order and from the pizzas field.
     */
    @FXML
    public void onRemovePizzaClick() {
        Object selected = pizzas.getSelectionModel().getSelectedItem();
        order.remove(selected);
        pizzas.getItems().remove(selected);
        refresh();
    }

    /**
     * Places the current order.
     * Makes a popup confirming that the order has been placed.
     * @throws IOException
     */
    @FXML
    public void onPlaceOrderClick() throws IOException {
        if ( storeOrder.add(order) ) {
            order = storeOrder.getCurrOrder();
            refresh();
            makePopUp("Order Placed", "Order placed successfully!");
            Stage stage = (Stage) pizzas.getScene().getWindow();
            stage.close();
        } else {
            makePopUp("Error", "The order is empty.");
        }
    }

    /**
     * Clears the order.
     * Empties the current order object and defaults the GUI fields.
     */
    @FXML
    public void onClearOrderClick() {
        order.clearCart();
        refresh();
    }


}
