package com.example.rupizzeria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class for the RU Pizzeria GUI.
 * Launches the Java FX application.
 * @author Rishabh Patel, Albert Zou
 */
public class PizzaApplication extends Application {
    /**
     * Starts the application, setting up the stage and scene.
     * @param stage received from the launching of the application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PizzaApplication.class.getResource("MainView.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch ( IOException e ) {
            throw new IOException(e);
        }
        stage.setTitle("RU Pizzeria");
        stage.show();
    }

    /**
     * Launches the application.
     * @param args from the command line
     */
    public static void main(String[] args) {
        launch();
    }
}