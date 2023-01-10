package com.example.rupizzeria;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the ChicagoOrdering View.
 * Handles pizza options, including type and size.
 * @author Rishabh Patel, Albert Zou
 */
public class ChicagoOrderingController extends SecondaryViewController implements Initializable {
    private ChicagoPizza factory;
    private Pizza pizza;
    @FXML
    private ComboBox flavorBox;
    @FXML
    private ToggleGroup sizeToggles;
    @FXML
    private TextField crust;
    @FXML
    private ImageView image;
    @FXML
    private ListView available;
    @FXML
    private Button selectBtn;
    @FXML
    private Button deselectBtn;
    @FXML
    private ListView selected;
    @FXML
    private TextField price;

    private Order order;

    private static final int MAX_TOPPINGS = 7;

    private static final String[] FLAVORS = {"Build Your Own", "Deluxe", "BBQ Chicken", "Meatzza"};
    private static final String[] DELUXE_TOP = {"Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom"};
    private static final String[] BBQ_CHICKEN_TOP = {"BBQ Chicken", "Green Pepper", "Provolone", "Cheddar"};
    private static final String[] MEATZZA_TOP = {"Sausage", "Pepperoni", "Beef", "Ham"};
    private static final String IMAGES_PATH = "src/main/resources/img/";
    private static final String CD_IMAGE = "chicagoDeluxe.jpg";
    private static final String CBBQ_IMAGE = "chicagoBBQ.jpg";
    private static final String CM_IMAGE = "chicagoMeatzza.jpg";
    private static final String CBYO_IMAGE = "chicagoBYO.jpg";

    /**
     * Constructs ChicagoOrderingController object.
     * Creates new ChicagoPizza factory object.
     */
    public ChicagoOrderingController() {
        factory = new ChicagoPizza();
    }

    /**
     * Creates pizza and sets GUI elements to defaults.
     * @param url default parameter
     * @param resourceBundle default parameter
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pizza = factory.createBuildYourOwn();

        changeImage(CBYO_IMAGE);

        flavorBox.getItems().removeAll(flavorBox.getItems());
        flavorBox.getItems().addAll(FLAVORS);
        flavorBox.getSelectionModel().selectFirst();

        refresh();
    }

    /**
     * Collects storeOrder information from the controller that opens it.
     * @param storeOrder that the GUI is working from.
     */
    @Override
    public void initData(StoreOrders storeOrder) {
        this.order = storeOrder.getCurrOrder();
    }

    /**
     * Updates GUI fields to match the current selection.
     * Sets size, crust, and price fields.
     * Updates toppings menus.
     */
    private void refresh() {
        String s = ((RadioButton) sizeToggles.getSelectedToggle()).getText().toUpperCase();
        pizza.setSize(Size.valueOf(s));

        crust.setText(pizza.crust());

        available.getItems().removeAll(available.getItems());
        available.getItems().addAll(Topping.values());

        selected.getItems().removeAll(selected.getItems());

        price.setText(pizza.price() + "");
    }

    /**
     * Updates the Pizza image to match the style selected.
     * @param imgPath filename of the image to display.
     */
    private void changeImage(String imgPath) {
        try {
            Image img = new Image(new FileInputStream(IMAGES_PATH + imgPath));
            image.setImage(img);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Controls whether the user can select items in the toppings menus.
     * Controls the available and selected fields and the select and deselect buttons.
     * @param notBYO
     */
    private void toggleVisibility(boolean notBYO) {
        available.setDisable(notBYO);
        selectBtn.setDisable(notBYO);
        deselectBtn.setDisable(notBYO);
        selected.setMouseTransparent(notBYO);
        selected.setFocusTraversable(!notBYO);
    }

    /**
     * Updates the GUI based on the flavor of pizza chosen.
     * Updates the pizza variable, and refreshes the GUI and image.
     * Toggles visibility.
     */
    @FXML
    protected void onFlavorClick() {
        switch ( flavorBox.getValue().toString() ) {
            case "Build Your Own":
                pizza = factory.createBuildYourOwn();
                refresh();
                changeImage(CBYO_IMAGE);
                break;
            case "Deluxe":
                pizza = factory.createDeluxe();
                refresh();
                changeImage(CD_IMAGE);
                for ( String topping : DELUXE_TOP) selectTopping(topping);
                break;
            case "BBQ Chicken":
                pizza = factory.createBBQChicken();
                refresh();
                changeImage(CBBQ_IMAGE);
                for ( String topping : BBQ_CHICKEN_TOP) selectTopping(topping);
                break;
            case "Meatzza":
                pizza = factory.createMeatzza();
                refresh();
                changeImage(CM_IMAGE);
                for ( String topping : MEATZZA_TOP) selectTopping(topping);
                break;
        }
        toggleVisibility(!flavorBox.getValue().toString().equals("Build Your Own"));
    }

    /**
     * Updates based on the chosen size.
     * Updates the pizza variable and change the price accordingly.
     */
    @FXML
    public void onSizeClick() {
        String s = ((RadioButton) sizeToggles.getSelectedToggle()).getText().toUpperCase();
        pizza.setSize(Size.valueOf(s));
        price.setText(pizza.price() + "");
    }

    /**
     * Adds the chosen topping.
     * Removes the topping from available and adds it to the pizza
     * @param name the topping to add.
     */
    private void selectTopping(String name) {
        Topping t = Topping.getTopping(name);
        if ( t != null ) {
            selected.getItems().add(t);
            available.getItems().remove(t);
            pizza.add(t);
        }
    }

    /**
     * Adds topping to the pizza when the select button is clicked.
     * Creates a popup if seven toppings have already been chosen.
     * @throws IOException
     */
    @FXML
    protected void onSelectToppingClick() throws IOException {
        Object topping = available.getSelectionModel().getSelectedItem();
        if ( topping != null ) {
            if ( selected.getItems().size() >= MAX_TOPPINGS ) {
                makePopUp("Error", "Can't add more than 7 toppings!");
            } else {
                selectTopping(topping.toString());
                price.setText(pizza.price() + "");
            }
        }
    }

    /**
     * Removes the chosen topping.
     * Adds the topping to available and removes it from pizza
     * @param name the topping to remove.
     */
    private void deselectTopping(String name) {
        Topping t = Topping.getTopping(name);
        if ( t != null ) {
            available.getItems().add(t);
            selected.getItems().remove(t);
            pizza.remove(t);
        }
    }

    /**
     * Removes topping from the pizza when the deselect button is clicked.
     */
    @FXML
    protected void onDeselectToppingClick() {
        Object topping = selected.getSelectionModel().getSelectedItem();
        if ( topping != null ) {
            deselectTopping(topping.toString());
            price.setText(pizza.price() + "");
        }
    }

    /**
     * Adds the pizza to the storeOrder object.
     * Makes a popup confirming that the order has been placed.
     * @throws IOException
     */
    @FXML
    protected void onAddOrderClick() throws IOException {
        order.add(pizza);
        makePopUp("Pizza Added", "Pizza successfully added to order!");
        Stage stage = (Stage) flavorBox.getScene().getWindow();
        stage.close();
    }
}
