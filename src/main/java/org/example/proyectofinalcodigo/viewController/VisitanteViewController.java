package org.example.proyectofinalcodigo.viewController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.proyectofinalcodigo.App;
import org.example.proyectofinalcodigo.model.clases.Visitante;
import org.example.proyectofinalcodigo.model.enums.TipoTicket;

public class VisitanteViewController {

    private App app;

    @FXML private TextField txtNombre;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtEdad;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtEstatura;

    @FXML private TableView<Visitante> tablaVisitantes;
    @FXML private TableColumn<Visitante, String> colNombre;
    @FXML private TableColumn<Visitante, String> colDocumento;
    @FXML private TableColumn<Visitante, Integer> colEdad;
    @FXML private TableColumn<Visitante, String> colTelefono;
    @FXML private TableColumn<Visitante, String> colDireccion;
    @FXML private TableColumn<Visitante, Double> colEstatura;
    @FXML private TableColumn<Visitante, Double> colSaldo;

    @FXML private Label lblMensaje;
    @FXML private Label lblNotificacion;
    @FXML private Label lblDescuentoInfo;
    @FXML private Label lblNumIntegrantes;
    @FXML private Label lblTicketMsg;
    @FXML private ComboBox<TipoTicket> cbTipoTicket;
    @FXML private TextField txtPrecioTicket;
    @FXML private TextField txtNumIntegrantes;

    public void setApp(App app) {
        this.app = app;
        refrescarTabla();
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colEstatura.setCellValueFactory(new PropertyValueFactory<>("estatura"));
        colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldoVirtual"));

        cbTipoTicket.setItems(FXCollections.observableArrayList(TipoTicket.values()));
    }

    private void refrescarTabla() {
        tablaVisitantes.setItems(
                FXCollections.observableArrayList(app.parque.getListVisitante()));
    }

    @FXML
    private void onVolver() {
        app.mostrarMenu();
    }

    @FXML
    private void onGuardar() {
        lblMensaje.setText("pendiente");
    }

    @FXML
    private void onActualizar() {
        lblMensaje.setText("que funcione esto porfa tengo sueño");
    }

    @FXML
    private void onEliminar() {
        lblMensaje.setText("pendiente");
    }

    @FXML
    private void onCambiarTipoTicket() {
        TipoTicket tipo = cbTipoTicket.getValue();
        boolean familiar = tipo == TipoTicket.FAMILIAR;
        lblNumIntegrantes.setVisible(familiar);
        lblNumIntegrantes.setManaged(familiar);
        txtNumIntegrantes.setVisible(familiar);
        txtNumIntegrantes.setManaged(familiar);
    }

    @FXML
    private void onComprarTicket() {
        lblTicketMsg.setText("pendiente");
    }
}
