module org.example.proyectofinalcodigo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.proyectofinalcodigo to javafx.fxml;
    exports org.example.proyectofinalcodigo;
}