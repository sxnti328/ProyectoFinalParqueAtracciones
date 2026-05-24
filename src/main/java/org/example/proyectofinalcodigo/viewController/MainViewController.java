package org.example.proyectofinalcodigo.viewController;

import javafx.fxml.FXML;
import org.example.proyectofinalcodigo.App;

public class MainViewController {

    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void irAVisitantes() {
        app.mostrarVisitantes();
    }

    @FXML
    private void irAOperador() {
        app.mostrarOperador();
    }

    @FXML
    private void irAAdmin() {
        app.mostrarAdmin();
    }
}
