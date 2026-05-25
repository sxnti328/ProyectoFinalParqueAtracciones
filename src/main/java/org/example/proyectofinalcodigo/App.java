package org.example.proyectofinalcodigo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.proyectofinalcodigo.model.clases.ParqueDeAtraccion;
import org.example.proyectofinalcodigo.viewController.AdminViewController;
import org.example.proyectofinalcodigo.viewController.MainViewController;
import org.example.proyectofinalcodigo.viewController.OperadorViewController;
import org.example.proyectofinalcodigo.viewController.VisitanteViewController;

public class App extends Application {

    private Stage stage;
    public ParqueDeAtraccion parque;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("Parque de Atracciones");
        stage.setWidth(950);
        stage.setHeight(700);
        stage.setMinWidth(800);
        stage.setMinHeight(600);

        parque = new ParqueDeAtraccion("Tech-Park UQ", "NIT-123-456", "Calle 100 # 50-30", 500);
        mostrarMenu();
        stage.show();
    }

    private void cambiarVista(Parent root) {
        stage.setScene(new Scene(root, 950, 700));
    }

    public void mostrarMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/proyectofinalcodigo/menu-principal.fxml"));
            Parent root = loader.load();
            MainViewController vc = loader.getController();
            vc.setApp(this);
            cambiarVista(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarVisitantes() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/proyectofinalcodigo/CrudVisitante.fxml"));
            Parent root = loader.load();
            VisitanteViewController vc = loader.getController();
            vc.setApp(this);
            stage.setScene(new Scene(root, 950, 840));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarOperador() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/proyectofinalcodigo/OperadorView.fxml"));
            Parent root = loader.load();
            OperadorViewController vc = loader.getController();
            vc.setApp(this);
            cambiarVista(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/proyectofinalcodigo/AdminView.fxml"));
            Parent root = loader.load();
            AdminViewController vc = loader.getController();
            vc.setApp(this);
            cambiarVista(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
