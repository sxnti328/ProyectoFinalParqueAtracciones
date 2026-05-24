module org.example.proyectofinalcodigo {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.example.proyectofinalcodigo;
    opens   org.example.proyectofinalcodigo to javafx.fxml;

    exports org.example.proyectofinalcodigo.viewController;
    opens   org.example.proyectofinalcodigo.viewController to javafx.fxml;

    exports org.example.proyectofinalcodigo.controller;
    opens   org.example.proyectofinalcodigo.controller to javafx.fxml;

    exports org.example.proyectofinalcodigo.model.clases;
    opens   org.example.proyectofinalcodigo.model.clases to javafx.fxml;

    exports org.example.proyectofinalcodigo.model.clasesAbstractas;
    opens   org.example.proyectofinalcodigo.model.clasesAbstractas to javafx.fxml;

    exports org.example.proyectofinalcodigo.model.enums;
    opens   org.example.proyectofinalcodigo.model.enums to javafx.fxml;

    exports org.example.proyectofinalcodigo.model.interfaces;
    opens   org.example.proyectofinalcodigo.model.interfaces to javafx.fxml;

    exports org.example.proyectofinalcodigo.model.records;
    opens   org.example.proyectofinalcodigo.model.records to javafx.fxml;
}
