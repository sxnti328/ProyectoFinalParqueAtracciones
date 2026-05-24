package org.example.proyectofinalcodigo.viewController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.proyectofinalcodigo.App;
import org.example.proyectofinalcodigo.controller.AdminController;
import org.example.proyectofinalcodigo.model.clases.Atraccion;
import org.example.proyectofinalcodigo.model.clases.Operador;
import org.example.proyectofinalcodigo.model.clases.Zona;
import org.example.proyectofinalcodigo.model.enums.TipoAtraccion;

public class AdminViewController {

    private App app;
    private AdminController adminController;

    // Operadores
    @FXML private TextField txtOpNombre;
    @FXML private TextField txtOpDocumento;
    @FXML private TextField txtOpEdad;
    @FXML private TextField txtOpIdEmp;
    @FXML private TextField txtOpTurno;
    @FXML private ComboBox<Zona> cbOpZona;
    @FXML private Label lblOpMsg;
    @FXML private TableView<Operador> tablaOperadores;
    @FXML private TableColumn<Operador, String> colOpNombre;
    @FXML private TableColumn<Operador, String> colOpDoc;
    @FXML private TableColumn<Operador, String> colOpTurno;
    @FXML private TableColumn<Operador, String> colOpZona;

    // Zonas
    @FXML private TextField txtZonaId;
    @FXML private TextField txtZonaNombre;
    @FXML private TextField txtZonaDesc;
    @FXML private TextField txtZonaCap;
    @FXML private Label lblZonaMsg;
    @FXML private TableView<Zona> tablaZonas;
    @FXML private TableColumn<Zona, String>  colZonaId;
    @FXML private TableColumn<Zona, String>  colZonaNombre;
    @FXML private TableColumn<Zona, Integer> colZonaCap;

    // Atracciones
    @FXML private TextField txtAtrId;
    @FXML private TextField txtAtrNombre;
    @FXML private ComboBox<TipoAtraccion> cbAtrTipo;
    @FXML private TextField txtAtrCap;
    @FXML private TextField txtAtrAltura;
    @FXML private TextField txtAtrEdad;
    @FXML private TextField txtAtrCosto;
    @FXML private ComboBox<Zona> cbAtrZona;
    @FXML private Label lblAtrMsg;
    @FXML private TableView<Atraccion> tablaAtracciones;
    @FXML private TableColumn<Atraccion, String>  colAtrNombre;
    @FXML private TableColumn<Atraccion, String>  colAtrTipo;
    @FXML private TableColumn<Atraccion, String>  colAtrEstado;
    @FXML private TableColumn<Atraccion, Integer> colAtrVisitantes;

    @FXML private Label    lblAlertaMsg;
    @FXML private TextArea txtReporte;

    public void setApp(App app) {
        this.app = app;
        this.adminController = new AdminController(app.parque);
        initView();
    }

