package com.example.rupizzeria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the Main View.
 * Allows for opening of different Menus and stores information centrally.
 * @author Rishabh Patel, Albert Zou.
 */
public class MainController {
    private StoreOrders storeOrder;
    private static final int CHICAGO = 1;
    private static final int NY = 2;
    private static final int STORE = 3;
    private static final int CURRENT = 4;

    /**
     * Constructs MainController object.
     * Constructs a StoreOrders object.
     */
    public MainController() {
        storeOrder = new StoreOrders();
    }

    /**
     * Opens a scene based on the given type.
     * Loads the relevant FXML fle, creates the scene and stage, and
     * passes in the storeOrder.
     * @param type The view to open.
     */
    private void openScene(int type) {
        String file = "", title = "";
        switch ( type ) {
            case CHICAGO:
                file = "ChicagoOrderingView.fxml";
                title = "Chicago Style Pizza";
                break;
            case NY:
                file = "NYOrderingView.fxml";
                title = "New York Style Pizza";
                break;
            case STORE:
                file = "StoreOrdersView.fxml";
                title = "Store Orders";
                break;
            case CURRENT:
                file = "CurrentOrderingView.fxml";
                title = "Order Detail";
                break;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
        Stage newStage = new Stage();
        try {
            newStage.setScene(new Scene(loader.load()));
        } catch ( IOException e ) {
            throw new RuntimeException();
        }
        newStage.setTitle(title);
        SecondaryViewController controller = loader.getController();
        controller.initData(storeOrder);
        newStage.show();
    }

    /**
     * Opens the Chicago scene.
     */
    @FXML
    protected void onChicagoClick() {
        openScene(CHICAGO);
    }

    /**
     * Opens the NY scene.
     */
    @FXML
    protected void onNYClick() {
        openScene(NY);
    }

    /**
     * Opens the StoreOrder scene.
     */
    @FXML
    protected void onStoreOrdersClick() {
        openScene(STORE);
    }

    /**
     * Opens the CurrentOrder scene.
     */
    @FXML
    protected void onCurrentOrderClick() {
        openScene(CURRENT);
    }
}
