package com.example.rupizzeria;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Controller for PopUp View.
 * Alerts user with a message.
 * @author Rishabh Patel, Albert Zou
 */
public class PopUpController {
    @FXML
    private TextArea msg;

    /**
     * Receives message to display.
     * @param message String containing message to display.
     */
    public void initData(String message) {
        msg.setText(message);
    }

    /**
     * Closes window when button is clicked.
     */
    @FXML
    public void onAightBetClick() {
        Stage stage = (Stage) msg.getScene().getWindow();
        stage.close();
    }
}
