module com.example.rupizzeria {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires junit;
    requires org.testng;

    opens com.example.rupizzeria to javafx.fxml;
    exports com.example.rupizzeria;
}