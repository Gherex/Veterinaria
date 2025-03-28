package gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.text.html.ImageView;

public class CargarDatosController {

    // Mapea los elementos si necesitas acceder a ellos
    @FXML private TextField nombreField;
    @FXML private TextField razaField;
    @FXML private TextField colorField;
    @FXML private ComboBox<String> alergicoCombo;
    @FXML private ComboBox<String> atencionEspecialCombo;
    @FXML private Button guardarButton;
    @FXML private ImageView imagenMascota;

    @FXML
    private void limpiarFormulario() {
        // Lógica para limpiar campos
    }

    @FXML
    private void guardarDatos() {

    }

    public void limpiarDatos(ActionEvent actionEvent) {
    }
}