    private void initView() {
        // Operadores
        colOpNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colOpDoc.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colOpTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));
        colOpZona.setCellValueFactory(new PropertyValueFactory<>("idZona"));
        cbOpZona.setItems(FXCollections.observableArrayList(adminController.obtenerListaZonas()));

        // Zonas
        colZonaId.setCellValueFactory(new PropertyValueFactory<>("idZona"));
        colZonaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colZonaCap.setCellValueFactory(new PropertyValueFactory<>("capacidadMax"));

        // Atracciones
        colAtrNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colAtrTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colAtrEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colAtrVisitantes.setCellValueFactory(new PropertyValueFactory<>("contadorVisitantes"));
        cbAtrTipo.setItems(FXCollections.observableArrayList(TipoAtraccion.values()));
        cbAtrZona.setItems(FXCollections.observableArrayList(adminController.obtenerListaZonas()));

        cargarTablas();
    }

    // ----- Operadores -----

    @FXML
    private void onGuardarOperador() {
        try {
            Zona z = cbOpZona.getValue();
            int edad = Integer.parseInt(txtOpEdad.getText());
            boolean ok = adminController.agregarOperador(
                    txtOpNombre.getText(), txtOpDocumento.getText(), edad,
                    txtOpIdEmp.getText(), txtOpTurno.getText(), z);
            lblOpMsg.setText(ok ? "Operador agregado." : "Ya existe ese documento.");
            cargarTablas();
        } catch (NumberFormatException e) {
            lblOpMsg.setText("Edad invalida.");
        }
    }

    @FXML
    private void onEliminarOperador() {
        Operador sel = tablaOperadores.getSelectionModel().getSelectedItem();
        if (sel == null) { lblOpMsg.setText("Seleccione un operador."); return; }
        adminController.eliminarOperador(sel.getDocumento());
        lblOpMsg.setText("Operador eliminado.");
        cargarTablas();
    }

    // ----- Zonas -----

    @FXML
    private void onGuardarZona() {
        try {
            int cap = Integer.parseInt(txtZonaCap.getText());
            boolean ok = adminController.agregarZona(
                    txtZonaId.getText(), txtZonaNombre.getText(), txtZonaDesc.getText(), cap);
            lblZonaMsg.setText(ok ? "Zona agregada." : "Ya existe esa zona.");
            cargarTablas();
            cbOpZona.setItems(FXCollections.observableArrayList(adminController.obtenerListaZonas()));
            cbAtrZona.setItems(FXCollections.observableArrayList(adminController.obtenerListaZonas()));
        } catch (NumberFormatException e) {
            lblZonaMsg.setText("Capacidad invalida.");
        }
    }

    @FXML
    private void onEliminarZona() {
        Zona sel = tablaZonas.getSelectionModel().getSelectedItem();
        if (sel == null) { lblZonaMsg.setText("Seleccione una zona."); return; }
        adminController.eliminarZona(sel.getIdZona());
        lblZonaMsg.setText("Zona eliminada.");
        cargarTablas();
    }

    // ----- Atracciones -----

    @FXML
    private void onGuardarAtraccion() {
        try {
            Zona z = cbAtrZona.getValue();
            TipoAtraccion tipo = cbAtrTipo.getValue();
            if (z == null || tipo == null) {
                lblAtrMsg.setText("Seleccione zona y tipo.");
                return;
            }
            int cap       = Integer.parseInt(txtAtrCap.getText());
            double altura = Double.parseDouble(txtAtrAltura.getText());
            int edad      = Integer.parseInt(txtAtrEdad.getText());
            double costo  = Double.parseDouble(txtAtrCosto.getText());

            adminController.agregarAtraccion(z, tipo, txtAtrId.getText(),
                    txtAtrNombre.getText(), cap, altura, edad, costo);
            lblAtrMsg.setText("Atraccion agregada a zona " + z.getNombre());
            cargarTablas();
        } catch (NumberFormatException e) {
            lblAtrMsg.setText("Revise los datos numericos.");
        }
    }

    @FXML
    private void onEliminarAtraccion() {
        Atraccion sel = tablaAtracciones.getSelectionModel().getSelectedItem();
        if (sel == null) { lblAtrMsg.setText("Seleccione una atraccion."); return; }
        adminController.eliminarAtraccion(sel);
        lblAtrMsg.setText("Atraccion eliminada.");
        cargarTablas();
    }

    // ----- Alertas / reportes -----

    @FXML
    private void onActivarAlerta() {
        adminController.activarAlertaClimatica();
        lblAlertaMsg.setText("Alerta climatica activada.");
        cargarTablas();
    }

    @FXML
    private void onDesactivarAlerta() {
        adminController.desactivarAlertaClimatica();
        lblAlertaMsg.setText("Alerta climatica desactivada.");
        cargarTablas();
    }

    @FXML
    private void onGenerarReporte() {
        txtReporte.setText(adminController.generarReporte());
    }

    @FXML
    private void onVolver() {
        app.mostrarMenuPrincipal();
    }

    private void cargarTablas() {
        tablaOperadores.setItems(FXCollections.observableArrayList(adminController.obtenerListaOperadores()));
        tablaZonas.setItems(FXCollections.observableArrayList(adminController.obtenerListaZonas()));
        tablaAtracciones.setItems(FXCollections.observableArrayList(adminController.obtenerListaAtracciones()));
    }
}