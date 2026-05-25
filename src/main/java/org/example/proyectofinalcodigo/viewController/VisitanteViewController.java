package org.example.proyectofinalcodigo.viewController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.proyectofinalcodigo.App;
import org.example.proyectofinalcodigo.controller.VisitanteController;
import org.example.proyectofinalcodigo.model.clases.Atraccion;
import org.example.proyectofinalcodigo.model.clases.Visitante;
import org.example.proyectofinalcodigo.model.enums.TipoTicket;

public class VisitanteViewController {

    private App app;
    private VisitanteController controlador;

    @FXML private TextField txtNombre;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtEdad;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtEstatura;

    @FXML private TableView<Visitante> tablaVisitantes;
    @FXML private TableColumn<Visitante, String> colNombre;
    @FXML private TableColumn<Visitante, String> colDocumento;
    @FXML private TableColumn<Visitante, Integer>colEdad;
    @FXML private TableColumn<Visitante, String> colTelefono;
    @FXML private TableColumn<Visitante, String> colDireccion;
    @FXML private TableColumn<Visitante, Double> colEstatura;
    @FXML private TableColumn<Visitante, Double> colSaldo;

    @FXML private Label lblMensaje;
    @FXML private Label lblNotificacion;
    @FXML private Label lblDescuentoInfo;
    @FXML private Label lblNumIntegrantes;
    @FXML private Label lblTicketMsg;
    @FXML private Label lblPrecioTicket;
    @FXML private ComboBox<TipoTicket> cbTipoTicket;
    @FXML private TextField txtNumIntegrantes;

    @FXML private ComboBox<Atraccion> cbAtraccionFav;
    @FXML private Label               lblFavoritas;
    @FXML private TextArea            txtNotificaciones;

    private static final double PRECIO_GENERAL   = 25000;
    private static final double PRECIO_FAMILIAR  = 45000;
    private static final double PRECIO_FAST_PASS = 60000;

