module org.example.proyectofinalcodigo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.proyectofinalcodigo to javafx.fxml;
    exports org.example.proyectofinalcodigo;
    exports org.example.proyectofinalcodigo.model;
    opens org.example.proyectofinalcodigo.model to javafx.fxml;
    exports org.example.proyectofinalcodigo.model.clases;
    opens org.example.proyectofinalcodigo.model.clases to javafx.fxml;
    exports org.example.proyectofinalcodigo.model.clasesAbstractas;
    opens org.example.proyectofinalcodigo.model.clasesAbstractas to javafx.fxml;
}