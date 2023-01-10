package com.example.rupizzeria;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Abstract class for Secondary Views.
 * Allows for declaring the different views with one class.
 * @author Rishabh Patel, Albert Zou
 */
public abstract class SecondaryViewController {
    /**
     * Receives storeOrder from the method caller.
     * @param storeOrder storeOrder to access.
     */
    public abstract void initData(StoreOrders storeOrder);

    /**
     * Creates PopUp view.
     * @param title title of the PopUp window.
     * @param msg message to display in the popup.
     * @throws IOException
     */
    protected void makePopUp(String title, String msg) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUpView.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
        } catch ( IOException e ) {
            throw new IOException();
        }
        PopUpController controller = loader.getController();
        controller.initData(msg);
        stage.setTitle(title);
        stage.show();
    }
}