    public void setApp(App app) {
        this.app = app;
        this.controlador = new VisitanteController(app.parque);

        cbAtraccionFav.setItems(FXCollections.observableArrayList(controlador.getTodasAtracciones()));

        tablaVisitantes.getSelectionModel().selectedItemProperty().addListener(
                (obs, anterior, seleccionado) -> {
                    if (seleccionado != null) {
                        llenarFormulario(seleccionado);
                    }
                }
        );

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
                FXCollections.observableArrayList(controlador.getVisitantes()));
        tablaVisitantes.refresh();
    }

    private void llenarFormulario(Visitante v) {
        txtNombre.setText(v.getNombre());
        txtDocumento.setText(v.getDocumento());
        txtEdad.setText(String.valueOf(v.getEdad()));
        txtTelefono.setText(v.getTelefono());
        txtDireccion.setText(v.getDireccion());
        txtEstatura.setText(String.valueOf(v.getEstatura()));

        var favs = controlador.getFavoritas(v.getDocumento());
        lblFavoritas.setText(favs.isEmpty() ? "Sin favoritas" : String.join(", ", favs));

        txtNotificaciones.setText(controlador.getNotificacionesTexto(v.getDocumento()));
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtDocumento.clear();
        txtEdad.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        txtEstatura.clear();
        lblFavoritas.setText("Sin favoritas");
        txtNotificaciones.clear();
        tablaVisitantes.getSelectionModel().clearSelection();
    }

    @FXML
    private void onVolver() {
        app.mostrarMenu();
    }

    @FXML
    private void onGuardar() {
        String nombre = txtNombre.getText().trim();
        String documento = txtDocumento.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();

        try {
            int edad = Integer.parseInt(txtEdad.getText().trim());
            double estatura = Double.parseDouble(txtEstatura.getText().trim());

            boolean ok = controlador.agregarVisitante(nombre, documento, edad,
                    estatura, telefono, direccion);
            if (ok) {
                limpiarCampos();
                refrescarTabla();
                lblMensaje.setText("Visitante guardado correctamente.");
            } else {
                lblMensaje.setText("Ya existe un visitante con ese documento.");
            }
        } catch (NumberFormatException e) {
            lblMensaje.setText("Edad debe ser entero y estatura debe ser número.");
        }
    }

    @FXML
    private void onActualizar() {
        Visitante seleccionado = tablaVisitantes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            lblMensaje.setText("Selecciona un visitante de la tabla.");
            return;
        }

        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();

        try {
            int edad = Integer.parseInt(txtEdad.getText().trim());
            double estatura = Double.parseDouble(txtEstatura.getText().trim());

            boolean ok = controlador.actualizarVisitante(seleccionado.getDocumento(),
                    nombre, edad, estatura, telefono, direccion);
            if (ok) {
                limpiarCampos();
                refrescarTabla();
                lblMensaje.setText("Visitante actualizado.");
            } else {
                lblMensaje.setText("No se pudo actualizar.");
            }
        } catch (NumberFormatException e) {
            lblMensaje.setText("Verifica que edad y estatura sean números válidos.");
        }
    }

    @FXML
    private void onEliminar() {
        Visitante seleccionado = tablaVisitantes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            lblMensaje.setText("Selecciona un visitante de la tabla.");
            return;
        }

        boolean ok = controlador.eliminarVisitante(seleccionado.getDocumento());
        if (ok) {
            limpiarCampos();
            refrescarTabla();
            lblMensaje.setText("Visitante eliminado.");
        } else {
            lblMensaje.setText("No se pudo eliminar el visitante.");
        }
    }

    @FXML
    private void onCambiarTipoTicket() {
        TipoTicket tipo = cbTipoTicket.getValue();
        boolean familiar = tipo == TipoTicket.FAMILIAR;
        lblNumIntegrantes.setVisible(familiar);
        lblNumIntegrantes.setManaged(familiar);
        txtNumIntegrantes.setVisible(familiar);
        txtNumIntegrantes.setManaged(familiar);

        if (tipo != null) {
            double precio = getPrecioFijo(tipo);
            lblPrecioTicket.setText("$" + String.format("%.0f", precio));
        }
    }

    private double getPrecioFijo(TipoTicket tipo) {
        switch (tipo) {
            case FAMILIAR:  return PRECIO_FAMILIAR;
            case FAST_PASS: return PRECIO_FAST_PASS;
            default:        return PRECIO_GENERAL;
        }
    }

    @FXML
    private void onComprarTicket() {
        Visitante seleccionado = tablaVisitantes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            lblTicketMsg.setText("Selecciona un visitante primero.");
            return;
        }

        TipoTicket tipo = cbTipoTicket.getValue();

        double precio = getPrecioFijo(tipo);
        int integrantes = 1;
        try {
            if (tipo == TipoTicket.FAMILIAR) {
                integrantes = Integer.parseInt(txtNumIntegrantes.getText().trim());
            }
        } catch (NumberFormatException e) {
            lblTicketMsg.setText("Numero de integrantes invalido.");
            return;
        }

        String resultado = controlador.comprarTicket(
                seleccionado.getDocumento(), tipo, precio, integrantes);
        lblTicketMsg.setText(resultado);
        refrescarTabla();
    }

    @FXML
    private void onVerMapa() {
        var url = getClass().getResource("/org/example/proyectofinalcodigo/mapa_parque.png");
        if (url == null) {
            lblMensaje.setText("Imagen del mapa no encontrada en recursos.");
            return;
        }
        ImageView img = new ImageView(new Image(url.toExternalForm()));
        img.setFitWidth(860);
        img.setPreserveRatio(true);

        ScrollPane scroll = new ScrollPane(img);
        scroll.setFitToWidth(true);

        Stage ventana = new Stage();
        ventana.setTitle("Mapa del parque - Tech-Park UQ");
        ventana.setScene(new Scene(scroll, 880, 640));
        ventana.show();
    }

    @FXML
    private void onAgregarFavorita() {
        Visitante sel = tablaVisitantes.getSelectionModel().getSelectedItem();
        if (sel == null) {
            lblFavoritas.setText("Selecciona un visitante primero.");
            return;
        }
        Atraccion a = cbAtraccionFav.getValue();
        if (a == null) {
            lblFavoritas.setText("Selecciona una atraccion.");
            return;
        }
        controlador.agregarFavorita(sel.getDocumento(), a.getId());
        var favs = controlador.getFavoritas(sel.getDocumento());
        lblFavoritas.setText(String.join(", ", favs));
    }
}
