module org.example.proyectofinalcodigo {
    requires javafx.controls;
    requires javafx.fxml;


    exports org.example.proyectofinalcodigo.model.clases;
    opens org.example.proyectofinalcodigo.model.clases to javafx.fxml;
    exports org.example.proyectofinalcodigo.model.clasesAbstractas;
    opens org.example.proyectofinalcodigo.model.clasesAbstractas to javafx.fxml;
    exports org.example.proyectofinalcodigo.model.enums;
    opens org.example.proyectofinalcodigo.model.enums to javafx.fxml;
    exports org.example.proyectofinalcodigo.model.interfaces;
    opens org.example.proyectofinalcodigo.model.interfaces to javafx.fxml;
    exports org.example.proyectofinalcodigo.model.records;
    opens org.example.proyectofinalcodigo.model.records to javafx.fxml;
}
