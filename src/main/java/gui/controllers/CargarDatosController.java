package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextFormatter;
import logic.ControladoraLogica;

public class CargarDatosController {

    private final ControladoraLogica logicControl = new ControladoraLogica();

    @FXML
    private TextField nombreField;
    @FXML
    private TextField razaField;
    @FXML
    private TextField colorField;
    @FXML
    private TextField duenoField;
    @FXML
    private TextField celDuenoField;
    @FXML
    private ComboBox<String> alergicoCombo;
    @FXML
    private ComboBox<String> atencionEspecialCombo;
    @FXML
    private TextArea observacionesArea;
    @FXML
    private Button guardarButton;
    @FXML
    private Button limpiarButton;

    private void configurarTextFieldNumerico(TextField textField) {
        textField.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("\\d*")) ? change : null));
    }

    @FXML
    public void initialize() {
        configurarTextFieldNumerico(celDuenoField);
        guardarButton.setDisable(true);
        limpiarButton.setDisable(true);
        agregarListeners();
    }

    private void agregarListeners() {
        // Listener para cada campo de texto
        nombreField.textProperty().addListener((obs, oldValue, newValue) -> actualizarEstadoBotones());
        razaField.textProperty().addListener((obs, oldValue, newValue) -> actualizarEstadoBotones());
        colorField.textProperty().addListener((obs, oldValue, newValue) -> actualizarEstadoBotones());
        duenoField.textProperty().addListener((obs, oldValue, newValue) -> actualizarEstadoBotones());
        celDuenoField.textProperty().addListener((obs, oldValue, newValue) -> actualizarEstadoBotones());

        // Listener para ComboBox
        alergicoCombo.valueProperty().addListener((obs, oldValue, newValue) -> actualizarEstadoBotones());
        atencionEspecialCombo.valueProperty().addListener((obs, oldValue, newValue) -> actualizarEstadoBotones());

        // Listener para TextArea
        observacionesArea.textProperty().addListener((obs, oldValue, newValue) -> actualizarEstadoBotones());
    }

    private void actualizarEstadoBotones() {
        boolean hayDatos = !nombreField.getText().isEmpty() ||
                !razaField.getText().isEmpty() ||
                !colorField.getText().isEmpty() ||
                !duenoField.getText().isEmpty() ||
                !celDuenoField.getText().isEmpty() ||
                alergicoCombo.getValue() != null ||
                atencionEspecialCombo.getValue() != null ||
                !observacionesArea.getText().isEmpty();

        boolean todosCompletos = !nombreField.getText().isEmpty() &&
                !razaField.getText().isEmpty() &&
                !colorField.getText().isEmpty() &&
                !duenoField.getText().isEmpty() &&
                !celDuenoField.getText().isEmpty() &&
                alergicoCombo.getValue() != null &&
                atencionEspecialCombo.getValue() != null &&
                !observacionesArea.getText().isEmpty();

        limpiarButton.setDisable(!hayDatos);
        guardarButton.setDisable(!todosCompletos);
    }

    @FXML
    public void limpiarDatos() {
        nombreField.clear();
        razaField.clear();
        colorField.clear();
        duenoField.clear();
        celDuenoField.clear();
        alergicoCombo.getSelectionModel().clearSelection();
        atencionEspecialCombo.getSelectionModel().clearSelection();
        observacionesArea.clear();

        limpiarButton.setDisable(true);
    }

    @FXML
    public void guardarDatos() {

        String nombre = nombreField.getText();
        String raza = razaField.getText();
        String color = colorField.getText();
        String observaciones = observacionesArea.getText();
        String alergico = alergicoCombo.getValue();
        String atenEspecial = atencionEspecialCombo.getValue();

        String dueno = duenoField.getText();
        String celDueno = celDuenoField.getText();

        logicControl.guardar(nombre, raza, color, observaciones, alergico, atenEspecial, dueno, celDueno);

        // Mostrar mensaje Guardado
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guardado exitoso");
        alert.setHeaderText(null);
        alert.setContentText("Se guardó correctamente.");
        alert.showAndWait();

        // Vaciar nuevamente el form
        limpiarDatos();
    }

}
