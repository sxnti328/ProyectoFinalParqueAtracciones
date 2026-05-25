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
import javafx.beans.property.SimpleStringProperty;
import org.example.proyectofinalcodigo.model.enums.EstadoActual;
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
    @FXML private TableColumn<Operador, String>  colOpNombre;
    @FXML private TableColumn<Operador, String>  colOpDoc;
    @FXML private TableColumn<Operador, Integer> colOpEdad;
    @FXML private TableColumn<Operador, String>  colOpIdEmp;
    @FXML private TableColumn<Operador, String>  colOpTurno;
    @FXML private TableColumn<Operador, String>  colOpZona;

    // Zonas
    @FXML private TextField txtZonaId;
    @FXML private TextField txtZonaNombre;
    @FXML private TextField txtZonaDesc;
    @FXML private TextField txtZonaCap;
    @FXML private Label lblZonaMsg;
    @FXML private TableView<Zona> tablaZonas;
    @FXML private TableColumn<Zona, String>  colZonaId;
    @FXML private TableColumn<Zona, String>  colZonaNombre;
    @FXML private TableColumn<Zona, String>  colZonaDesc;
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
    @FXML private TableColumn<Atraccion, String>        colAtrId;
    @FXML private TableColumn<Atraccion, String>        colAtrNombre;
    @FXML private TableColumn<Atraccion, TipoAtraccion> colAtrTipo;
    @FXML private TableColumn<Atraccion, String>        colAtrZona;
    @FXML private TableColumn<Atraccion, Integer>       colAtrCap;
    @FXML private TableColumn<Atraccion, Double>        colAtrAltura;
    @FXML private TableColumn<Atraccion, Integer>       colAtrEdadMin;
    @FXML private TableColumn<Atraccion, Double>        colAtrCosto;
    @FXML private TableColumn<Atraccion, EstadoActual>  colAtrEstado;
    @FXML private TableColumn<Atraccion, Integer>       colAtrVisitantes;

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
        colOpEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colOpIdEmp.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        colOpTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));
        colOpZona.setCellValueFactory(new PropertyValueFactory<>("idZona"));
        cbOpZona.setItems(FXCollections.observableArrayList(adminController.obtenerListaZonas()));

        // Zonas
        colZonaId.setCellValueFactory(new PropertyValueFactory<>("idZona"));
        colZonaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colZonaDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colZonaCap.setCellValueFactory(new PropertyValueFactory<>("capacidadMax"));

        // Atracciones
        colAtrId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAtrNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colAtrTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colAtrZona.setCellValueFactory(c -> {
            Zona z = c.getValue().getZona();
            return new SimpleStringProperty(z != null ? z.getNombre() : "");
        });
        colAtrCap.setCellValueFactory(new PropertyValueFactory<>("capacidadMaxima"));
        colAtrAltura.setCellValueFactory(new PropertyValueFactory<>("alturaMinima"));
        colAtrEdadMin.setCellValueFactory(new PropertyValueFactory<>("edadMinima"));
        colAtrCosto.setCellValueFactory(new PropertyValueFactory<>("costoAdicional"));
        colAtrEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colAtrVisitantes.setCellValueFactory(new PropertyValueFactory<>("contadorVisitantes"));
        cbAtrTipo.setItems(FXCollections.observableArrayList(TipoAtraccion.values()));
        cbAtrZona.setItems(FXCollections.observableArrayList(adminController.obtenerListaZonas()));

        tablaOperadores.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtOpNombre.setText(sel.getNombre());
                txtOpDocumento.setText(sel.getDocumento());
                txtOpEdad.setText(String.valueOf(sel.getEdad()));
                txtOpIdEmp.setText(sel.getIdEmpleado());
                txtOpTurno.setText(sel.getTurno());
                cbOpZona.setValue(null);
                for (Zona z : adminController.obtenerListaZonas()) {
                    if (z.getIdZona().equals(sel.getIdZona())) {
                        cbOpZona.setValue(z);
                        break;
                    }
                }
            }
        });

        tablaZonas.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtZonaId.setText(sel.getIdZona());
                txtZonaNombre.setText(sel.getNombre());
                txtZonaDesc.setText(sel.getDescripcion());
                txtZonaCap.setText(String.valueOf(sel.getCapacidadMax()));
            }
        });

        tablaAtracciones.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtAtrId.setText(sel.getId());
                txtAtrNombre.setText(sel.getNombre());
                txtAtrCap.setText(String.valueOf(sel.getCapacidadMaxima()));
                txtAtrAltura.setText(String.valueOf(sel.getAlturaMinima()));
                txtAtrEdad.setText(String.valueOf(sel.getEdadMinima()));
                txtAtrCosto.setText(String.valueOf(sel.getCostoAdicional()));
            }
        });

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
            if (ok) limpiarCamposOperador();
            cargarTablas();
        } catch (NumberFormatException e) {
            lblOpMsg.setText("Edad invalida.");
        }
    }

    @FXML
    private void onEliminarOperador() {
        Operador sel = tablaOperadores.getSelectionModel().getSelectedItem();
        if (sel == null) { lblOpMsg.setText("Seleccione un operador."); return; }
        for (Zona z : adminController.obtenerListaZonas()) {
            if (z.getIdZona().equals(sel.getIdZona())) {
                if (!z.getListAtraccion().isEmpty() && z.getListOperador().size() <= 1) {
                    lblOpMsg.setText("No se puede eliminar: es el unico operador de la zona.");
                    return;
                }
            }
        }
        adminController.eliminarOperador(sel.getDocumento());
        lblOpMsg.setText("Operador eliminado.");
        limpiarCamposOperador();
        cargarTablas();
    }

    @FXML
    private void onActualizarOperador() {
        Operador sel = tablaOperadores.getSelectionModel().getSelectedItem();
        if (sel == null) { lblOpMsg.setText("Seleccione un operador."); return; }
        try {
            int edad = Integer.parseInt(txtOpEdad.getText());
            boolean ok = adminController.actualizarOperador(sel.getDocumento(),
                    txtOpNombre.getText(), edad, txtOpTurno.getText());
            Zona zonaSeleccionada = cbOpZona.getValue();
            if (ok && zonaSeleccionada != null) {
                adminController.asignarOperadorAZona(sel.getIdEmpleado(), zonaSeleccionada.getIdZona());
            }
            lblOpMsg.setText(ok ? "Operador actualizado." : "No se encontro el operador.");
            if (ok) limpiarCamposOperador();
            cargarTablas();
        } catch (NumberFormatException e) {
            lblOpMsg.setText("Edad invalida.");
        }
    }

    // ----- Zonas -----

    @FXML
    private void onGuardarZona() {
        try {
            int cap = Integer.parseInt(txtZonaCap.getText());
            boolean ok = adminController.agregarZona(
                    txtZonaId.getText(), txtZonaNombre.getText(), txtZonaDesc.getText(), cap);
            lblZonaMsg.setText(ok ? "Zona agregada." : "Ya existe esa zona.");
            if (ok) limpiarCamposZona();
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
        limpiarCamposZona();
        cargarTablas();
    }

    @FXML
    private void onActualizarZona() {
        Zona sel = tablaZonas.getSelectionModel().getSelectedItem();
        if (sel == null) { lblZonaMsg.setText("Seleccione una zona."); return; }
        try {
            int cap = Integer.parseInt(txtZonaCap.getText());
            boolean ok = adminController.actualizarZona(sel.getIdZona(),
                    txtZonaNombre.getText(), txtZonaDesc.getText(), cap);
            lblZonaMsg.setText(ok ? "Zona actualizada." : "No se encontro la zona.");
            if (ok) limpiarCamposZona();
            cargarTablas();
        } catch (NumberFormatException e) {
            lblZonaMsg.setText("Capacidad invalida.");
        }
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

            boolean ok = adminController.agregarAtraccion(z, tipo, txtAtrId.getText(),
                    txtAtrNombre.getText(), cap, altura, edad, costo);
            lblAtrMsg.setText(ok ? "Atraccion agregada a zona " + z.getNombre() : "Error al agregar.");
            if (ok) limpiarCamposAtraccion();
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
        limpiarCamposAtraccion();
        cargarTablas();
    }

    @FXML
    private void onActualizarAtraccion() {
        Atraccion sel = tablaAtracciones.getSelectionModel().getSelectedItem();
        if (sel == null) { lblAtrMsg.setText("Seleccione una atraccion."); return; }
        try {
            int cap       = Integer.parseInt(txtAtrCap.getText());
            double altura = Double.parseDouble(txtAtrAltura.getText());
            int edad      = Integer.parseInt(txtAtrEdad.getText());
            double costo  = Double.parseDouble(txtAtrCosto.getText());
            boolean ok = adminController.actualizarAtraccion(sel.getId(),
                    txtAtrNombre.getText(), cap, altura, edad, costo);
            lblAtrMsg.setText(ok ? "Atraccion actualizada." : "No se encontro la atraccion.");
            if (ok) limpiarCamposAtraccion();
            cargarTablas();
        } catch (NumberFormatException e) {
            lblAtrMsg.setText("Revise los datos numericos.");
        }
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
        app.mostrarMenu();
    }

    private void limpiarCamposOperador() {
        txtOpNombre.clear();
        txtOpDocumento.clear();
        txtOpEdad.clear();
        txtOpIdEmp.clear();
        txtOpTurno.clear();
        cbOpZona.setValue(null);
        tablaOperadores.getSelectionModel().clearSelection();
    }

    private void limpiarCamposZona() {
        txtZonaId.clear();
        txtZonaNombre.clear();
        txtZonaDesc.clear();
        txtZonaCap.clear();
        tablaZonas.getSelectionModel().clearSelection();
    }

    private void limpiarCamposAtraccion() {
        txtAtrId.clear();
        txtAtrNombre.clear();
        txtAtrCap.clear();
        txtAtrAltura.clear();
        txtAtrEdad.clear();
        txtAtrCosto.clear();
        cbAtrTipo.setValue(null);
        cbAtrZona.setValue(null);
        tablaAtracciones.getSelectionModel().clearSelection();
    }

    private void cargarTablas() {
        tablaOperadores.setItems(FXCollections.observableArrayList(adminController.obtenerListaOperadores()));
        tablaOperadores.refresh();
        tablaZonas.setItems(FXCollections.observableArrayList(adminController.obtenerListaZonas()));
        tablaZonas.refresh();
        tablaAtracciones.setItems(FXCollections.observableArrayList(adminController.obtenerListaAtracciones()));
        tablaAtracciones.refresh();
    }
}
