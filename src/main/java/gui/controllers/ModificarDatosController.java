package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import logic.ControladoraLogica;
import logic.Mascota;

public class ModificarDatosController {

    private final ControladoraLogica logicControl = new ControladoraLogica();
    private Mascota mascota;

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
    private Button modificarButton;
    @FXML
    private Button limpiarButton;

    private void configurarTextFieldNumerico(TextField textField) {
        textField.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("\\d*")) ? change : null));
    }

    @FXML
    public void initialize() {
        configurarTextFieldNumerico(celDuenoField);
        modificarButton.setDisable(true);
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
        modificarButton.setDisable(!todosCompletos);
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
    public void modificarDatos() {

        // TODOS LOS DATOS DE LAS MASCOTAS
        String nombreMasco = nombreField.getText();
        String raza = razaField.getText();
        String color = colorField.getText();
        String observaciones = observacionesArea.getText();
        String alergico = alergicoCombo.getValue();
        String atenEsp = atencionEspecialCombo.getValue();

        // LOS DATOS DEL DUEÑO
        String nombreDueno = duenoField.getText();
        String celDueno = celDuenoField.getText();

        // Paso primero la mascota vieja y luego los valores nuevos modificados
        logicControl.modificarMascota(mascota, nombreMasco, raza, color, observaciones, alergico, atenEsp, nombreDueno, celDueno);

        // Mostrar mensaje modificado
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modificado exitoso");
        alert.setHeaderText(null);
        alert.setContentText("Se modificó correctamente.");
        alert.showAndWait();

        // Vaciar nuevamente el form
        limpiarDatos();
        // Cerrar la ventana actual
        Stage stage = (Stage) modificarButton.getScene().getWindow();
        stage.close();
    }

    // Este seteo lo hago desde VerDatosController para iniciar la ventana ModificarDatos con valores en los inputs
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
        if (mascota != null) {
            nombreField.setText(mascota.getNombre());
            razaField.setText(mascota.getRaza());
            colorField.setText(mascota.getColor());
            duenoField.setText(mascota.getUnDueno() != null ? mascota.getUnDueno().getNombre() : "");
            celDuenoField.setText(mascota.getUnDueno() != null ? mascota.getUnDueno().getCelDueno() : "");
            alergicoCombo.setValue(mascota.getAlergico());
            atencionEspecialCombo.setValue(mascota.getAtencion_especial());
            observacionesArea.setText(mascota.getObservaciones());
        }
    }

    public Mascota getMascota() {
        return mascota;
    }
}