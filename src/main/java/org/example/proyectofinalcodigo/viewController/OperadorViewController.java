package org.example.proyectofinalcodigo.viewController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.proyectofinalcodigo.App;
import org.example.proyectofinalcodigo.controller.OperadorController;
import org.example.proyectofinalcodigo.model.clases.Atraccion;
import org.example.proyectofinalcodigo.model.clases.Operador;
import org.example.proyectofinalcodigo.model.enums.EstadoActual;
import org.example.proyectofinalcodigo.model.enums.MotivoCierre;

public class OperadorViewController {

    private App app;
    private OperadorController controlador;
    private Operador operadorActual;

    @FXML private ComboBox<Operador>      cbOperador;
    @FXML private Label                   lblInfoOperador;
    @FXML private Label                   lblZona;

    @FXML private TableView<Atraccion>         tablaAtracciones;
    @FXML private TableColumn<Atraccion, String>  colNombreA;
    @FXML private TableColumn<Atraccion, String>  colTipoA;
    @FXML private TableColumn<Atraccion, String>  colEstadoA;
    @FXML private TableColumn<Atraccion, Integer> colVisitantesA;
    @FXML private TableColumn<Atraccion, Integer> colEsperaA;

    @FXML private TextField             txtDocVisitante;
    @FXML private ComboBox<Atraccion>   cbAtraccionAcceso;
    @FXML private Label                 lblResultadoAcceso;

    @FXML private ComboBox<Atraccion>    cbAtraccionEstado;
    @FXML private ComboBox<EstadoActual> cbNuevoEstado;
    @FXML private ComboBox<MotivoCierre> cbMotivo;
    @FXML private Label                  lblResultadoEstado;

    @FXML private ComboBox<Atraccion> cbAtraccionRevision;
    @FXML private TextArea            txtDescripcion;
    @FXML private Label               lblResultadoRevision;

    @FXML private TextField txtDocSaldo;
    @FXML private TextField txtMontoSaldo;
    @FXML private Label     lblResultadoSaldo;

    public void setApp(App app) {
        this.app = app;
        this.controlador = new OperadorController(app.parque);

        cbOperador.setItems(FXCollections.observableArrayList(controlador.getOperadores()));
        cbNuevoEstado.setItems(FXCollections.observableArrayList(EstadoActual.values()));
        cbMotivo.setItems(FXCollections.observableArrayList(MotivoCierre.values()));
    }

    @FXML
    public void initialize() {
        colNombreA.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTipoA.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colEstadoA.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colVisitantesA.setCellValueFactory(new PropertyValueFactory<>("contadorVisitantes"));
        colEsperaA.setCellValueFactory(new PropertyValueFactory<>("tiempoEspera"));
    }

    @FXML
    private void onSeleccionarOperador() {
        Operador op = cbOperador.getValue();
        if (op == null) return;
        operadorActual = op;

        lblInfoOperador.setText("Turno: " + op.getTurno());
        lblZona.setText("Zona: " + controlador.getNombreZona(op.getIdZona()));

        refrescarAtracciones();
    }

    private void refrescarAtracciones() {
        if (operadorActual == null) return;
        var lista = FXCollections.observableArrayList(
                controlador.getAtraccionesDeZona(operadorActual.getIdZona()));
        tablaAtracciones.setItems(lista);
        cbAtraccionAcceso.setItems(lista);
        cbAtraccionEstado.setItems(lista);
        cbAtraccionRevision.setItems(lista);
    }

    @FXML
    private void onValidarAcceso() {
        String resultado = controlador.validarAcceso(
                txtDocVisitante.getText().trim(),
                cbAtraccionAcceso.getValue());
        lblResultadoAcceso.setText(resultado);
    }

    @FXML
    private void onCambiarEstado() {
        String resultado = controlador.cambiarEstado(
                cbAtraccionEstado.getValue(),
                cbNuevoEstado.getValue(),
                cbMotivo.getValue());
        lblResultadoEstado.setText(resultado);
        refrescarAtracciones();
    }

    @FXML
    private void onRegistrarRevision() {
        String resultado = controlador.registrarRevision(
                operadorActual,
                cbAtraccionRevision.getValue(),
                txtDescripcion.getText().trim());
        lblResultadoRevision.setText(resultado);
        refrescarAtracciones();
    }

    @FXML
    private void onRecargarSaldo() {
        try {
            double monto = Double.parseDouble(txtMontoSaldo.getText().trim());
            String resultado = controlador.recargarSaldo(txtDocSaldo.getText().trim(), monto);
            lblResultadoSaldo.setText(resultado);
        } catch (NumberFormatException e) {
            lblResultadoSaldo.setText("Ingresa un monto valido.");
        }
    }

    @FXML
    private void onVolver() {
        app.mostrarMenu();
    }
}
